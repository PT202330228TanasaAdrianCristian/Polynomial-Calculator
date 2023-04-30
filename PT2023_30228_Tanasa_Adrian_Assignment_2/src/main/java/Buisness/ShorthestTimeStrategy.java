package Buisness;

import Model.Server;
import Model.Task;

import java.util.List;

public class ShorthestTimeStrategy implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task task) {
        int minServiceTime = servers.get(0).getServiceTime();
        int minServiceTimeIndex = 0;

        for(int i = 1; i < servers.size(); i++)
        {
            Server aServer = servers.get(i);
            if(aServer.getServiceTime() < minServiceTime)
            {
                minServiceTime = aServer.getServiceTime();
                minServiceTimeIndex = i;
            }
        }

        servers.get(minServiceTimeIndex).addTask(task);
    }
}
