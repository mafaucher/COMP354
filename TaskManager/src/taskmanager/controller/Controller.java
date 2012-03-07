package taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import taskmanager.view.MainWindow;


public class Controller 
{
    MainWindow mw;
    
    public void go()
    {
        mw = new MainWindow();
        
        mw.btPrintTasks.addActionListener(new PrintTaskListener());
        mw.btPrintPeople.addActionListener(new PrintPeopleListener());
        
        
        mw.setVisible(true);
    }
    
    
    class PrintPeopleListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("Should print people");
        }
    }
    
    class PrintTaskListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Should print tasks");
        }
    }
}
