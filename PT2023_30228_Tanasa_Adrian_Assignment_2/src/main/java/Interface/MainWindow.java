package Interface;

import javax.swing.*;

public class MainWindow extends JFrame {

    public JTextField m_nrOfClients;
    public JTextField m_nrOfQueues;
    public JTextField m_simulationInterval;
    public JTextField m_minArrivalTime;
    public JTextField m_maxArrivalTime;
    public JTextField m_minServiceTime;
    public JTextField m_maxServiceTime;

    public JLabel m_nrOfCliensLabel;
    public JLabel m_nrOfQueuesLabel;
    public JLabel m_simulationIntervalLabel;
    public JLabel m_minArrivalTimeLabel;
    public JLabel m_maxArrivalTimeLabel;
    public JLabel m_minServiceTimeLabel;
    public JLabel m_maxServiceTimeLabel;

    public JButton m_startAppButton;

    public JButton getStartButton() { return m_startAppButton;};
    public int getNrOfClients() { return Integer.parseInt(m_nrOfClients.getText());};
    public int getNrOfQueues() { return Integer.parseInt(m_nrOfQueues.getText()); };
    public int getSimulationInterval() { return Integer.parseInt(m_simulationInterval.getText());};
    public int getMinArrivalTime() { return Integer.parseInt(m_minArrivalTime.getText());};
    public int getMaxArrivalTime() { return Integer.parseInt(m_maxArrivalTime.getText());};
    public int getMinServiceTime() { return Integer.parseInt(m_minArrivalTime.getText());};
    public int getMaxServiceTime() { return Integer.parseInt(m_maxArrivalTime.getText());};



    public MainWindow()
    {
        this.setBounds(100,100,500,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Simulation");
        this.getContentPane().setLayout(null);
        int maxHeight = 20;
        int maxWidth = 100;
        int xPozStartForTextFields = 200;
        int xPozStartForLabels = 10;
        int widthOfLabels = xPozStartForTextFields - xPozStartForLabels;
        int heightOfLabels = 20;

        m_nrOfClients = new JTextField();
        m_nrOfClients.setBounds(xPozStartForTextFields,0, maxWidth, maxHeight);
        m_nrOfCliensLabel = new JLabel();
        m_nrOfCliensLabel.setText("Nr of clients");
        m_nrOfCliensLabel.setBounds(xPozStartForLabels,0, widthOfLabels, heightOfLabels);

        this.getContentPane().add(m_nrOfClients);
        this.getContentPane().add(m_nrOfCliensLabel);

        m_nrOfQueues = new JTextField();
        m_nrOfQueues.setBounds(xPozStartForTextFields, 50, maxWidth, maxHeight);
        m_nrOfQueuesLabel = new JLabel();
        m_nrOfQueuesLabel.setText("Nr of Queues");
        m_nrOfQueuesLabel.setBounds(xPozStartForLabels, 50, widthOfLabels, heightOfLabels);
        this.getContentPane().add(m_nrOfQueues);
        this.getContentPane().add(m_nrOfQueuesLabel);

        m_simulationInterval = new JTextField();
        m_simulationIntervalLabel = new JLabel();
        m_simulationInterval.setBounds(xPozStartForTextFields, 100, maxWidth, maxHeight);
        m_simulationIntervalLabel.setBounds(xPozStartForLabels, 100, widthOfLabels, heightOfLabels);
        m_simulationIntervalLabel.setText("Sim interval");
        this.getContentPane().add(m_simulationInterval);
        this.getContentPane().add(m_simulationIntervalLabel);

        m_minArrivalTime = new JTextField();
        m_minArrivalTimeLabel = new JLabel();
        m_minArrivalTimeLabel.setText("Min arrival time");
        m_minArrivalTime.setBounds(xPozStartForTextFields, 150, maxWidth, maxHeight);
        m_minArrivalTimeLabel.setBounds(xPozStartForLabels, 150, widthOfLabels, heightOfLabels);
        this.getContentPane().add(m_minArrivalTime);
        this.getContentPane().add(m_minArrivalTimeLabel);

        m_maxArrivalTime = new JTextField();
        m_maxArrivalTimeLabel = new JLabel();
        m_maxArrivalTimeLabel.setText("Max arrival time");
        m_maxArrivalTime.setBounds(xPozStartForTextFields, 200, maxWidth, maxHeight);
        m_maxArrivalTimeLabel.setBounds(xPozStartForLabels,  200, widthOfLabels, heightOfLabels);
        this.getContentPane().add(m_maxArrivalTime);
        this.getContentPane().add(m_maxArrivalTimeLabel);

        m_minServiceTime = new JTextField();
        m_minServiceTimeLabel = new JLabel();
        m_minServiceTimeLabel.setText("Min service time");
        m_minServiceTime.setBounds(xPozStartForTextFields, 250, maxWidth, maxHeight);
        m_minServiceTimeLabel.setBounds(xPozStartForLabels, 250, widthOfLabels, heightOfLabels);
        this.getContentPane().add(m_minServiceTime);
        this.getContentPane().add(m_minServiceTimeLabel);

        m_maxServiceTime = new JTextField();
        m_maxServiceTimeLabel = new JLabel();
        m_maxServiceTimeLabel.setText("Max service time");
        m_maxServiceTime.setBounds(xPozStartForTextFields, 300, maxWidth, maxHeight);
        m_maxServiceTimeLabel.setBounds(xPozStartForLabels, 300, widthOfLabels, heightOfLabels);
        this.getContentPane().add(m_maxServiceTime);
        this.getContentPane().add(m_maxServiceTimeLabel);

        m_startAppButton = new JButton();
        m_startAppButton.setBounds((int)(this.getContentPane().getWidth() / 2), 400, 100, 100);
        m_startAppButton.setText("Start sim");

        this.getContentPane().add(m_startAppButton);

        this.setVisible(true);


    }

    public int getNrOfServers()
    {
        return Integer.parseInt(m_nrOfQueues.getText());
    }
}
