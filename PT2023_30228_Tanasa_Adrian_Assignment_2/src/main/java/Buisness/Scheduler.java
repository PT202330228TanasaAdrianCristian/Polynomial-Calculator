package Buisness;

import Model.Task;
import Model.Server;
import java.util.ArrayList;
import java.util.List;

public class Scheduler
{

    private List<Server> m_servers;
    private int m_maxNrOfServers;
    private int m_maxTasksPerServers;
    private Strategy m_strategy;

    public Scheduler(int maxNrOfServers, int maxTasksPerServers)
    {
        m_maxNrOfServers = maxNrOfServers;
        m_maxTasksPerServers = maxTasksPerServers;
        m_servers = new ArrayList<Server>(maxNrOfServers);
        for(int i = 0; i < m_maxNrOfServers; i++)
        {
            Server aServer = new Server(m_maxTasksPerServers);
            m_servers.add(aServer);
            Thread aServerThread = new Thread(aServer);
            aServerThread.start();
        }
        //?
    }

    public void changeStrategy(SelectionPolicy policy)
    {
        switch(policy) {
            case SHORTEST_QUEUE: {
                m_strategy = new ShorthestQueueStrategy();
                break;
            }
            case SHORTEST_TIME: {
                m_strategy = new ShorthestTimeStrategy();
            }
        }
    }

    public void dispatchTask(Task taskToDispatch) throws InterruptedException {
        m_strategy.addTask(m_servers, taskToDispatch);
    }

    public List<Server> getServers()
    {
        return m_servers;
    }
}
