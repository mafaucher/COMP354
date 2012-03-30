package taskmanager.view;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import taskmanager.model.MainModel;
import taskmanager.model.Person;

public class PersonView extends JPanel
{
    MainModel mm;
    JTable tablePerson;
    String columnNames[];
    Object rowData[][];
    
    PersonView(MainModel mm)
    {
        //table will be the only visual within this panel
        this.setLayout(new GridLayout(1, 1));
        
        //here be column names
        columnNames = new String[8];
        columnNames[0] = "identifier";
        columnNames[1] = "First Name";
        columnNames[2] = "Last Name";
        columnNames[3] = "Job Title";
        columnNames[4] = "Job Description";
        columnNames[5] = "Clearance";
        columnNames[6] = "Total hours";
        columnNames[7] = "Project list";
        
        this.mm = mm;
        
        loadTable(mm.getPeopleData());
    }
    
    
    //this should also be called when tabs are changed 
    //in order to refresh changes
    public void loadTable(List<Person> peopleData)
    {
        //clears the content pane
        this.removeAll();
        
        //initialize the data array for the table
        rowData = new String[peopleData.size()][];
        
        for (int i = 0; i < rowData.length; i++)
        {
            rowData[i] = new String[8];
            
            rowData[i][0] = peopleData.get(i).getIdentifier();
            rowData[i][1] = peopleData.get(i).getFName();
            rowData[i][2] = peopleData.get(i).getLName();
            rowData[i][3] = peopleData.get(i).getJobTitle();
            rowData[i][4] = peopleData.get(i).getJobDescription();
            rowData[i][5] = peopleData.get(i).getClearance();
            rowData[i][6] = String.valueOf(mm.getTotalHoursOnProjects(peopleData.get(i).getIdentifier()));
            rowData[i][7] = String.valueOf(mm.getListOfProjects(peopleData.get(i).getIdentifier()));
        }
        peopleData = null; //to avoid security related probs
       
        
        //create the table here with the data
        tablePerson = new JTable(rowData, columnNames);
        
        //the following makes the table nicer
        JScrollPane scrollPane = new JScrollPane(tablePerson);
        tablePerson.setFillsViewportHeight(true);
        
        //add the table to the panel
        this.add(scrollPane);
        
        //make it read-only
        tablePerson.setEnabled(false);
    }
            
}
