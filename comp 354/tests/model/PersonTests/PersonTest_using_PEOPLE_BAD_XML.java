package model.PersonTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import model.StaXParser;
import model.Person;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class PersonTest_using_PEOPLE_BAD_XML {

	private static StaXParser __StaXParser__;
	
	private static String dataFile = "tests/resources/PEOPLE_BAD.xml";
				
	
	
	private static double TOTALHOURS = 0;
	private static ArrayList<String> PROJECTS = new ArrayList<String>();	
	private static  String IDENTIFIER;
	private static  String FNAME;
	private static  String LNAME;
	private static  String JOBTITLE;
	private static  String JOBDESCRIPTION;
	private static  String CLEARENCE;
	
	public PersonTest_using_PEOPLE_BAD_XML (	Person _param1_, 	Boolean _b1_) {				
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

 		__StaXParser__= new StaXParser();    	 
    	List<Person> ___persons_list_object___=__StaXParser__.readPeople(dataFile);
    	 
    	 /*
    	 System.out.println("_____________________________________________________________________________________________");
    	 for (task task_object : ___task_list_object___) {
    		 System.out.println("identifier: 	"+ task_object.getIdentifier());
    		 System.out.println("title:		"+ task_object.getTitle());
    		 System.out.println("description: 	"+ task_object.getDescription());
    		 System.out.println("duration:	"+ task_object.getDuration());
    		 System.out.println("deliverable:	"+ task_object.getDelivarable());
    		 System.out.println("deadline: 	"+ task_object.getDeadline());
    		 System.out.println("personassigned:"+ task_object.getPersonassigned());
    		 System.out.println("completion: 	"+ task_object.getCompletion());
        	 System.out.println("_____________________________________________________________________________________________");
    	 }
    	 */
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
	
    @AfterClass public static void logout() {
    	System.out.println("_____________________________________________________________________________________________");
  }
	
	@Test
	public void testSetIdentifier() {
		//fail("Not yet implemented");
		System.out.println("Testing IDENTIFIER:		" + IDENTIFIER);		
		String  PATTERN_IDENTIFIER = "^[0-9]+$";
		Assert.assertTrue(IDENTIFIER.matches(PATTERN_IDENTIFIER));	
	}

	@Test
	public void testSetFName() {
		//fail("Not yet implemented");
		System.out.println("Testing FNAME: 			" + FNAME);
		String  PATTERN_TITLE = "^[a-zA-Z -]+$";
		Assert.assertTrue(FNAME.matches(PATTERN_TITLE));
	}

	@Test
	public void testSetLName() {
		//fail("Not yet implemented");
		System.out.println("Testing LNAME: 			" + LNAME);
		String  PATTERN_TITLE = "^[a-zA-Z -]+$";
		Assert.assertTrue(LNAME.matches(PATTERN_TITLE));
	}

	@Test
	public void testSetJobTitle() {
		//fail("Not yet implemented");
		System.out.println("Testing JOBTITLE: 		" + JOBTITLE);
		String  PATTERN_TITLE = "^[a-zA-Z ]+$";
		Assert.assertTrue(JOBTITLE.matches(PATTERN_TITLE));
	}

	@Test
	public void testSetJobDescription() {
		//fail("Not yet implemented");
		System.out.println("Testing JOBDESCRIPTION: 	" + JOBDESCRIPTION);
		String  PATTERN_DECRIPTION = "^(?:\\p{L}\\p{M}*|[\\ ,.-])*$";
		Assert.assertTrue(JOBDESCRIPTION.matches(PATTERN_DECRIPTION));
	}

	@Test
	public void testSetClearance() {
		//fail("Not yet implemented");
		System.out.println("Testing CLEARENCE: 		" + CLEARENCE);
		String  PATTERN_DURATION = "^[0-9]+$";
		Assert.assertTrue(CLEARENCE.matches(PATTERN_DURATION));
	}

	@Test
	public void testSetTotalHours() {
		//fail("Not yet implemented");
		System.out.println("Testing TOTALHOURS: 		" + TOTALHOURS);
		String _total_hours_String_temp = Double.toString(TOTALHOURS);
		
		try{
			Double.parseDouble(_total_hours_String_temp);
			Assert.assertTrue(true);
		}
		catch(NumberFormatException e){
			e.printStackTrace();
			Assert.assertTrue(!true);
		}
		
		//String  PATTERN_DURATION = "^[0-9]+$";
		//Assert.assertTrue(_total_hours_String_temp.matches(PATTERN_DURATION));
	}

	@Test
	public void testSetProjects() {
		//fail("Not yet implemented");
	}

}
