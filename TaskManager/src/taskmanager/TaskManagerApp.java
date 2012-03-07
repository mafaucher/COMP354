package taskmanager;

import taskmanager.controller.Controller;


public class TaskManagerApp 
{
    public static void main(String args[]) 
    {
       Controller con = new Controller();
       con.go();
    }
}
