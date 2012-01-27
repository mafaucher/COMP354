package model.unit_tests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import model.StaXParser;
import model.task;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class taskTest__with_staXParser_using_GOOD_XML  {

		
	private static StaXParser __StaXParser__;

	private static String IDENTIFIER;
	private static String TITLE;
	private static String DESCRIPTION;
	private static String DURATION;
	private static String DELIVERABLE;
	private static String DEADLINE;
	private static String PERSONASSIGNED;
	private static String COMPLETION;
	
	public taskTest__with_staXParser_using_GOOD_XML (	task _param1_, 	Boolean _b1_
									     	
											) {
		this.IDENTIFIER = _param1_.getIdentifier();
		this.TITLE = _param1_.getTitle();
		this.DESCRIPTION = _param1_.getDescription();
		this.DURATION = _param1_.getDuration();
		this.DELIVERABLE = _param1_.getDelivarable();
		this.DEADLINE = _param1_.getDeadline();
		this.PERSONASSIGNED = _param1_.getPersonassigned();
		this.COMPLETION = _param1_.getCompletion();
	}
	
	
	@Parameterized.Parameters 
    public static List<Object[]> data() {
    	 __StaXParser__= new StaXParser();
    	 List<task> ___task_list_object___=__StaXParser__.readConfig("TASKS_GOOD.xml");
    	 
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
    	 task tempObject1 = ___task_list_object___.get(0);
    	 task tempObject2 = ___task_list_object___.get(1);
    	 task tempObject3 = ___task_list_object___.get(2);
    	 task tempObject4 = ___task_list_object___.get(3);
    	 task tempObject5 = ___task_list_object___.get(4);
    	 task tempObject6 = ___task_list_object___.get(5);
    	 
    	 

    	 return Arrays.asList(new Object[][] {
                 { tempObject1, true },
                 { tempObject2, true },
                 { tempObject3, true },
                 { tempObject4, true },
                 { tempObject5, true },
                 { tempObject6, true }
                 
                 
         });    	 
    }
	
	
	@Test
	public void testSetIdentifier() {
		//fail("Not yet implemented");
		System.out.println("Testing Identifier:		" + IDENTIFIER);		
		String  PATTERN_IDENTIFIER = "^[0-9]+$";
		Assert.assertTrue(this.IDENTIFIER.matches(PATTERN_IDENTIFIER));		
	}

	@Test
	public void testSetTitle() {
		//fail("Not yet implemented");
		System.out.println("Testing Title: 			" + TITLE);
		String  PATTERN_TITLE = "^[a-zA-Z ]+$";
		Assert.assertTrue(this.TITLE.matches(PATTERN_TITLE));

	}

	@Test
	public void testSetDescription() {
		//fail("Not yet implemented");
		System.out.println("Testing Description: 		" + DESCRIPTION);
		String  PATTERN_DECRIPTION = "^(?:\\p{L}\\p{M}*|[\\ ,.-])*$";
		Assert.assertTrue(this.DESCRIPTION.matches(PATTERN_DECRIPTION));

	}

	@Test
	public void testSetDuration() {
		//fail("Not yet implemented");
		System.out.println("Testing Duration: 		" + DURATION);
		String  PATTERN_DURATION = "^[0-9]+$";
		Assert.assertTrue(this.DURATION.matches(PATTERN_DURATION));

	}

	@Test
	public void testSetDelivarable() {
		//fail("Not yet implemented");
		System.out.println("Testing Deliverable: 		" + DELIVERABLE);
		String  PATTERN_DELIVARABLE = "^(?:\\p{L}\\p{M}*|[\\ ,.-])*$";
		Assert.assertTrue(this.DELIVERABLE.matches(PATTERN_DELIVARABLE));

	}

	@Test
	public void testSetDeadline() {
		//fail("Not yet implemented");
		System.out.println("Testing Deadline: 		" + DEADLINE);
		DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");;
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
		System.out.println("Testing Person assigned:	" + PERSONASSIGNED);
		String  PATTERN_PERSONASSIGNED = "^(?:\\p{L}\\p{M}*|[\\ ,.-])*$";
		Assert.assertTrue(this.PERSONASSIGNED.matches(PATTERN_PERSONASSIGNED));

	}

	@Test
	public void testSetCompletion() {
		//fail("Not yet implemented");
		System.out.println("Testing Completion: 		" + COMPLETION);
		String  PATTERN_COMPLETION = "^[0-9]+$";
		Assert.assertTrue(this.COMPLETION.matches(PATTERN_COMPLETION));

	}

}
