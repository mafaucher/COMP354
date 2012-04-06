package taskmanager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import taskmanager.model.MainModel;
import taskmanager.model.Task;
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
        mw.panelTasks.btAdd.addActionListener(new AddTaskListener());
        mw.panelTasks.btRemove.addActionListener(new RemoveTaskListener());
        mw.panelTasks.tableTasks.getModel().addTableModelListener(new TaskTableListener());

        mw.setVisible(true);
    }

    public class TaskTableListener implements TableModelListener
    {
        public void tableChanged(TableModelEvent e) {
            int column = e.getColumn();
            int row = e.getFirstRow();
            TableModel model = (TableModel)e.getSource();
            String data = model.getValueAt(row, column).toString();
            String oldData = "";

            // Get task that corresponds to this row
            Task task = mm.findTask(String.valueOf(model.getValueAt(row, 0)));

            boolean revert = false;
            switch (column) {
                case 0:
                    // Don't accept any change
                    mw.updateTaskTable(mm.getTaskData());
                    mw.repaint();
                    return;
                case 1:
                    oldData = task.getTitle();
                    revert = !task.setTitle(data);
                    break;
                case 2:
                    oldData = task.getDescription();
                    revert = !task.setDescription(data);
                    break;
                case 3:
                    oldData = task.getDuration();
                    revert = !task.setDuration(data);
                    break;
                case 4:
                    oldData = task.getDeliverable();
                    revert = !task.setDeliverable(data);
                    break;
                case 5:
                    oldData = task.getStartDate();
                    revert = !task.setStartDate(data);
                    break;
                case 6:
                    oldData = task.getDeadline();
                    revert = !task.setDeadline(data.toString());
                    break;
                case 7:
                    // Modify People assigned & cell data to a properly formated string
                    mm.assignStringOfID(task, data);
                    oldData = task.getPeopleassignedAsString();
                    revert = !data.equals(oldData);
                    break;
                case 8:
                    oldData = task.getCompletion();
                    revert = !task.setCompletion(data.toString());
                    break;
                case 9:
                    oldData = task.getParentDependencyId();
                    revert = !task.setParentDependencyId(data);
                    break;
                case 10:
                    oldData = task.getParent();
                    revert = !task.setParent(data);
                    break;
            }
            mm.updateXML();
            //reload people table
            mw.updatePeopleTable(mm.getPeopleData());
            mw.updateGanttList(mm.getTaskData());
            
            mw.taskTree.reBuildTree();
            
            // Revert invalid changes
            if (revert)
            {
                model.setValueAt(oldData, row, column);
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
            mw.panelPeople.loadTable(mm.getPeopleData());
            mw.panelGanttChart.loadList(mm.getTaskData());
            mm.updateXML();
            mw.repaint();
        }
    }
    
    class AddTaskListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Task t = new Task(mm.nextAvailableId());
            
            /*
            Task t = new Task(  mm.nextAvailableId(),
                                "fake title",
                                "fake description",
                                "100",
                                "fake deliverable",
                                "Feb 19, 2011",
                                "Feb 20, 2012",
                                "0",
                                "0",
                                "");
            */
            
            String fdf="";
            mm.getTaskData().add(t);
//<<<<<<< .mine
            String fd45f="";
            //mw.panelTasks.loadTable(mm.getTaskData());  ?
            
//=======
            
//>>>>>>> .r71
            mw.updatePeopleTable(mm.getPeopleData());
            mw.updateTaskTable(mm.getTaskData());
            mw.updateGanttList(mm.getTaskData());
            mm.updateXML();
            
            mw.panelTasks.tableTasks.getModel().removeTableModelListener(null);
            mw.panelTasks.tableTasks.getModel().addTableModelListener(new TaskTableListener());
            
            mw.taskTree.reBuildTree();
            
            mw.repaint();
        }
    }
}
