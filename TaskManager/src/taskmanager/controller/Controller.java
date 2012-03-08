package taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import taskmanager.model.MainModel;
import taskmanager.view.MainWindow;
import taskmanager.view.TaskView;


public class Controller 
{
    MainModel mm;
    MainWindow mw;
    
    public void go()
    {
        mm = new MainModel();
        mw = new MainWindow(mm);
        
        mw.btPrintTasks.addActionListener(new PrintTaskListener());
        mw.btPrintPeople.addActionListener(new PrintPeopleListener());

        mw.panelTasks.tableTasks.getModel().addTableModelListener(new TaskTableListener());


        mw.setVisible(true);
    }

    // TODO: check for valid input; accept employee list
    class TaskTableListener implements TableModelListener
    {
        public void tableChanged(TableModelEvent e) {
            int column = e.getColumn();
            int row = e.getFirstRow();
            TableModel model = (TableModel)e.getSource();
            String data = model.getValueAt(row, column).toString();
            String oldData;

            boolean revert = false;
            switch (column) {
                case 0:
                    oldData = mm.getTaskData().get(row).getIdentifier();
                    if (!data.equals(oldData))
                    {
                        data = oldData;
                        revert = true;
                    }
                    break;
                case 1:
                    mm.getTaskData().get(row).setTitle(data);
                    break;
                case 2:
                    mm.getTaskData().get(row).setDescription(data);
                    break;
                case 3:
                    mm.getTaskData().get(row).setDuration(data);
                    break;
                case 4:
                    mm.getTaskData().get(row).setDeliverable(data);
                    break;
                case 5:
                    mm.getTaskData().get(row).setDeadline(data.toString());
                    break;
                case 6:
                    oldData = mm.getTaskData().get(row).getPeopleassignedAsString();
                    if (!data.equals(oldData))
                    {
                        data = oldData;
                        revert = true;
                    }
                    break;
                case 7:
                    mm.getTaskData().get(row).setCompletion(data.toString());
                    break;
            }
            mm.updateXML();

            // Revert invalid changes
            if (revert)
            {
                model.setValueAt(data, row, column);
                mw.panelTasks.tableTasks.setModel(model);
            }
        }
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
