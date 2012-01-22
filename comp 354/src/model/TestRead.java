package model;

import java.util.List;

import model.task;

public class TestRead {
	public static void main(String args[]) {

		// Create a new StaXParser, read
		StaXParser read = new StaXParser();
		
		System.out.println("Reading Input File...");
		
		// Read in the XML File containing the task details
		List<task> readConfig = read.readConfig("tasklist.xml");
		
		
		System.out.println("Input File Successfully Read.");
		
		// Output the file detailing the people
		read.processOutput(readConfig);
	}
	
}