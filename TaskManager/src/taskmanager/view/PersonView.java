package taskmanager.view;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.TableHeaderUI;
import taskmanager.model.*;

public class PersonView extends JPanel
{
    JTable tablePerson;
    String columnNames[];
    String rowData[][];
    
    PersonView()
    {
        this.setLayout(new GridLayout(1, 1));
        
        columnNames = new String[6];
        columnNames[0] = "identifier";
        columnNames[1] = "First Name";
        columnNames[2] = "Last Name";
        columnNames[3] = "Job Title";
        columnNames[4] = "Job Description";
        columnNames[5] = "Clearance";
        
        loadTable();
    }
    
    public void loadTable()
    {
        XMLParser xmlP = new XMLParser();
        java.util.List<Person> peopleData = xmlP.readPeople();
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
       
        
        tablePerson = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(tablePerson);
        tablePerson.setFillsViewportHeight(true);
        
        this.add(scrollPane);
    }
            
}
