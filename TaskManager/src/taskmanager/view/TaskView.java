package taskmanager.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import taskmanager.model.Task;
import taskmanager.model.XMLParser;

public class TaskView  extends JPanel 
{
    JTable tablePerson;
    String columnNames[];
    String rowData[][];
    
    TaskView()
    {
        //table will be the only other visual within this panel
        this.setLayout(new GridLayout(1, 1));
        
        //here be column names
        columnNames = new String[8];
        columnNames[0] = "identifier";
        columnNames[1] = "title";
        columnNames[2] = "description";
        columnNames[3] = "duration";
        columnNames[4] = "deliverable";
        columnNames[5] = "deadline";
        columnNames[6] = "people assigned";
        columnNames[7] = "completion";
        
        loadTable();
    }
    
    
    //this should also be called when tabs are changed 
    //in order to refresh changes
    public void loadTable()
    {
        //parser to read tasks.xml
        XMLParser xmlP = new XMLParser();
        java.util.List<Task> taskData = xmlP.readTasks();
        
        //initialize the data array for the table
        rowData = new String[taskData.size()][];
        
        for (int i = 0; i < rowData.length; i++)
        {
            rowData[i] = new String[8];
            
            rowData[i][0] = taskData.get(i).getIdentifier();
            rowData[i][1] = taskData.get(i).getTitle();
            rowData[i][2] = taskData.get(i).getDescription();
            rowData[i][3] = taskData.get(i).getDuration();
            rowData[i][4] = taskData.get(i).getDelivarable();
            rowData[i][5] = taskData.get(i).getDeadline();
            
            
            //Use a string builder to be efficient with Memory
            StringBuilder peopleAssigned = new StringBuilder();
            ArrayList<String> alPID = taskData.get(i).getPeopleassigned();
            
            //work here to gather people assigned to a project
            for (int j = 0; j < alPID.size(); j++)
            {
                peopleAssigned.append(alPID.get(j));
                
                if (j != alPID.size() - 1)
                    peopleAssigned.append(", ");
            }
            
            rowData[i][6] = peopleAssigned.toString();
            rowData[i][7] = taskData.get(i).getCompletion();
        }
        taskData = null; //to avoid security related probs
       
        //create the table here with the data
        tablePerson = new JTable(rowData, columnNames);
        
        //the following makes the table nicer
        JScrollPane scrollPane = new JScrollPane(tablePerson);
        tablePerson.setFillsViewportHeight(true);
        
        //add the table to the panel
        this.add(scrollPane);
    }
}
