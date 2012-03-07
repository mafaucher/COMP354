package taskmanager.view;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import taskmanager.model.Person;
import taskmanager.model.XMLParser;

public class PersonView extends JPanel
{
    JTable tablePerson;
    String columnNames[];
    Object rowData[][];
    
    PersonView()
    {
        //table will be the only other visual within this panel
        this.setLayout(new GridLayout(1, 1));
        
        //here be column names
        columnNames = new String[6];
        columnNames[0] = "identifier";
        columnNames[1] = "First Name";
        columnNames[2] = "Last Name";
        columnNames[3] = "Job Title";
        columnNames[4] = "Job Description";
        columnNames[5] = "Clearance";
        
        loadTable();
    }
    
    
    //this should also be called when tabs are changed 
    //in order to refresh changes
    public void loadTable()
    {
        //we need a parser to read people.xml
        XMLParser xmlP = new XMLParser();
        java.util.List<Person> peopleData = xmlP.readPeople();
        
        //initialize the data array for the table
        rowData = new String[peopleData.size()][];
        
        for (int i = 0; i < rowData.length; i++)
        {
            rowData[i] = new String[6];
            
            rowData[i][0] = peopleData.get(i).getIdentifier();
            rowData[i][1] = peopleData.get(i).getFName();
            rowData[i][2] = peopleData.get(i).getLName();
            rowData[i][3] = peopleData.get(i).getJobTitle();
            rowData[i][4] = peopleData.get(i).getJobDescription();
            rowData[i][5] = peopleData.get(i).getClearance();
        }
        peopleData = null; //to avoid security related probs
       
        
        //create the table here with the data
        tablePerson = new JTable(rowData, columnNames);
        
        //the following makes the table nicer
        JScrollPane scrollPane = new JScrollPane(tablePerson);
        tablePerson.setFillsViewportHeight(true);
        
        //add the table to the panel
        this.add(scrollPane);
    }
            
}
