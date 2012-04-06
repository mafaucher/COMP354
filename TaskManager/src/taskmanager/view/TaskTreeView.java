/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager.view;

//import java.awt.GridLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.GridLayout;
//import javax.swing.JTable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;
import taskmanager.model.MainModel;
import taskmanager.model.Person;
import taskmanager.model.Task;

/**
 *
 * @author Ladmin
 */
public class TaskTreeView extends JPanel 
{
    private     MainModel mm;
    private     JTree taskTree;
    private     DefaultMutableTreeNode m_rootNode; 
     private    DefaultTreeModel m_model; 
    private     JEditorPane htmlPane;
    private     TaskView taskViewPane;
    
    private     JScrollPane htmlView;
    private     JSplitPane splitPane;
    
    public TaskTreeView(final MainModel _mm_)
    {
        this.mm = _mm_;
        this.setLayout(new GridLayout(1, 1));  // 1, 1 ?
        
        TreeNode m_rootNode = buildDummyTree(); 
        m_model = new DefaultTreeModel(m_rootNode);  
        taskTree = new JTree(m_model); 
        
        taskTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);        
        

        taskTree.addTreeSelectionListener(new TreeSelectionListener() {
              public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                String [] parts = node.toString().split(" ");    
                String fddf="";
                String dfgg3="";
                System.out.println("You selected " + node + " title: " + mm.findTask(parts[1]).getTitle() );
                taskViewPane = new TaskView(mm.getTaskData(parts[1]));
                htmlView = new JScrollPane(taskViewPane);
                splitPane.setBottomComponent(htmlView);
              }
            });        
        
        JScrollPane treeViewScrollPane = new JScrollPane(taskTree);
        
        
        
        taskViewPane = new TaskView(mm.getTaskData("1"));
        htmlView = new JScrollPane(taskViewPane);
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(taskTree);
        splitPane.setBottomComponent(htmlView);
 
        Dimension minimumSize = new Dimension(300, 50);
        htmlView.setMinimumSize(minimumSize);
        taskTree.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(300);
        splitPane.setPreferredSize(new Dimension(100, 300));
 
        add(splitPane);
    }
    

    
    
            public MainModel getMainModel(){
            return mm;
        }
            
            
   /** 
     * This method takes the node string and 
     * traverses the tree till it finds the node 
     * matching the string. If the match is found  
     * the node is returned else null is returned 
     *  
     * @param nodeStr node string to search for 
     * @return tree node  
     */ 
    public DefaultMutableTreeNode searchNode(String nodeStr) 
    { 
        DefaultMutableTreeNode node = null; 
         
        //Get the enumeration 
        Enumeration enum3 = m_rootNode.breadthFirstEnumeration(); 
         
        //iterate through the enumeration 
        while(enum3.hasMoreElements()) 
        { 
            //get the node 
            node = (DefaultMutableTreeNode)enum3.nextElement(); 
             
            //match the string with the user-object of the node 
            if(nodeStr.equals(node.getUserObject().toString())) 
            { 
                //tree node with string found 
                return node;                          
            } 
        } 
         
        //tree node with string node found return null 
        return null; 
    }             
    
    private static Comparator<Task> COMPARATOR = new Comparator<Task>()
	    {
	    // This is where the sorting happens.
	        public int compare(Task o1, Task o2)
	        {
                    int i1 = Integer.parseInt(o1.getIdentifier());
                    int i2 = Integer.parseInt(o2.getIdentifier());
	            return i1 - i2;
	        }
    };    
    
    public TreeNode buildDummyTree() {
        m_rootNode = new DefaultMutableTreeNode("Task Tree");  
        
        List<Task> taskData = mm.getTaskData();
        String fdd="";
        //Collections.sort(taskData, COMPARATOR);
        String fdfd="";
        //String[taskData.size()] strArray = new String[taskData.size()];

        ArrayList<Task> notFoundTasks = new ArrayList<Task>();
        
        for(Task t : taskData){
            System.out.println("I am task: "+t.getIdentifier());
            
              if(t.getParent()== null){
                  System.out.println("I am a node!");
                  //taskTree.
                  
                  DefaultMutableTreeNode child = new DefaultMutableTreeNode("Task "+ t.getIdentifier());
                  m_rootNode.add(child);
                  
                      for(Task lt : notFoundTasks){
                          if(lt.getParent().equals(t.getIdentifier())){
                              DefaultMutableTreeNode parentNode2 = searchNode("Task "+t.getIdentifier());
                              DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("Task "+ lt.getIdentifier());
                              parentNode2.add(child2);
                          }
                      }
                      
                      if(t.getIdentifier().equals("3"))
                      {
                          String fdf="";
                      }                  
                  
                  //_root_.add(child);
              }else {
                  System.out.println("I am a child and my parent is: "+t.getParent());
                  if(t.getIdentifier().equals("10"))
                  {
                    String fdf="";
                  }
                  if(searchNode("Task "+t.getParent())!=null){
                      
                      
                      
                      String dffdfd="";
                      DefaultMutableTreeNode parentNode = searchNode("Task "+t.getParent());
                      

                      
                      DefaultMutableTreeNode child = new DefaultMutableTreeNode("Task "+ t.getIdentifier());
                      String gffgf4="";
                      parentNode.add(child);
                      
                      
                  }else{

                      notFoundTasks.add(t);
                  }
                  
              }
        }
                      
        visitAllNodes(m_rootNode, notFoundTasks);              
        
        return m_rootNode;  
    }
    
public void visitAllNodes(TreeNode node, ArrayList<Task> _notFoundTasks_) {
    // node is visited exactly once
    //process(node);

    if (node.getChildCount() >= 0) {
        for (Enumeration e=node.children(); e.hasMoreElements(); ) {
            TreeNode n = (TreeNode)e.nextElement();
            String temp = n.toString();
            String [] temp_parts = temp.split(" ");
            String fdffdfd43="";
                     
                      for(Task lt : _notFoundTasks_){
                          if((lt.getParent().equals(temp_parts[1]) && (!lt.getIdentifier().equals(temp_parts[1])))){
                              DefaultMutableTreeNode parentNode2 = searchNode("Task "+temp_parts[1]);
                              DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("Task "+ lt.getIdentifier());
                              parentNode2.add(child2);
                          }
                      } 
                      
            
            String fdfd="";
            visitAllNodes(n, _notFoundTasks_);
        }
    }
}    
    
    public void reBuildTree(){
        
        this.removeAll();
        
this.setLayout(new GridLayout(1, 1));  // 1, 1 ?
        //Create the nodes.
        //DefaultMutableTreeNode root = new DefaultMutableTreeNode("Task Tree");
        
        TreeNode m_rootNode = buildDummyTree(); 
        m_model = new DefaultTreeModel(m_rootNode);  
        taskTree = new JTree(m_model); 
        
        //TreeNode  m_rootNode = new DefaultMutableTreeNode("Task Tree");     
        //createNodes(m_rootNode);
        
         //Create a tree that allows one selection at a time.
        //taskTree = new JTree(m_rootNode);
        taskTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);        
        

        taskTree.addTreeSelectionListener(new TreeSelectionListener() {
              public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                String [] parts = node.toString().split(" ");    
                String fddf="";
                //Task tempTask = mm.findTask(m.group(1));
                String dfgg3="";
                System.out.println("You selected " + node + " title: " + mm.findTask(parts[1]).getTitle() );
                //taskViewPane.getRowData().
                //taskViewPane.loadTable(mm.getTaskData(parts[1]));
                taskViewPane = new TaskView(mm.getTaskData(parts[1]));
                htmlView = new JScrollPane(taskViewPane);
                splitPane.setBottomComponent(htmlView);
                //htmlView.paint(null);
                //splitPane.p();
                //splitPane.setBottomComponent(htmlView);
              }
            });        
        
        //Create the scroll pane and add the tree to it.
        JScrollPane treeViewScrollPane = new JScrollPane(taskTree);
                //Create the HTML viewing pane.
//        htmlPane = new JEditorPane();
//        htmlPane.setEditable(false);
        //initHelp();
//        JScrollPane htmlView = new JScrollPane(htmlPane);
        
        
        
        taskViewPane = new TaskView(mm.getTaskData("1"));
        htmlView = new JScrollPane(taskViewPane);
        //htmlView.updateUI();
        //Add the scroll panes to a split pane.
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(taskTree);
        splitPane.setBottomComponent(htmlView);
 
        Dimension minimumSize = new Dimension(300, 50);
        htmlView.setMinimumSize(minimumSize);
        taskTree.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(300);
        splitPane.setPreferredSize(new Dimension(100, 300));
 
        //Add the split pane to this panel.
        add(splitPane);        
        
    }
    
}
