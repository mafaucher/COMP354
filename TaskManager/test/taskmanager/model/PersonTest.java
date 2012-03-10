/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package taskmanager.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class PersonTest {
    private static XMLParser __StaXParser__;
    private static String dataFile = "tests/resources/PEOPLE_BAD.xml";

    private static double TOTALHOURS = 0;
    private static ArrayList<String> PROJECTS = new ArrayList<String>();
    private static  String IDENTIFIER;
    private static  String FNAME;
    private static  String LNAME;
    private static  String JOBTITLE;
    private static  String JOBDESCRIPTION;
    private static  String CLEARENCE;

    public PersonTest(	Person _param1_, 	Boolean _b1_) {
        IDENTIFIER = _param1_.getIdentifier();
	FNAME = _param1_.getFName();
	LNAME = _param1_.getLName();
	JOBTITLE = _param1_.getJobTitle();
	JOBDESCRIPTION = _param1_.getJobDescription();
	CLEARENCE = _param1_.getClearance();
	TOTALHOURS = _param1_.getTotalHours();
	PROJECTS = _param1_.getProjects();
    }

    @Parameterized.Parameters 
    public static List<Object[]> data() {
 	System.out.println("Testing using file: "+ dataFile );
	System.out.println("___________________________________________________________________________________________________________" );

 	__StaXParser__= new XMLParser();
    	List<Person> ___persons_list_object___=__StaXParser__.readPeople();

    	
    	Person tempObject1 = ___persons_list_object___.get(0);
    	Person tempObject2 = ___persons_list_object___.get(1);
    	Person tempObject3 = ___persons_list_object___.get(2);
    	Person tempObject4 = ___persons_list_object___.get(3);
    	Person tempObject5 = ___persons_list_object___.get(4);
    	Person tempObject6 = ___persons_list_object___.get(5);
    	Person tempObject7 = ___persons_list_object___.get(6);
    	Person tempObject8 = ___persons_list_object___.get(7);


    	 return Arrays.asList(new Object[][] {
                 { tempObject1, true },
                 { tempObject2, true },
                 { tempObject3, true },
                 { tempObject4, true },
                 { tempObject5, true },
                 { tempObject6, true },
                 { tempObject7, true },
                 { tempObject8, true }



         });
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
     * Test of setIdentifier method, of class Person.
     */
    @Test
    public void testSetIdentifier() {
	System.out.println("Testing IDENTIFIER:	" + IDENTIFIER);
	String  PATTERN_IDENTIFIER = "^[0-9]+$";
	assertTrue(IDENTIFIER.matches(PATTERN_IDENTIFIER));
    }

    /**
     * Test of setFName method, of class Person.
     */
    @Test
    public void testSetFName() {
	System.out.println("Testing FNAME: " + FNAME);
	String  PATTERN_TITLE = "^[a-zA-Z -]+$";
	assertTrue(FNAME.matches(PATTERN_TITLE));
    }

    /**
     * Test of setLName method, of class Person.
     */
    @Test
    public void testSetLName() {
        System.out.println("Testing LNAME: " + LNAME);
	String  PATTERN_TITLE = "^[a-zA-Z -]+$";
	assertTrue(LNAME.matches(PATTERN_TITLE));
    }

    /**
     * Test of setJobTitle method, of class Person.
     */
    @Test
    public void testSetJobTitle() {
	System.out.println("Testing JOBTITLE: " + JOBTITLE);
	String  PATTERN_TITLE = "^[a-zA-Z ]+$";
	assertTrue(JOBTITLE.matches(PATTERN_TITLE));
    }

    /**
     * Test of setJobDescription method, of class Person.
     */
    @Test
    public void testSetJobDescription() {
	System.out.println("Testing JOBDESCRIPTION: " + JOBDESCRIPTION);
	String  PATTERN_DECRIPTION = "^(?:\\p{L}\\p{M}*|[\\ ,.-])*$";
	assertTrue(JOBDESCRIPTION.matches(PATTERN_DECRIPTION));
    }

    /**
     * Test of setClearance method, of class Person.
     */
    @Test
    public void testSetClearance() {
	System.out.println("Testing CLEARENCE: " + CLEARENCE);
	String  PATTERN_DURATION = "^[0-9]+$";
	assertTrue(CLEARENCE.matches(PATTERN_DURATION));
    }

    /**
     * Test of setTotalHours method, of class Person.
     */
    @Test
    public void testSetTotalHours() {
        System.out.println("Testing TOTALHOURS: " + TOTALHOURS);
        String _total_hours_String_temp = Double.toString(TOTALHOURS);

        try{
                Double.parseDouble(_total_hours_String_temp);
                assertTrue(true);
        }
        catch(NumberFormatException e){
                e.printStackTrace();
                assertTrue(!true);
        }
    }

    /**
     * Test of setProjects method, of class Person.
     */
    @Test
    public void testSetProjects() {
         //fail("To Be Implemented");
    }

}