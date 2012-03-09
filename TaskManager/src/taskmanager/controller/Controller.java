package taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import taskmanager.model.MainModel;
import taskmanager.model.TextOutputer;
import taskmanager.view.MainWindow;


public class Controller 
{
    MainModel mm;
    MainWindow mw;
    
    public void go()
    {
        mm = new MainModel();
        mw = new MainWindow(mm);
        
        mw.btPrintPeople.addActionListener(new PrintPeopleListener());
        mw.panelTasks.btRemove.addActionListener(new RemoveTaskListener());
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
            //reload people table
            mw.updatePeopleTable(mm.getPeopleData());
            
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
            mm.writePeopleTxt();
        }
    }
    
    class RemoveTaskListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int selectedRows[] = mw.panelTasks.tableTasks.getSelectedRows();
            
            for (int i = 0; i < selectedRows.length; i++)
                for (int j = 0; j < mm.getTaskData().size(); j++)
                {
                    String tableId = (String)mw.panelTasks.tableTasks.getValueAt(selectedRows[i], 0);
                    if (tableId.equals(mm.getTaskData().get(j).getIdentifier()))
                    {
                        mm.getTaskData().remove(j);
                        break;
                    }
                }
            
            mw.panelTasks.loadTable(mm.getTaskData());
            mm.updateXML();
            mw.panelPeople.loadTable(mm.getPeopleData());
        }
    }
    
    class AddTaskListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
        }
    }
}
