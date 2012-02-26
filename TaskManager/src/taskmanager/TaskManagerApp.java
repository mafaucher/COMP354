/*
 * TaskManagerApp.java
 */

package taskmanager;


import taskmanager.controller.Controller;
//import taskmanager.view.MainWindow;


/**
 * The main class of the application.
 */
public class TaskManagerApp {
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       Controller con = new Controller();
       con.initialize();
    }
}
