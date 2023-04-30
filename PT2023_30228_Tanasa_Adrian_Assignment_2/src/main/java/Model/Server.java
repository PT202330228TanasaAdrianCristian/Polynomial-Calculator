package Model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable

{
    private BlockingQueue<Task> m_tasks;
    private AtomicInteger m_waitingPeriod;

    /*
    Server constructor
     */
    public Server(int maxNrOfTasks)
    {
        m_tasks = new ArrayBlockingQueue<>(maxNrOfTasks); //ArrayBlockingQueue!!!
        m_waitingPeriod = new AtomicInteger();
        m_waitingPeriod.set(0);
    }

    public void decrementProcessingTime()
    {
        Task aTask = m_tasks.peek();
        if(aTask != null) {
            aTask.setServiceTime(aTask.getServiceTime() - 1);
        }
    }
    public String getServerStateLogData(int currentTime)
    {
        if(isClosed())
        {
            return " closed";
        } else {
            String logString = new String();
            for(Task aTask : m_tasks)
            {
                logString += "(" + aTask.getID() + ", " + aTask.getArrivalTime()+ ", " + aTask.getServiceTime() + "),";
            }
            return logString;
        }
    }
    public boolean isClosed()
    {
        return getQueueSize() == 0;
    }
    /*
    Function that adds a new task to the BlockQueue m_tasks
     */
    public void addTask(Task newTask)
    {
        m_tasks.add(newTask);
        m_waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    public int getQueueSize()
    {
        return m_tasks.size();
    }

    public int getServiceTime()
    {
        return m_waitingPeriod.get();
    }

    public void subtractWaitingTimeFromServer(int serviceTime)
    {
        m_waitingPeriod.addAndGet(-serviceTime);
    }

    private Task getFirstTaskInServer()
    {
        return m_tasks.peek();
    }
    private void processTask(int serviceTime) throws InterruptedException {
        Thread.sleep(serviceTime * 1000);
    }

    private void removeTaskFromServer() throws InterruptedException {
        m_tasks.take();
    }
    /*
    int main() of the thread
     */
    public void run()
    {
        while(true)
        {
            Task firstTaskInArray = getFirstTaskInServer();
            if(firstTaskInArray != null)
            {
                int serviceTime = firstTaskInArray.getServiceTime();
                try {
                    processTask(serviceTime);
                    subtractWaitingTimeFromServer(serviceTime);
                    removeTaskFromServer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
