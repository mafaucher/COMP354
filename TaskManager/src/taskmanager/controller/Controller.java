/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import taskmanager.view.MainWindow;

/**
 *
 * @author Jonathan
 */
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
