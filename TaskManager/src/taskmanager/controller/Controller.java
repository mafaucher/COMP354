
package taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import taskmanager.view.MainWindow;


public class Controller 
{
    MainWindow mw;
    
    
    public void initialize()
    {
        mw = new MainWindow();
        mw.setVisible(true);
    }
    
    class TaskListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            
            
        }
    }
}
