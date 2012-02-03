package model;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


public class StaXParser {
	static final String TASK="task";
	static final String PERSON="person";
	static final String SUBTASK="subtask";
	static final String IDENTIFIER = "identifier";
	static final String ID = "id";
	static final String PARENTID = "parentid";
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String DURATION = "duration";
	static final String DELIVERABLE = "deliverable";
	static final String STARTDATE = "startdate";
	static final String DEADLINE = "deadline";
	static final String SUBTASKS = "subtasks";
	static final String COMPLETION = "completion";
	static final String PRIORITY = "priority";
	static final String PEOPLEASSIGNED = "peopleassigned";
	static final String FNAME = "fname";
	static final String LNAME = "lname";
	static final String JOBTITLE = "jobtitle";
	static final String JOBDESCRIPTION = "jobdescription";
	static final String CLEARANCE = "clearance";

	public List<Task> readTasks(String configFile) {
		List<Task> tasks = new ArrayList<Task>();
		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
			Task task = null;

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					// If we have a item element we create a new item
					if (startElement.getName().getLocalPart() == (TASK)) {
						task = new Task();
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
							.equals(STARTDATE)) {
						event = eventReader.nextEvent();
						task.setDeadline(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(DEADLINE)) {
						event = eventReader.nextEvent();
						task.setDeadline(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(COMPLETION)) {
						event = eventReader.nextEvent();
						task.setCompletion(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart().equals(ID)) {					
						
						event = eventReader.nextEvent();

						task.setPeopleassigned(event.asCharacters().getData());
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
	
	public List<Subtask> readSubtasks(String configFile) {
		List<Subtask> subtasks = new ArrayList<Subtask>();
		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
			Subtask subtask = null;

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					// If we have a item element we create a new item
					if (startElement.getName().getLocalPart() == (SUBTASK)) {
						subtask = new Subtask();
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
							subtask.setIdentifier(event.asCharacters().getData());
							continue;
						}
					}
					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(PARENTID)) {
							event = eventReader.nextEvent();
							subtask.setParentID(event.asCharacters().getData());
							continue;
						}
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(TITLE)) {
						event = eventReader.nextEvent();
						subtask.setTitle(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(DESCRIPTION)) {
						event = eventReader.nextEvent();
						subtask.setDescription(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(DURATION)) {
						event = eventReader.nextEvent();
						subtask.setDuration(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(PRIORITY)) {
						event = eventReader.nextEvent();
						subtask.setPriority(event.asCharacters().getData());
						continue;
					}

				}
				// If we reach the end of an item element we add it to the list
				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (SUBTASK)) {
						subtasks.add(subtask);
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return subtasks;
	}
	
	public List<Person> readPeople(String configFile) {
		List<Person> people = new ArrayList<Person>();
		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
			Person person = null;

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					// If we have a item element we create a new item
					if (startElement.getName().getLocalPart() == (PERSON)) {
						person = new Person();
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
							person.setIdentifier(event.asCharacters().getData());
							continue;
						}
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(FNAME)) {
						event = eventReader.nextEvent();
						person.setFName(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(LNAME)) {
						event = eventReader.nextEvent();
						person.setLName(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(JOBTITLE)) {
						event = eventReader.nextEvent();
						person.setJobTitle(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(JOBDESCRIPTION)) {
						event = eventReader.nextEvent();
						person.setJobDescription(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(CLEARANCE)) {
						event = eventReader.nextEvent();
						person.setClearance(event.asCharacters().getData());
						continue;
					}
	
				}
				// If we reach the end of an item element we add it to the list
				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (PERSON)) {
						people.add(person);
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return people;
	}

}

