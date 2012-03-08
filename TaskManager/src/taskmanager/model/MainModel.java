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
    /*
    public String nextAvailableId()
    {
        int id = 0;
        for (int i=0; i<taskData.size())
    }
    */
}