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
        //table will be the only other visual within this panel
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
    
    private void createNodes(TreeNode  _root_) {
                DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;
        DefaultMutableTreeNode book1 = null;
        DefaultMutableTreeNode book2 = null;
        DefaultMutableTreeNode book3 = null;
        DefaultMutableTreeNode book4 = null;
        DefaultMutableTreeNode book5 = null;
        DefaultMutableTreeNode child = null;
        DefaultMutableTreeNode subchild = null;
        
         List<Task> taskData = mm.getTaskData();
         Map<String,List<Task>> map = new HashMap<String,List<Task>>();
         
         //Vector<List<Task>> vector_of_taskLists = new Vector<List<Task>>();
         ArrayList<Task> tempList = new ArrayList<Task>();
         for(Task t : taskData){
             
             
              System.out.println("I am task: "+t.getIdentifier());
              if(t.getParent()== null){
                  System.out.println("I am a node!");
                  //taskTree.
                  
                  child = new DefaultMutableTreeNode("Task "+ t.getIdentifier());
                  //_root_.add(child);
              }else {
                  System.out.println("I am a child and my parent is: "+t.getParent());
              }
              
             
             for(Task t2: taskData){
                 if((t.getIdentifier().equals(t2.getParent()))){
                                       System.out.println(" my task is: "+t2.getIdentifier());
                                       
                     tempList.add(t2);
                 }
                 
             }
             map.put(t.getIdentifier(), new ArrayList<Task>(tempList));
             tempList.clear();
             //vector_of_taskLists.add(tempList);
             
             if(t.getParent()== null){
                 //child = new DefaultMutableTreeNode("Task "+ t.getIdentifier());
                 //_root_.add(child);
                 
                 //if(t.getParent().equals(t))
                 //for(Task t2 : taskData){
                 //    if(t2.getIdentifier().equals(t.getIdentifier()))
                // }
                 
             }else{
                 
                 for(Task t2: taskData){
                     //if(t2.getParent().equals(t.getIdentifier())){
                     //    subchild = new DefaultMutableTreeNode("Task "+ t.getIdentifier());
                     //    child.add(subchild);
                    // }
                     //System.out.println("task: "+t2.getIdentifier()+" parent is: "+t2.getParent());
                 }
                 //subchild = new DefaultMutableTreeNode("Task "+ t.getIdentifier());
                 //child.add(subchild);
             }
             String fdfd="fdf";
            //category = new DefaultMutableTreeNode("Task "+t.getIdentifier());
            //_root_.add(category);
            
            /*
            int i = 0 ;
            for(;i<5;i++){
                book1 = new DefaultMutableTreeNode(new BookInfo(t.getCompletion(),"tutorial.html"));
                book2 = new DefaultMutableTreeNode(new BookInfo(t.getDeadline(),"tutorial.html"));
                book3 = new DefaultMutableTreeNode(new BookInfo(t.getDeliverable(),"tutorial.html"));
                book4 = new DefaultMutableTreeNode(new BookInfo(t.getDescription(),"tutorial.html"));
                book5 = new DefaultMutableTreeNode(new BookInfo(t.getDuration(),"tutorial.html"));
                category.add(book1);
                category.add(book2);
                category.add(book3);
                category.add(book4);
                category.add(book5);
                
            }
            */
         }
         /*
          System.out.println("____________________________________________________________________");
         Iterator iterator = map.keySet().iterator();
         while(iterator. hasNext()){
             String _i_ = (String) iterator.next();
             System.out.println("I am task: "+ _i_);
             
             child = new DefaultMutableTreeNode("Task "+ _i_);
             _root_.add(child);
             
             List<Task> tempList_ = (List<Task>) map.get(_i_);
             System.out.println("i have "+ tempList_.size()+ " children...they are:");
             
             for(Task tt : tempList_){
                 System.out.println("i am child: "+tt.getIdentifier());
                 subchild = new DefaultMutableTreeNode("Task "+ tt.getIdentifier());
                 child.add(subchild);
             }

         }
         */

        /*
        category = new DefaultMutableTreeNode("Books for Java Programmers");
        _root_.add(category);
 
        //original Tutorial
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Tutorial: A Short Course on the Basics",
            "tutorial.html"));
        category.add(book);
 
        //Tutorial Continued
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Tutorial Continued: The Rest of the JDK",
            "tutorialcont.html"));
        category.add(book);
        */
    }
    
    
        private class BookInfo {
        public String bookName;
        public URL bookURL;
 
        public BookInfo(String book, String filename) {
            bookName = book;
            bookURL = getClass().getResource(filename);
            if (bookURL == null) {
                System.err.println("Couldn't find file: "
                                   + filename);
            }
        }
 
        public String toString() {
            return bookName;
        }
        
        
        

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
    
    public TreeNode buildDummyTree() {
        m_rootNode = new DefaultMutableTreeNode("Task Tree");  
        
        List<Task> taskData = mm.getTaskData();
        
        for(Task t : taskData){
            System.out.println("I am task: "+t.getIdentifier());
              if(t.getParent()== null){
                  System.out.println("I am a node!");
                  //taskTree.
                  
                  DefaultMutableTreeNode child = new DefaultMutableTreeNode("Task "+ t.getIdentifier());
                  m_rootNode.add(child);
                  //_root_.add(child);
              }else {
                  System.out.println("I am a child and my parent is: "+t.getParent());
                  
                  if(searchNode("Task "+t.getParent())!=null){
                      DefaultMutableTreeNode parentNode = searchNode("Task "+t.getParent());
                      DefaultMutableTreeNode child = new DefaultMutableTreeNode("Task "+ t.getIdentifier());
                      parentNode.add(child);
                  }
                  
              }
        }
        
        
        return m_rootNode;  
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
