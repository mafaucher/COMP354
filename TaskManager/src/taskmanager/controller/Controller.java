/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import taskmanager.view.MainWindow;
import taskmanager.view.MenuPanel;
import taskmanager.view.TaskView;

/**
 *
 * @author Jonathan
 */
public class Controller 
{
    MainWindow mw;
    MenuPanel mp;
    
    public void initialize(/*MainWindow mwin*/)
    {
        mp = new MenuPanel();
        mp.btTasks.addActionListener(new TaskListener());
        mw = new MainWindow();
        mw.setLayout(new BorderLayout());
        mw.getContentPane().removeAll();
        mw.getContentPane().add(mp, BorderLayout.EAST);
        mw.validate();
        mw.repaint();
        mw.setVisible(true);
    }
    
    class TaskListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            
            
        }
    }
}
