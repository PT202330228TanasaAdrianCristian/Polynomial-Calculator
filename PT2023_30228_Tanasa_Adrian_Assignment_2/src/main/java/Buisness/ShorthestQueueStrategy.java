package Buisness;

import Model.Server;
import Model.Task;

import java.util.List;

public class ShorthestQueueStrategy implements Strategy
{
    public void addTask(List<Server> servers, Task task) throws InterruptedException {
        int minQueueSize = servers.get(0).getQueueSize();
        int minQueueSizeIndex = 0;

        for(int i = 1; i < servers.size() ; i++)
        {
            Server aServer = servers.get(i);
            if(aServer.getQueueSize() < minQueueSize)
            {
                minQueueSize = aServer.getQueueSize();
                minQueueSizeIndex = i;
            }
        }

        servers.get(minQueueSizeIndex).addTask(task);
    }
}
