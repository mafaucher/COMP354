/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
        
        if (returnString.contains(","))
            returnString = returnString.substring(0, returnString.lastIndexOf(','));
        
        return returnString;
    }
    
    public void writePeopleTxt()
    {
        TextOutputer.printPeopleTXT(xmlP);
    }
        
    public String nextAvailableId()
    {
        int id = 0;
        boolean unique = false;

        while(!unique)
        {
            unique = true;
            for (Task t : taskData)
            {
                // ID already exists, try next one
                if (Integer.parseInt(t.getIdentifier()) == id)
                {
                    id++;
                    unique = false;
                    break;
                }
            }
        }
        return String.valueOf(id);
    }

    public Task findTask(String taskID)
    {
        // Find the right task
        for (Task t : taskData)
        {
            if (t.getIdentifier().equals(taskID))
                return t;
        }
        return null;
    }
    
    public void assignStringOfID(Task task, String data)
    {
        StringTokenizer st = new StringTokenizer(data, ",");
        ArrayList<String> peopleassigned = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            String id = st.nextToken();
            id = id.trim();

            // Only add IDs that correspond to a person
            for (Person p : peopleData)
            {
                if (p.getIdentifier().equals(id))
                {
                    peopleassigned.add(id);
                    break;
                }
            }
        }
        task.setPeopleassigned(peopleassigned);
    }
     
}