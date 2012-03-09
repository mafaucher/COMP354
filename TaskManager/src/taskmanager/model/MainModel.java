/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager.model;

import java.util.List;

/**
 *
 * @author ma_fauch
 */
public class MainModel
{
    private XMLParser xmlP;
    private List<Person> peopleData;
    private List<Task> taskData;

    public MainModel()
    {
        xmlP = new XMLParser();
        peopleData = xmlP.readPeople();
        taskData = xmlP.readTasks();
    }

    public void updateXML()
    {
        xmlP.writePeople(peopleData);
        xmlP.writeTasks(taskData);
    }

    public List<Task> getTaskData()
    {
        return taskData;
    }

    public List<Person> getPeopleData()
    {
        return peopleData;
    }
    
    public int getTotalHoursOnProjects(String personID)
    {
        int hours = 0;
        
        List<Task> tasksList = xmlP.readTasks();
        
        for (Task t : tasksList)
        {
            for (String personAssigned : t.getPeopleassigned())
            {
                if (personID.equals(personAssigned))
                {
                    hours += Integer.parseInt(t.getDuration()) / t.getPeopleassigned().size();
                    break;
                }
            }
        }
        
        return hours;
    }
    
    
    public String getListOfProjects(String personID)
    {
        StringBuilder list = new StringBuilder();
        
        List<Task> tasksList = xmlP.readTasks();
        
        for (Task t : tasksList)
        {
            for (String personAssigned : t.getPeopleassigned())
            {
                if (personID.equals(personAssigned))
                {
                    list.append(t.getIdentifier());
                    list.append(", ");
                    break;
                }
            }
        }
        
        String returnString = list.toString();
        
        returnString = returnString.substring(0, returnString.lastIndexOf(','));
        
        return returnString;
    }
        
    /*
    public String nextAvailableId()
    {
        int id = 0;
        for (int i=0; i<taskData.size())
    }
    */
}