package model.PersonTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import model.XMLParser;
import model.Person;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class PersonTest_using_PEOPLE_PASSED_XML {

	private static XMLParser __StaXParser__;
	
	private static String dataFile = "tests/resources/PEOPLE_PASSED.xml";
	
	private static double TOTALHOURS = 0;
	private static ArrayList<String> PROJECTS = new ArrayList<String>();	
	private static  String IDENTIFIER;
	private static  String FNAME;
	private static  String LNAME;
	private static  String JOBTITLE;
	private static  String JOBDESCRIPTION;
	private static  String CLEARENCE;
	private static ArrayList<String> listOfPeople = new ArrayList<String>();
	private static int counter = 0;
	
	public PersonTest_using_PEOPLE_PASSED_XML (	Person _param1_, 	Boolean _b1_) {		
		if (_b1_)
		{
			IDENTIFIER = _param1_.getIdentifier();
			FNAME = _param1_.getFName();
			LNAME = _param1_.getLName();
			JOBTITLE = _param1_.getJobTitle();
			JOBDESCRIPTION = _param1_.getJobDescription();
			CLEARENCE = _param1_.getClearance();
			TOTALHOURS = _param1_.getTotalHours();
			PROJECTS = _param1_.getProjects(); 	
		}
		else
		{
			FNAME = null;
			
		}
	}
	
	
	@Parameterized.Parameters 
    public static List<Object[]> data() {
 		System.out.println("Testing using file: "+ dataFile );   	
		System.out.println("___________________________________________________________________________________________________________" );
		
		String[] thePeople = {"Kai", "Dmitry", "William", 
			"Jeffrey", "Jonathan", "Alain", "Marc-Andre", "Daniel", "Alexander", "Brendan", "Thomas", "Simon"};

		for (int i = 0; i < thePeople.length; i++)
			listOfPeople.add(thePeople[i]);
		
		
		
 		__StaXParser__= new XMLParser();    	 
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
    	Person tempObject1;
    	if (___persons_list_object___.get(0) != null)
    		 tempObject1 = ___persons_list_object___.get(0);
    	else
    		tempObject1 = null;
    	
    	Person tempObject2 = ___persons_list_object___.get(1);
    	Person tempObject3 = ___persons_list_object___.get(2);
    	Person tempObject4 = ___persons_list_object___.get(3);
    	Person tempObject5 = ___persons_list_object___.get(4);
    	Person tempObject6 = ___persons_list_object___.get(5);    	 
    	Person tempObject7 = ___persons_list_object___.get(6);
    	Person tempObject8 = ___persons_list_object___.get(7);
    	Person tempObject9 = ___persons_list_object___.get(8);
    	Person tempObject10 = ___persons_list_object___.get(9);
    	Person tempObject11 = ___persons_list_object___.get(10);
    	Person tempObject12;
    	
    	if (___persons_list_object___.size() > 11)
    		tempObject12 = ___persons_list_object___.get(11);
    	else
    		tempObject12 = null;
    	

    	 return Arrays.asList(new Object[][] {
                 { tempObject1, true },
                 { tempObject2, true },
                 { tempObject3, true },
                 { tempObject4, true },
                 { tempObject5, true },
                 { tempObject6, true },
                 { tempObject7, true },
                 { tempObject8, true },
                 { tempObject9, true },
                 { tempObject10, true },
                 { tempObject11, true },
                 { tempObject12, false }
                 
         });    	 
    }
	
    @AfterClass public static void logout() {
    	System.out.println("_____________________________________________________________________________________________");
  }
    
   
   @Test
   public void removePeopleThatPassed()
   {
	   /*
	 try
	 {
		 String s = FNAME; //to test for null
	 
	 }catch (NullPointerException e)
	 {
		 Assert.assertTrue(false);
	 }
	 */
	 
	 
	   if (listOfPeople.contains(FNAME))
		   for (int i = 0; i < listOfPeople.size(); i++)
			   if (listOfPeople.get(i).equals(FNAME))
				   listOfPeople.remove(i);
	   
			   
		if (++counter == 12)
			Assert.assertTrue(listOfPeople.isEmpty());
	   
	   Assert.assertTrue(true);
	   
   }
   
}
