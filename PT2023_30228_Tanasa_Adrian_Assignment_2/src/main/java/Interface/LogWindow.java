package Interface;

import javax.swing.*;

public class LogWindow extends JFrame {
    private JTextPane currentLogData;

    public LogWindow()
    {
        this.setBounds(300,100,535,600 );
        this.setTitle("The logs");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        currentLogData = new JTextPane();

        this.getContentPane().add(currentLogData);
        currentLogData.setBounds(10,40,500,500);

        this.setVisible(true);
    }

    public void setCurrentLogData(String data)
    {
        currentLogData.setText(data);
    }
}
