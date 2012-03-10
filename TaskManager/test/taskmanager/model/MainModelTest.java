/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package taskmanager.model;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author t_pauli
 */
public class MainModelTest {

    public MainModelTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of updateXML method, of class MainModel.
     */
    @Test
    public void testUpdateXML() {
        System.out.println("updateXML");
        MainModel instance = new MainModel();
        instance.updateXML();

        // Test to make sure the variables we need are not empty or null
            assertFalse("People Data is Empty.",instance.getPeopleData().isEmpty());
            assertFalse("Task Data is Empty.",instance.getTaskData().isEmpty());
    }

    /**
     * Test of getTaskData method, of class MainModel.
     */
    @Test
    public void testGetTaskData() {
        System.out.println("getTaskData");
        MainModel instance = new MainModel();
        List badResult = null;
        List result = instance.getTaskData();

        // Ensure what we are returning is not null or empty.
            assertFalse("Task Data is Empty.",result.isEmpty());
            assertFalse("Task Data is null.",result.equals(badResult));
    }

    /**
     * Test of getPeopleData method, of class MainModel.
     */
    @Test
    public void testGetPeopleData() {
        System.out.println("getPeopleData");
        MainModel instance = new MainModel();
        List badResult = null;
        List result = instance.getPeopleData();

        // Ensure what we are returning is not null or empty.
            assertFalse("People Data is null.",result.isEmpty());
            assertFalse("People Data is Empty.",result.equals(badResult));
    }

    /**
     * Test of getTotalHoursOnProjects method, of class MainModel.
     */
    @Test
    public void testGetTotalHoursOnProjects() {
        System.out.println("getTotalHoursOnProjects");
        String personID = "5";
        MainModel instance = new MainModel();
        int minResult = 0;
        int expResult = 162;
        int result = instance.getTotalHoursOnProjects(personID);

        // Ensure what we are returning is greater than or equal to 0
            assertTrue("Number of Hours is less than 0.",minResult <= result);

        // Ensure that we have proper data and format for an arbitrary user (in this case 5)
            assertEquals(expResult, result);
    }

    /**
     * Test of getListOfProjects method, of class MainModel.
     */
    @Test
    public void testGetListOfProjects() {
        System.out.println("getListOfProjects");
        String personID = "5";
        MainModel instance = new MainModel();
        String badResult = null;
        String expResult = "5, 9, 10";
        String result = instance.getListOfProjects(personID);

        // Ensure what we are returning is not null or empty.
            assertFalse("People Data is null.",result.isEmpty());
            assertFalse("People Data is Empty.",result.equals(badResult));

        // Ensure that we have proper data and format for an arbitrary user (in this case 5)
            assertEquals(expResult, result);
        
    }

    /**
     * Test of writePeopleTxt method, of class MainModel.
     */
    @Test
    public void testWritePeopleTxt() {
        System.out.println("writePeopleTxt");
        MainModel instance = new MainModel();
        instance.writePeopleTxt();
    }

    /**
     * Test of nextAvailableId method, of class MainModel.
     */
    @Test
    public void testNextAvailableId() {
        System.out.println("nextAvailableId");
        MainModel instance = new MainModel();
        String expResult = "12";
        String badResult = null;
        String result = instance.nextAvailableId();

        // Ensure that the index is not null
            assertFalse("Index is null.",result.equals(badResult));
            
        // Ensure that the index is the appropriate value (12 at this point)
            assertEquals("Index is not the proper value.",expResult, result);

    }

    /**
     * Test of findTask method, of class MainModel.
     */
    @Test
    public void testFindTask() {
        System.out.println("findTask");
        String taskID = "50";
        MainModel instance = new MainModel();
        Task expResult = null;
        Task result = instance.findTask(taskID);

        // Ensure that the task is the appropriate value (null at this point)
            assertEquals("Index is not the proper value.",expResult, result);

        taskID = "5";
        String expTitle = "Documentation";
        result = instance.findTask(taskID);

        // Ensure that the task is the appropriate value (5 - Documentation at this point)
            assertEquals("Index is not the proper value.",expTitle, result.getTitle());
    }

    /**
     * Test of assignStringOfID method, of class MainModel.
     */
    @Test
    public void testAssignStringOfID() {
        System.out.println("assignStringOfID");
        String taskID = "1";
        String data = "9, 11, 1";

        MainModel instance = new MainModel();
        Task task = instance.findTask(taskID);
        String expAssignees = data;
        instance.assignStringOfID(task, data);

        // Ensure that the assignees of the task are the proper data (equal to the data string)
            assertEquals("Index is not the proper value.",expAssignees, instance.findTask(taskID).getPeopleassignedAsString());
    }

}