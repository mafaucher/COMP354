package model.SubtaskTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import model.Person;
import model.StaXParser;
import model.Subtask;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class SubtaskTest_using_SUBTASKS_BAD_XML {

	private static StaXParser __StaXParser__;
	
	private static String dataFile = "tests/resources/SUBTASKS_BAD.xml";
					
	
	private static  String IDENTIFIER;
	private static  String PARENTID;
	private static  String PRIORITY;
	private static  String TITLE;
	private static  String DESCRIPTION;
	private static  String DURATION;
	private static ArrayList PEOPLE_ASSIGNED = new ArrayList();	
	
	
	public SubtaskTest_using_SUBTASKS_BAD_XML (	Subtask _param1_, 	Boolean _b1_) {				
		IDENTIFIER = _param1_.getIdentifier();
		PARENTID = _param1_.getParentID();
		PRIORITY = _param1_.getPriority();
		TITLE = _param1_.getTitle();
		DESCRIPTION = _param1_.getDescription();
		DURATION = _param1_.getDuration();
		PEOPLE_ASSIGNED = _param1_.getPeopleassigned();
	}
	
	
	@Parameterized.Parameters 
    public static List<Object[]> data() {
 		System.out.println("Testing using file: "+ dataFile );   	
		System.out.println("___________________________________________________________________________________________________________" );   	
 		
 		__StaXParser__= new StaXParser();    	 
    	List<Subtask> ___subtasks_list_object___=__StaXParser__.readSubtasks(dataFile);
    	 
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
    	Subtask tempObject1 = ___subtasks_list_object___.get(0);
    	Subtask tempObject2 = ___subtasks_list_object___.get(1);
    	Subtask tempObject3 = ___subtasks_list_object___.get(2);
    	Subtask tempObject4 = ___subtasks_list_object___.get(3);
    	Subtask tempObject5 = ___subtasks_list_object___.get(4);
    	Subtask tempObject6 = ___subtasks_list_object___.get(5);    	 
    	Subtask tempObject7 = ___subtasks_list_object___.get(6);
    	Subtask tempObject8 = ___subtasks_list_object___.get(7);
    	 

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
	public void testSetParentID() {
		//fail("Not yet implemented");
		System.out.println("Testing PARENTID:		" + PARENTID);		
		String  PATTERN_IDENTIFIER = "^[0-9]+$";
		Assert.assertTrue(PARENTID.matches(PATTERN_IDENTIFIER));	

	}

	@Test
	public void testSetIdentifier() {
		//fail("Not yet implemented");
		System.out.println("Testing IDENTIFIER:		" + IDENTIFIER);		
		String  PATTERN_IDENTIFIER = "^[0-9]+$";
		Assert.assertTrue(IDENTIFIER.matches(PATTERN_IDENTIFIER));	

	}

	@Test
	public void testSetTitle() {
		//fail("Not yet implemented");
		System.out.println("Testing TITLE: 			" + TITLE);
		String  PATTERN_TITLE = "^[a-zA-Z ]+$";
		Assert.assertTrue(TITLE.matches(PATTERN_TITLE));

	}

	@Test
	public void testSetDescription() {
		//fail("Not yet implemented");
		System.out.println("Testing DESCRIPTION: 		" + DESCRIPTION);
		String  PATTERN_DECRIPTION = "^(?:\\p{L}\\p{M}*|[\\ ,.-])*$";
		Assert.assertTrue(DESCRIPTION.matches(PATTERN_DECRIPTION));

	}

	@Test
	public void testSetDuration() {
		//fail("Not yet implemented");
		System.out.println("Testing DURATION:		" + DURATION);		
		String  PATTERN_IDENTIFIER = "^[0-9]+$";
		Assert.assertTrue(DURATION.matches(PATTERN_IDENTIFIER));	

	}

	@Test
	public void testSetPriority() {
		//fail("Not yet implemented");
		System.out.println("Testing PRIORITY:		" + PRIORITY);		
		String  PATTERN_IDENTIFIER = "^[0-9]+$";
		Assert.assertTrue(PRIORITY.matches(PATTERN_IDENTIFIER));	

	}

	@Test
	public void testSetPeopleassigned() {
		//fail("Not yet implemented");
		//System.out.println("Testing LNAME: 			" + LNAME);
		String  PATTERN_TITLE = "^[0-9]+$";
		int var=PEOPLE_ASSIGNED.size();
		int i=0;
		Iterator<String> itr = PEOPLE_ASSIGNED.iterator();
		while (itr.hasNext()){
			System.out.println("Testing Person: 		" + itr.next().toString());
			//Assert.assertTrue(itr.next().toString().matches(PATTERN_TITLE));
			i++;
		}
		
		Assert.assertEquals(var, i);//(LNAME.matches(PATTERN_TITLE));
	}
	
}
