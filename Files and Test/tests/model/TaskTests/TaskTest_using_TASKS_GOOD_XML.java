package model.TaskTests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import junit.framework.Assert;

import model.XMLParser;
import model.Task;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class TaskTest_using_TASKS_GOOD_XML {

	private static XMLParser __StaXParser__;
	
	private static String dataFile = "tests/resources/TASKS_GOOD.xml";
	
	private static String IDENTIFIER;
	private static String TITLE;
	private static String DESCRIPTION;
	private static String DURATION;
	private static String DELIVERABLE;
	private static String DEADLINE;
	private static ArrayList<String> PEOPLEASSIGNED;
	private static String COMPLETION;
	
	
	public TaskTest_using_TASKS_GOOD_XML (	Task _param1_, 	Boolean _b1_) {				
		IDENTIFIER = _param1_.getIdentifier();
		TITLE = _param1_.getTitle();
		DESCRIPTION = _param1_.getDescription();
		DURATION = _param1_.getDuration();
		DELIVERABLE = _param1_.getDelivarable();
		DEADLINE = _param1_.getDeadline();
		PEOPLEASSIGNED = _param1_.getPeopleassigned();
		COMPLETION = _param1_.getCompletion();				
	}
	
	@Parameterized.Parameters 
    public static List<Object[]> data() {
 		System.out.println("Testing using file: "+ dataFile );  
		System.out.println("___________________________________________________________________________________________________________" );   	
 		
 		__StaXParser__= new XMLParser();    	 
    	List<Task> ___task_list_object___=__StaXParser__.readTasks(dataFile);
    	 
    	 /*
    	 System.out.println("_____________________________________________________________________________________________");
    	 for (task task_object : ___task_list_object___) {
    		 System.out.println("identifier: 	"+ task_object.getIdentifier());
    		 System.out.println("title:		"+ task_object.getTitle());
    		 System.out.println("description: 	"+ task_object.getDescription());
    		 System.out.println("duration:	"+ task_object.getDuration());
    		 System.out.println("deliverable:	"+ task_object.getDelivarable());
    		 System.out.println("deadline: 	"+ task_object.getDeadline());
    		 System.out.println("People assigned:"+ task_object.getPersonassigned());
    		 System.out.println("completion: 	"+ task_object.getCompletion());
        	 System.out.println("_____________________________________________________________________________________________");
    	 }
    	 */
    	 Task tempObject1 = ___task_list_object___.get(0);
    	 Task tempObject2 = ___task_list_object___.get(1);
    	 Task tempObject3 = ___task_list_object___.get(2);
    	 Task tempObject4 = ___task_list_object___.get(3);
    	 Task tempObject5 = ___task_list_object___.get(4);
    	 Task tempObject6 = ___task_list_object___.get(5);
    	 Task tempObject7 = ___task_list_object___.get(6);
    	 
    	 

    	 return Arrays.asList(new Object[][] {
                 { tempObject1, true },
                 { tempObject2, true },
                 { tempObject3, true },
                 { tempObject4, true },
                 { tempObject5, true },
                 { tempObject6, true },
                 { tempObject7, true }
                 
                 
         });    	 
    }
	
    @AfterClass public static void logout() {
    	System.out.println("_____________________________________________________________________________________________");
  }
	@Test
	public void testSetIdentifier() {
		//fail("Not yet implemented");
		System.out.println("Testing Identifier:		" + IDENTIFIER);		
		String  PATTERN_IDENTIFIER = "^[0-9]+$";
		Assert.assertTrue(IDENTIFIER.matches(PATTERN_IDENTIFIER));		
	}

	@Test
	public void testSetTitle() {
		//fail("Not yet implemented");
		System.out.println("Testing Title: 			" + TITLE);
		String  PATTERN_TITLE = "^[a-zA-Z ]+$";
		Assert.assertTrue(TITLE.matches(PATTERN_TITLE));

	}

	@Test
	public void testSetDescription() {
		//fail("Not yet implemented");
		System.out.println("Testing Description: 		" + DESCRIPTION);
		String  PATTERN_DECRIPTION = "^(?:\\p{L}\\p{M}*|[\\ ,.-])*$";
		Assert.assertTrue(DESCRIPTION.matches(PATTERN_DECRIPTION));

	}

	@Test
	public void testSetDuration() {
		//fail("Not yet implemented");
		System.out.println("Testing Duration: 		" + DURATION);
		String  PATTERN_DURATION = "^[0-9]+$";
		Assert.assertTrue(DURATION.matches(PATTERN_DURATION));

	}

	@Test
	public void testSetDelivarable() {
		//fail("Not yet implemented");
		System.out.println("Testing Deliverable: 		" + DELIVERABLE);
		String  PATTERN_DELIVARABLE = "^(?:\\p{L}\\p{M}*|[\\ ,.-])*$";
		Assert.assertTrue(DELIVERABLE.matches(PATTERN_DELIVARABLE));

	}

	@Test
	public void testSetDeadline() {
		//fail("Not yet implemented");
		System.out.println("Testing Deadline: 		" + DEADLINE);
		DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);;
		 Date date;
		 try {
			date = (Date) formatter.parse(this.DEADLINE);
			Assert.assertTrue(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue(!true);
		}
	}
	
	@Test
	public void testSetPersonassigned() {
		//fail("Not yet implemented");
		for (int i=0;i<PEOPLEASSIGNED.size();i++)
		{
			System.out.println("Testing Person assigned:	" + PEOPLEASSIGNED.get(i));
			String  PATTERN_PERSONASSIGNED = "^[0-9]+$";
			Assert.assertTrue(PEOPLEASSIGNED.get(i).matches(PATTERN_PERSONASSIGNED));
		}

	}
	
	@Test
	public void testSetCompletion() {
		//fail("Not yet implemented");
		System.out.println("Testing Completion: 		" + COMPLETION);
		String  PATTERN_COMPLETION = "^[0-9]+$";
		Assert.assertTrue(COMPLETION.matches(PATTERN_COMPLETION));

	}

}
