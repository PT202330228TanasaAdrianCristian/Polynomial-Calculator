package Buisness;

import Interface.LogWindow;
import Interface.MainWindow;
import Model.Server;
import Model.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.System.exit;

public class SimulationManager implements Runnable
{
    private Scheduler scheduler;
    private MainWindow guiWindow;
    private List<Task> lstTasks;
    private SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private int m_nrOfClients;
    private int m_maxSimulationTime;
    private int m_nrOfServers;
    private int m_nrOfQueues;
    private int m_simulationInterval;
    private int m_currentTime = 0;
    private int m_minArrivalTime;
    private int m_maxArrivalTime;
    private int m_minServiceTime;
    private int m_maxServiceTime;
    private static Thread thrd;

    private LogWindow aLogWindow;

    private BufferedWriter aBufferedWriter;

    public SimulationManager() throws IOException {
        guiWindow = new MainWindow();
        aBufferedWriter = new BufferedWriter(new FileWriter("theLogs.txt"));

        guiWindow.getStartButton().addActionListener((event) -> {
            m_nrOfClients = guiWindow.getNrOfClients();
            m_nrOfServers = guiWindow.getNrOfQueues();
            m_maxSimulationTime = guiWindow.getSimulationInterval();
            m_minArrivalTime = guiWindow.getMinArrivalTime();
            m_maxArrivalTime = guiWindow.getMaxArrivalTime();
            m_minServiceTime = guiWindow.getMinServiceTime();
            m_maxServiceTime = guiWindow.getMaxServiceTime();
            m_nrOfServers = guiWindow.getNrOfServers(); //Vezi aici
            m_nrOfClients = guiWindow.getNrOfClients();

            scheduler = new Scheduler(m_nrOfServers, m_nrOfClients);
            scheduler.changeStrategy(selectionPolicy);
            lstTasks = generateNRandomTasks();
            aLogWindow = new LogWindow();
            thrd.start();
        });
    }

    private List<Task> generateNRandomTasks()
    {
        List<Task> tasks = new ArrayList<>();

        for(int i = 0; i < m_nrOfClients; i++)
        {
            int arrivalTimeForTask = (int)(Math.random() * (m_maxArrivalTime - m_minArrivalTime)) + m_minArrivalTime;
            int serviceTimeForTask = (int)(Math.random() * (m_maxServiceTime - m_minServiceTime)) + m_minServiceTime;
            Task t = new Task(i, arrivalTimeForTask, serviceTimeForTask);

            tasks.add(t);
        }

        return tasks;
    }

    public void logProgramState() throws IOException {
        String theLogMessage = new String();
        theLogMessage += "Time " + m_currentTime + "...\n";
        theLogMessage += "Waiting clients:";

        for(Task aTask : lstTasks)
        {
            theLogMessage += "(" + aTask.getID() + ", " + aTask.getArrivalTime() + ", " + aTask.getServiceTime() + ")";
        }

        theLogMessage += "\n\n";

        int serverIndex = 0;
        for(Server aServer : scheduler.getServers())
        {
            String serverState = new String();
            serverState = aServer.getServerStateLogData(m_currentTime);
            theLogMessage += "Queue " + serverIndex + " :" + serverState + "\n\n\n";

            serverIndex++;
        }
        aLogWindow.setCurrentLogData(theLogMessage);
        aBufferedWriter.write(theLogMessage);
    }

    public static void main(String[] args) throws IOException {
        SimulationManager simulation = new SimulationManager();
        thrd = new Thread(simulation);
    }

    public void dispatchTasksToScheduler() throws InterruptedException {
        ArrayList<Task> tasksToRemove = new ArrayList<>();
        for(int i = 0; i < lstTasks.size(); i++)
        {
            Task aTask = lstTasks.get(i);
            //If a task is found that has arrivalTime == currentTime
            if( aTask.getArrivalTime() == m_currentTime)
            {
                //Remember that we need to remove that task
                tasksToRemove.add(aTask);
                //Send task to scheduler to be dispatched
                scheduler.dispatchTask(aTask);
            }
        }
        //Delete tasks that were sent to be processed ??
        for(Task t: tasksToRemove)
        {
            lstTasks.remove(t);
        }
    }
    public void incrementCurrentTime() throws InterruptedException, IOException {

        for(Server aServer : scheduler.getServers())
        {
            aServer.decrementProcessingTime();
        }
        m_currentTime += 1;
        Thread.sleep(1000);
    }
    //Ceasul = m_currentTime si m_maxSimulationTime = limita
    public boolean didNotReachEndOfSimulation()
    {
        try {
            logProgramState();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        boolean tasksLeft = tasksLeftToProcess();
        boolean tasksAreBeingProcessed = tasksCurrentlyBeingProcessed();
        return (m_currentTime < m_maxSimulationTime) && (tasksLeft || tasksAreBeingProcessed);
    }

    public boolean tasksLeftToProcess()
    {
        return !lstTasks.isEmpty();
    }

    public boolean tasksCurrentlyBeingProcessed()
    {
        for(Server s : scheduler.getServers())
        {
            //If we find a server that isn't closed -
            if( !s.isClosed() ) // s.isClosed() == false means that it is processing a task
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {


        while(didNotReachEndOfSimulation())
        {
            try {
                //Dispatch a task to Scheduler that will decide
                //based on the strategy to which server to send it to
                dispatchTasksToScheduler();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Increment simulation time
            try {
                incrementCurrentTime();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        try {
            aBufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        exit(0);
    }
}
