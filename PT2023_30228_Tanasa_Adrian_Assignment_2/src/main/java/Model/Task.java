package Model;

public class Task

{
    private int m_ID;
    private int m_arrivalTime;
    private int m_serviceTime;

    public int getServiceTime() {
        return m_serviceTime;
    };
    public int getArrivalTime() { return m_arrivalTime;};
    public int getID() { return m_ID;};

    public void setServiceTime(int serviceTime) { m_serviceTime = serviceTime;};

    public Task(int id, int arrivalTime, int serviceTime) {
        m_ID = id;
        m_arrivalTime = arrivalTime;
        m_serviceTime = serviceTime;
    }
}
