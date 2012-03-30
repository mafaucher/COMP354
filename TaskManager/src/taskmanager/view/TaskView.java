package taskmanager.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import taskmanager.model.Task;

public class TaskView extends JPanel 
{
    public JTable tableTasks;
    public JButton btAdd;
    public JButton btRemove;
    public JPanel southPanel;
    String columnNames[];
    String rowData[][];
    
    TaskView(List<Task> taskData)
    {
        //table will be the only visual within this panel
        this.setLayout(new BorderLayout());
        
        btAdd = new JButton();
        btRemove = new JButton();
        btAdd.setText("Add Task");
        btRemove.setText("Remove Task");
        
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 2));
        
        southPanel.add(btAdd);
        southPanel.add(btRemove);
        
        //here be column names
        columnNames = new String[9];
        columnNames[0] = "identifier";
        columnNames[1] = "title";
        columnNames[2] = "description";
        columnNames[3] = "duration";
        columnNames[4] = "deliverable";
        columnNames[5] = "start date";
        columnNames[6] = "deadline";
        columnNames[7] = "people assigned";
        columnNames[8] = "completion";
        
        loadTable(taskData);
    }
    
    
    public void loadTable(List<Task> taskData)
    {
        this.removeAll();
        
        //initialize the data array for the table
        rowData = new String[taskData.size()][];
        
        for (int i = 0; i < rowData.length; i++)
        {
            rowData[i] = new String[9];
            
            rowData[i][0] = taskData.get(i).getIdentifier();
            rowData[i][1] = taskData.get(i).getTitle();
            rowData[i][2] = taskData.get(i).getDescription();
            rowData[i][3] = taskData.get(i).getDuration();
            rowData[i][4] = taskData.get(i).getDeliverable();
            rowData[i][5] = taskData.get(i).getStartDate();
            rowData[i][6] = taskData.get(i).getDeadline();
            rowData[i][7] = taskData.get(i).getPeopleassignedAsString();
            rowData[i][8] = taskData.get(i).getCompletion();
        }
       
        //create the table here with the data
        tableTasks = new JTable(rowData, columnNames);
        
        //the following makes the table nicer
        JScrollPane scrollPane = new JScrollPane(tableTasks);
        tableTasks.setFillsViewportHeight(true);
        
        //add the table to the panel
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        
    }
}
