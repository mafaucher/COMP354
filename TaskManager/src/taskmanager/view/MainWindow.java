package taskmanager.view;

import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame {

    private JPanel panelTasks;
    private JPanel panelPeople;
    private JTabbedPane jTabbedPane1;
    
    public MainWindow() {
        initComponents();
        
    }
                  
    private void initComponents() 
    {
        this.setSize(800, 600);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jTabbedPane1 = new JTabbedPane();
        panelTasks = new TaskView();
        panelPeople = new PersonView();
        
        jTabbedPane1.addTab("Tasks", panelTasks);    
        jTabbedPane1.addTab("People", panelPeople);  
        
        //this is for the main window
        this.setLayout(new GridLayout(1, 1));
       
        this.add(jTabbedPane1);
    }                    
}
