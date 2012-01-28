package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

import model.Task;
import model.Subtask;
import model.Person;

public class TestRead {
	private static List<Task> taskList;
	private static List<Subtask> subtaskList;
	private static List<Person> peopleList;
	
	static int longestnamelen = 0;
	static int longestprojectlistlen = 0;
	
	public static void main(String args[]) {

		// Create StaXparser objects, used to read the XML files
		StaXParser readTasks = new StaXParser();
		StaXParser readSubtasks = new StaXParser();
		StaXParser readPeople = new StaXParser();
		
		System.out.println("Reading Input File...");
		
		// Read in the XML File containing the task details
		taskList = readTasks.readTasks("tasks.xml");
		
		// Read in the XML File containing the subtask details
		subtaskList = readSubtasks.readSubtasks("subtasks.xml");
		
		// Read in the XML File containing the subtask details
		peopleList = readPeople.readPeople("people.xml");
		
		System.out.println("Input File Successfully Read.");
		
		// Process the subtasks, updating information on the people
		processSubtasks();

		// Build the output string based on the people
		String output = buildOutput();
		
		// Output the file detailing the people
		writeFile(output);

	}
	
	public static void processSubtasks(){
			String fullname = "";
			String projstr = "";
			
			int templen = 0;
			
			for (Subtask subtask : subtaskList) {
				
				ArrayList<String> assignees= new ArrayList(subtask.getPeopleassigned());		
				Iterator iterator = assignees.iterator();
				
				int curraid;
				int onproj = assignees.size(); // determine how many people are assigned to the task
				double timedistro = (Double.parseDouble(subtask.getDuration()) / onproj); // determine the even distribution of time based on number of assignees				

				
				while(iterator.hasNext()) // cycle through the assignees, and update field where necessary
				{
					curraid = Integer.parseInt((String) iterator.next());
					Person currentPerson = peopleList.get(curraid);
					currentPerson.setTotalHours(timedistro);
					currentPerson.setProjects(subtask.getParentID()+"."+subtask.getIdentifier());
					
					fullname = currentPerson.getLName() + ", " + currentPerson.getFName();	
					templen = fullname.length();
					
					if(templen > longestnamelen)
					{
						longestnamelen = templen; // store the longest name length for formatting later
					}
					
					
					if(projstr != ""){
						projstr += ", ";
					}
					
					
					ArrayList<String> projects = new ArrayList(currentPerson.getProjects());		
					Iterator projIterator = projects.iterator();
								
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
					// Debug output:
					// System.out.println("Assignee: " + currentPerson.getFName() + " " + currentPerson.getLName() + ". Hours: " + currentPerson.getTotalHours() + ". Projects: " + currentPerson.getProjects());
				}
			}
	}
	
	public static String buildOutput(){
		String output = "";
		String fullname = "";
		String projstr = "";
		String temp = "";
		String headerline = "";
		String separator = "";
		String[] headers = {"Name","Total Hours","Project List"};
		
		int templen = 0;
		int currheaderlen = 0;
		int currprojlistlen = 0;
		int thlen = 4; // total hours max size, hardcoded for now.
		
		for (Person person : peopleList) { // Build the rows of people
			fullname = person.getLName() + ", " + person.getFName();
			
			templen = fullname.length();
			output += fullname;
			for(int c = 0; c <= (longestnamelen - templen); c++){ // Add dynamic whitespace
				output += " ";
			}
			output += "| " + person.getTotalHours();
		
			for(int c = 0; c < (headers[1].length()-thlen); c++){ // Add dynamic whitespace
				output += " ";
			}
			output += "| ";
			
			projstr = "";
			
			ArrayList<String> projects = new ArrayList(person.getProjects());		
			Iterator iterator = projects.iterator();
						
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
			
			output += projstr + "\n";
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
				
				headerline += " | ";
			}
			else{ // Column heading for the project list
				for(int c = 0; c < (longestprojectlistlen - currprojlistlen); c++){ // Add dynamic whitespace
					headerline += " ";
				}
			}
		}
		
		headerline += "\n";
		
		for(int i = 0;i< headerline.length(); i++){ // Build the line to separate headers from data
			separator += "-";
		}
		
		separator += "\n";
		
		// Put it all together and return
		output = headerline + separator + output; 
	
		
		/*	
		
		// Write out the file header
		String content = "Name";
		
		int i = 0;
		int currnamelen = 0;
		int currhourslen = 0;
		int headerlen = 0;
		int projlistlen = 0;
		
		for (i=0; i<(longestnamelen-3); i++){
			  content += " ";
		}
		
		content += "| Total Hours | Projects" + "\r\n" + "--------------------------------------" + "\r\n";  // <=== The "\r\n" is for newline
																											 // so that in a text file every record
																											 // appears under. row after row.
		
		System.out.println(content.substring(0,content.length()-2));	// <=== WRITE HEADER TO CONSOLE WINDOW
																		// .substring(0,content.length()-2) is to remove extra linefeed
																		// so that it writes to console window exactly as seen in the file 
		
		String _temp_Person_output_row_ = ""; 	// <=== Temporary string to hold all the data to be written to console
												// window in a row by row manner.
		
    	for ( Person temp : people ){
    		currnamelen = temp.getName().length();
    			
    		content += temp.getName();
    		
    		
    		_temp_Person_output_row_ += temp.getName(); 
    		
     		for (i=0; i<((longestnamelen-currnamelen)+1); i++){
    			  content += " ";
    			  _temp_Person_output_row_ += " ";
    		}
    		
     		String roundedHours = String.format("%.2f", temp.getTotalHours());
    		content +="| ";
    		_temp_Person_output_row_ += "| ";
    		
    		
			    		
     		content += roundedHours;
     		_temp_Person_output_row_ += roundedHours;
     		
    		currhourslen = Double.toString(temp.getTotalHours()).length();

    		currhourslen = headers[1].length();
    		
    		
     		for (i=0; i<((longesthourslen-currhourslen)-1); i++){
    			  content += " ";
    			  _temp_Person_output_row_ += " ";
    		}
     		content += "| " + temp.getProjects() + "\r\n";
     		_temp_Person_output_row_ += "| " + temp.getProjects() + "\r\n";
    	}
    	
 		System.out.println(_temp_Person_output_row_);*/
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