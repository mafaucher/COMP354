package model;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


public class StaXParser {
	static final String TASK="task";
	static final String IDENTIFIER = "identifier";
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String DURATION = "duration";
	static final String DELIVERABLE = "deliverable";
	static final String DEADLINE = "deadline";
	static final String PERSONASSIGNED = "personassigned";
	static final String COMPLETION = "completion";

	@SuppressWarnings({ "unchecked", "null" })
	public List<task> readConfig(String configFile) {
		List<task> tasks = new ArrayList<task>();
		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
			task task = null;

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					// If we have a item element we create a new item
					if (startElement.getName().getLocalPart() == (TASK)) {
						task = new task();
						// We read the attributes from this tag and add the date
						// attribute to our object
						/*Iterator<Attribute> attributes = startElement
								.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(DATE)) {
								item.setDate(attribute.getValue());
							}

						}*/
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(IDENTIFIER)) {
							event = eventReader.nextEvent();
							task.setIdentifier(event.asCharacters().getData());
							continue;
						}
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(TITLE)) {
						event = eventReader.nextEvent();
						task.setTitle(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(DESCRIPTION)) {
						event = eventReader.nextEvent();
						task.setDescription(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(DURATION)) {
						event = eventReader.nextEvent();
						task.setDuration(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(DELIVERABLE)) {
						event = eventReader.nextEvent();
						task.setDelivarable(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(DEADLINE)) {
						event = eventReader.nextEvent();
						task.setDeadline(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(PERSONASSIGNED)) {
						event = eventReader.nextEvent();
						task.setPersonassigned(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(COMPLETION)) {
						event = eventReader.nextEvent();
						task.setCompletion(event.asCharacters().getData());
						continue;
					}
				}
				// If we reach the end of an item element we add it to the list
				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (TASK)) {
						tasks.add(task);
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return tasks;
	}
	
    public static void processOutput( List<task> taskList )
    {	
    	try{

    		ArrayList uniquenames = new ArrayList(); 
    		ArrayList<Person> people = new ArrayList<Person>();
    		
    		System.out.println("Writing Output File...");
    		
    		int n = 0;
    		int longestnamelen = 0;
    		int longesthourslen = 0;	
    		int longestprojlistlen = 0;	
    		
    		for (task task : taskList) {
    			String[] names =  task.getPersonassigned().toString().split( ",\\s*" );

    			for ( String string : names ){
    				int onproj = names.length;
    				double timedistro = (Double.parseDouble(task.getDuration()) / onproj);

			    	if(string.length() > longestnamelen){
			    		longestnamelen = string.length();    			    		
			    	}
			    	

    			    if(!uniquenames.contains(string)){
    			    	Person temp = new Person();
    			    	
    			    	temp.setName(string);
    			    	temp.setTotalHours(timedistro);
    			    	temp.setProjects(task.getIdentifier());
    	    	
    			    	people.add(temp);    
    			    	uniquenames.add(string);
    			    }
    			    else{
    			    	Iterator iterator = people.iterator();
    					
    			    	for ( Person temp : people ){
    			    			if( string.equals(temp.getName())){
    			    				temp.setTotalHours(timedistro);
    			    				temp.setProjects(task.getIdentifier());
    			    				
    						    	if(temp.getProjects().toString().length() > longestprojlistlen){
    						    		longestprojlistlen = temp.getProjects().toString().length();    
    						    	}
    						    	
    						    	if(Double.toString(temp.getTotalHours()).length() > longesthourslen){
    						    		longesthourslen = Double.toString(temp.getTotalHours()).length();
    						    	}
    			    			}
    			    	}
    			    	
    			    }
    			}    			
    		}
    		
    		
    		String[] headers = {"Name","Total Hours","Project List"};
    		
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
    		
    		content += "| Total Hours | Projects" + "\n" +
					 "--------------------------------------" + "\n";

	    	for ( Person temp : people ){
	    		currnamelen = temp.getName().length();

	    		content += temp.getName();
	    		
	     		for (i=0; i<((longestnamelen-currnamelen)+1); i++){
	    			  content += " ";
	    		}
	    		
	     		String roundedHours = String.format("%.2f", temp.getTotalHours());
	    		content +="| ";
    			    		
	     		content += roundedHours;
	    		currhourslen = Double.toString(temp.getTotalHours()).length();

	    		currhourslen = headers[1].length();

	    		
	     		for (i=0; i<((longesthourslen-currhourslen)-1); i++){
	    			  content += " ";
	    		}
	     		content += "| " + temp.getProjects() + "\n";

	    	}
    	

    		

    		File file = new File("people.txt"); // Name of the output file
 
    		// If the file does not exists, create it.
    		if(!file.exists()){
    		   file.createNewFile();
    		}
 
    		FileOutputStream fop=new FileOutputStream(file);
		//get the content in bytes
    		fop.write(content.getBytes());
    		fop.flush();
	        fop.close();
 
            System.out.println("Output File Successfully Written.");
 
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }
}

