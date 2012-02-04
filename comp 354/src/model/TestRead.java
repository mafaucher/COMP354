package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

import model.Task;
import model.Subtask;
import model.Person;

public class TestRead {
	private static List<Task> taskList;
	private static List<Person> peopleList;
	
	static int longestnamelen = 0;
	static int longesttotalhourslen = 0;
	static int longestprojectlistlen = 0;
	
	public static void main(String args[]) {

		// Create StaXparser objects, used to read the XML files
		XMLParser readTasks = new XMLParser();
		// XMLParser readSubtasks = new XMLParser();  // For Increment 2
		XMLParser readPeople = new XMLParser();
		
		System.out.println("Reading Input File...");
		
		// Read in the XML File containing the task details
		taskList = readTasks.readTasks("tasks.xml");
		
		// Read in the XML File containing the subtask details. This is for Increment 2.
		// subtaskList = readSubtasks.readSubtasks("subtasks.xml");
		
		// Read in the XML File containing the subtask details
		peopleList = readPeople.readPeople("people.xml");
		
		System.out.println("Input File Successfully Read.");
		
		// Process the subtasks, updating information on the people
		processTasks();

		// Build the output string based on the people
		String output = buildOutput();
		
		// Output the file detailing the people
		writeFile(output);

	}
	
	public static void processTasks(){
			String fullname = "";
			String projstr = "";
			String hoursstr = "";
			
			int templen = 0;
			
			for (Task task : taskList) {
				
				ArrayList<String> assignees= new ArrayList<String>(task.getPeopleassigned());		
				Iterator<String> iterator = assignees.iterator();
				Iterator<Person> piterator = peopleList.iterator();
				
				String curraid;
				int onproj = assignees.size(); // determine how many people are assigned to the task
				double timedistro = (Double.parseDouble(task.getDuration()) / onproj); // determine the even distribution of time based on number of assignees				


				Person currentPerson = new Person();
				
				while(iterator.hasNext()) // cycle through the assignees, and update field where necessary
				{
					curraid = iterator.next();
					while(piterator.hasNext()){
						currentPerson = piterator.next();
								
						if(currentPerson.getIdentifier().matches(curraid)){
							
							break;
						}
						if(!piterator.hasNext()){
							currentPerson = null;
						}
					}
					
					if(currentPerson == null){
						continue;
					}
					
					currentPerson.setTotalHours(timedistro);
					currentPerson.setProjects(task.getIdentifier());
					
					fullname = currentPerson.getLName() + ", " + currentPerson.getFName();	
					templen = fullname.length();
					
					if(templen > longestnamelen)
					{
						longestnamelen = templen; // store the longest name length for formatting later
					}
					
					
					if(projstr != ""){
						projstr += ", ";
					}
					
					
					ArrayList<String> projects = new ArrayList<String>(currentPerson.getProjects());		
					Iterator<String> projIterator = projects.iterator();
								
					while(projIterator.hasNext()) // cycle through the assignees, and update field where necessary
					{	
						if(projstr != ""){
							projstr += ", ";
						}
						
						projstr += projIterator.next();
					
					
						templen = projstr.length();
						if(templen > longestprojectlistlen)
						{
							longestprojectlistlen = templen;  // store the longest project list length for formatting later
						}
						
						projstr = "";
					}
					
					
					hoursstr = Double.toString(currentPerson.getTotalHours());
					templen = hoursstr.length();
					
					if(templen > longesttotalhourslen)
					{
						longesttotalhourslen = templen;  // store the longest project list length for formatting later
					}

				}
			}
	}
	
	public static String buildOutput(){
		String output = "";
		String fullname = "";
		String projstr = "";
		String headerline = "";
		String separator = "";
		String[] headers = {"Name","Total Hours","Project List"};
		
		int templen = 0;
		int currheaderlen = 0;
		int currprojlistlen = 0;
		int thlen = 4; // total hours min size, hardcoded for now.
		
		if(longesttotalhourslen < thlen){
			longesttotalhourslen = thlen;
		}
		
		for (Person person : peopleList) { // Build the rows of people
			fullname = person.getLName() + ", " + person.getFName();
			
			templen = fullname.length();
			output += fullname;
			for(int c = 0; c <= (longestnamelen - templen); c++){ // Add dynamic whitespace
				output += " ";
			}
			output += "| ";
			
			templen = Double.toString(person.getTotalHours()).length();
	
			output += person.getTotalHours();
			for(int c = 0; c <= (longesttotalhourslen - templen); c++){ // Add dynamic whitespace
				output += " ";
			}			
		

			output += "| ";
			
			projstr = "";
			
			ArrayList<String> projects = new ArrayList<String>(person.getProjects());		
			Iterator<String> iterator = projects.iterator();
						
			while(iterator.hasNext()) // cycle through the assignees, and update field where necessary
			{	
				if(projstr != ""){
					projstr += ", ";
				}
				
				projstr += iterator.next();
			}
			
			templen = projstr.length();
			
			if(templen > longestprojectlistlen)
			{
				longestprojectlistlen = templen;
			}
			
			output += projstr + System.getProperty("line.separator");
		}
		
		// Build the output file header

		for(int i = 0;i< headers.length; i++){
			currheaderlen = headers[i].length();
			headerline += headers[i];
			if(i == 0) // Column heading for the name
			{ 
				for(int c = 0; c <= (longestnamelen - currheaderlen); c++){ // Add dynamic whitespace
					headerline += " ";
				}
				headerline += "| ";
			}
			else if(i == 1){ // Column heading for the total hours
				for(int c = 0; c < (longesttotalhourslen - currheaderlen); c++){ // Add dynamic whitespace
					headerline += " ";
				}
				headerline += " | ";
			}
			else{ // Column heading for the project list
				for(int c = 0; c < (longestprojectlistlen - currprojlistlen); c++){ // Add dynamic whitespace
					headerline += " ";
				}
			}
		}
		
		headerline += System.getProperty("line.separator");
		
		for(int i = 0;i< headerline.length(); i++){ // Build the line to separate headers from data
			separator += "-";
		}
		
		separator += System.getProperty("line.separator");
		
		// Put it all together and return
		output = headerline + separator + output; 
	
		return output;
	}
	
	public static void writeFile(String output){
		try{
			File file = new File("people.txt"); // Name of the output file
			 
			// If the file does not exists, create it.
			if(!file.exists()){
			   file.createNewFile();
			}
	
			FileOutputStream fop=new FileOutputStream(file);
			
			// Get the content in bytes
			fop.write(output.getBytes());
			fop.flush();
	        fop.close();
	
	        System.out.println("Output File Successfully Written.");

		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	

	
	
	
	
}