package taskmanager.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import taskmanager.model.*;

public class MainWindow extends JFrame 
{
    public static final int INI_WIDTH = 800;
    public static final int INI_HEIGHT = 600;
    
    public TaskView panelTasks;
    public PersonView panelPeople;
    
    public JTabbedPane jTabbedPane1;
    public JButton btPrintPeople;
    
    public MainWindow(MainModel mm)
    {
        setSize(INI_WIDTH, INI_HEIGHT);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Task Manager");
        
        //Get dimensions to center the application
        Toolkit toolkit =  Toolkit.getDefaultToolkit ();
        Dimension dim = toolkit.getScreenSize();
        setLocation((dim.width - INI_WIDTH) / 2, (dim.height - INI_HEIGHT) / 2);
        
        jTabbedPane1 = new JTabbedPane();
        
        panelTasks = new TaskView(mm.getTaskData());
        panelPeople = new PersonView(mm);

        btPrintPeople = new JButton();
        btPrintPeople.setText("Print people.txt");
        add(btPrintPeople, BorderLayout.SOUTH);
        
        jTabbedPane1.addTab("Tasks", panelTasks);
        jTabbedPane1.addTab("People", panelPeople);
 
        add(jTabbedPane1, BorderLayout.NORTH);
    }
    
    public void updatePeopleTable(List<Person> pList)
    {
        panelPeople.loadTable(pList);
    }

    public void updateTaskTable(List<Task> tList)
    {
        panelTasks.loadTable(tList);
    }
}
