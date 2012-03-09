package taskmanager.model;

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.*;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLParser
{
    // Possible XML Tags, defined across all XML documents
    static final String TASK="task";
    static final String TASKS = "tasks";
    static final String PEOPLE = "people";
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

    // Pattern identifiers, for validation purposes
    static final String PATTERN_IDENTIFIER = "^[0-9]+$";
    static final String PATTERN_TITLE = "^[a-zA-Z -]+$";
    static final String PATTERN_DESCRIPTION = "^(?:\\p{L}\\p{M}*|[\\ ,.-])*$";
    static final String PATTERN_DURATION = "^[0-9]+$";

    static final String XML_PEOPLE = "people.xml";
    static final String XML_TASKS = "tasks.xml";
    
    public void writeTasks(List<Task> alTasks)
    {
        String configFile = XML_TASKS;
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        
        try 
        {
            XMLStreamWriter writer = new IndentingXMLStreamWriter(factory.createXMLStreamWriter(new FileWriter(configFile)));
            writer.writeStartDocument();
            writer.writeStartElement(TASKS);

            for (int i = 0; i < alTasks.size(); i++)
            {
                writer.writeStartElement(TASK);
                
                writer.writeStartElement(IDENTIFIER);
                writer.writeCharacters(alTasks.get(i).getIdentifier());
                writer.writeEndElement();

                writer.writeStartElement(TITLE);
                writer.writeCharacters(alTasks.get(i).getTitle());
                writer.writeEndElement();

                writer.writeStartElement(DESCRIPTION);
                writer.writeCharacters(alTasks.get(i).getDescription());
                writer.writeEndElement();

                writer.writeStartElement(DURATION);
                writer.writeCharacters(alTasks.get(i).getDuration());
                writer.writeEndElement();

                writer.writeStartElement(DELIVERABLE);
                writer.writeCharacters(alTasks.get(i).getDelivarable());
                writer.writeEndElement();

                writer.writeStartElement(DEADLINE);
                writer.writeCharacters(alTasks.get(i).getDeadline());
                writer.writeEndElement();

                writer.writeStartElement(PEOPLEASSIGNED);

                for (int j = 0; j < alTasks.get(i).getPeopleassigned().size(); j++)
                {
                    writer.writeStartElement(ID);
                    writer.writeCharacters(alTasks.get(i).getPeopleassigned().get(j));
                    writer.writeEndElement();
                }

                writer.writeEndElement();

                writer.writeStartElement(COMPLETION);
                writer.writeCharacters(alTasks.get(i).getCompletion());
                writer.writeEndElement();

                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();
        }
        catch (XMLStreamException e) {System.out.println(e.getMessage());}
        catch (IOException e) {System.out.println(e.getMessage());}
    }

    public void writePeople(List<Person> alPerson)
    {
        String configFile = XML_PEOPLE;
        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try 
        {
            XMLStreamWriter writer = new IndentingXMLStreamWriter(factory.createXMLStreamWriter(new FileWriter(configFile)));
            writer.writeStartDocument();
            writer.writeStartElement(PEOPLE);


            for (int i = 0; i < alPerson.size(); i++)
            {
                writer.writeStartElement(PERSON);

                writer.writeStartElement(IDENTIFIER);
                writer.writeCharacters(alPerson.get(i).getIdentifier());
                writer.writeEndElement();

                writer.writeStartElement(FNAME);
                writer.writeCharacters(alPerson.get(i).getFName());
                writer.writeEndElement();

                writer.writeStartElement(LNAME);
                writer.writeCharacters(alPerson.get(i).getLName());
                writer.writeEndElement();

                writer.writeStartElement(JOBTITLE);
                writer.writeCharacters(alPerson.get(i).getJobTitle());
                writer.writeEndElement();

                writer.writeStartElement(JOBDESCRIPTION);
                writer.writeCharacters(alPerson.get(i).getJobDescription());
                writer.writeEndElement();

                writer.writeStartElement(CLEARANCE);
                writer.writeCharacters(alPerson.get(i).getClearance());
                writer.writeEndElement();

                writer.writeEndElement();
            }
            
            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();
        }
        catch (XMLStreamException e) {System.out.println(e.getMessage());}
        catch (IOException e){System.out.println(e.getMessage());}
    }

    public List<Task> readTasks() 
    {
        ArrayList<String> taskidentifiers = new ArrayList<String>();
        
        String configFile = XML_TASKS;
        List<Task> tasks = new ArrayList<Task>();
        
        try 
        {
            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            
            // Setup a new eventReader
            InputStream in = new FileInputStream(configFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            
            // Read the XML document
            Task task = null;
            boolean validtask = true;
            
            while (eventReader.hasNext()) 
            {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) 
                {
                    StartElement startElement = event.asStartElement();
                    // If we have a item element we create a new item
                    if (startElement.getName().getLocalPart().equals(TASK)) 
                        task = new Task();
                            
                    if (event.isStartElement()) 
                    {
                        if (event.asStartElement().getName().getLocalPart().equals(IDENTIFIER)) {
                                event = eventReader.nextEvent();

                            // Make sure the field is valid
                            if(!event.isCharacters() || !event.asCharacters().getData().matches(PATTERN_IDENTIFIER))
                            {
                                    //checks empty and pattern
                                    System.out.println("Error: Task encountered with an invalid identifier. This entry will be skipped.");
                                    validtask = false;
                                    continue;
                            }
                            else if(taskidentifiers.contains(event.asCharacters().getData()))
                            {
                                    //check for duplicates
                                    System.out.println("Error: Task identifier '" + event.asCharacters().getData() + "' already exists. This entry will be skipped.");
                                    validtask = false;
                                    continue;
                            }

                            taskidentifiers.add(event.asCharacters().getData());
                            task.setIdentifier(event.asCharacters().getData());
                            continue;
                        }
                    }
                            if (event.asStartElement().getName().getLocalPart().equals(TITLE)) {
                                    event = eventReader.nextEvent();

                                    // Make sure the field is valid
                                    if(!event.isCharacters() || !event.asCharacters().getData().matches(PATTERN_TITLE)){
                                            System.out.println("Warning: Task " + task.getIdentifier() + " has an invalid title.");
                                            continue;
                                    }

                                    task.setTitle(event.asCharacters().getData());
                                    continue;
                            }

                            if (event.asStartElement().getName().getLocalPart().equals(DESCRIPTION)) {
                                    event = eventReader.nextEvent();

                                    // Make sure the field is valid
                                    if(!event.isCharacters() || !event.asCharacters().getData().matches(PATTERN_DESCRIPTION)){
                                            System.out.println("Warning: Task " + task.getIdentifier() + " has an invalid description.");
                                            continue;
                                    }

                                    task.setDescription(event.asCharacters().getData());
                                    continue;
                            }

                            if (event.asStartElement().getName().getLocalPart().equals(DURATION)) {
                                    event = eventReader.nextEvent();

                                    // Make sure the field is valid
                                    if(!event.isCharacters() || !event.asCharacters().getData().matches(PATTERN_DURATION)){
                                            System.out.println("Error: Task " + task.getIdentifier() + " has an invalid duration. This entry will be skipped.");
                                            validtask = false;
                                            continue;
                                    }

                                    task.setDuration(event.asCharacters().getData());
                                    continue;
                            }
                            if (event.asStartElement().getName().getLocalPart().equals(DELIVERABLE)) {
                                    event = eventReader.nextEvent();

                                    // Make sure the field is valid
                                    if(!event.isCharacters()){
                                            System.out.println("Warning: Task " + task.getIdentifier() + " has a blank deliverable.");
                                            continue;
                                    }

                                    task.setDeliverable(event.asCharacters().getData());
                                    continue;
                            }
                            if (event.asStartElement().getName().getLocalPart().equals(STARTDATE)) {
                                    event = eventReader.nextEvent();

                                    // Make sure the field is valid
                                    if(!event.isCharacters()){
                                            System.out.println("Warning: Task " + task.getIdentifier() + " has a blank start date.");
                                            continue;
                                    }

                                    task.setDeadline(event.asCharacters().getData());
                                    continue;
                            }
                            if (event.asStartElement().getName().getLocalPart().equals(DEADLINE)) {
                                    event = eventReader.nextEvent();

                                    // Make sure the field is valid
                                    if(!event.isCharacters()){
                                            System.out.println("Warning: Task " + task.getIdentifier() + " has a blank deadline.");
                                            continue;
                                    }

                                    task.setDeadline(event.asCharacters().getData());
                                    continue;
                            }
                            if (event.asStartElement().getName().getLocalPart().equals(COMPLETION)) {
                                    event = eventReader.nextEvent();

                                    // Make sure the field is valid
                                    if(!event.isCharacters()){
                                            System.out.println("Warning: Task " + task.getIdentifier() + " has a blank completion.");
                                            continue;
                                    }

                                    task.setCompletion(event.asCharacters().getData());
                                    continue;
                            }
                            if (event.asStartElement().getName().getLocalPart().equals(ID)) {					

                                event = eventReader.nextEvent();

                                // Make sure the field is valid
                                if(!event.isCharacters()){
                                    System.out.println("Warning: Task " + task.getIdentifier() + " has a blank assignee ID.");
                                    continue;
                                }
                                task.setPeopleassigned(event.asCharacters().getData());
                                continue;

                            }
                    }
                    // If we reach the end of an item element we add it to the list
                    if (event.isEndElement()) 
                    {
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equals(TASK) && validtask == true) {
                            if(task.getIdentifier() == null || task.getDuration() == null || task.getPeopleassigned().isEmpty()){
                                //CHECKS ELEMENT EXISTS --> OTHERWISE, ERROR --> GO TO NEXT NODE/TASK
                                System.out.println("Error: Task has undefined identifier or duration, or has nobody assigned to it. This entry will be skipped.");
                            }
                            else //TEMP OF GOOD TASKS
                                tasks.add(task);
                            
                        }
                        else if(endElement.getName().getLocalPart().equals(TASK)){
                                validtask = true;//reset valid task
                        }
                    }

            }
        } catch (FileNotFoundException e) {
                System.out.println("XML File cannot be found. Unable to Read.");
                System.exit(0); 

                //e.printStackTrace();
        } catch (XMLStreamException e) {
                System.out.println("XML File is blank or has an invalid format. Cancelling Read.");
                System.exit(0); 

                //e.printStackTrace();
        } 
        return tasks;
    }

    public List<Person> readPeople() {
        
            ArrayList<String> peopleidentifiers = new ArrayList<String>();
            
            String configFile = XML_PEOPLE;
            List<Person> people = new ArrayList<Person>();
            try {
                    // First create a new XMLInputFactory
                    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                    // Setup a new eventReader
                    InputStream in = new FileInputStream(configFile);
                    XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
                    // Read the XML document
                    Person person = null;
                    boolean validperson = true;
                    while (eventReader.hasNext()) {
                            XMLEvent event = eventReader.nextEvent();

                            if (event.isStartElement()) {
                                    StartElement startElement = event.asStartElement();
                                    // If we have a item element we create a new item
                                    if (startElement.getName().getLocalPart().equals(PERSON)) {
                                            person = new Person();
                                    }

                                    if (event.isStartElement()) {
                                            if (event.asStartElement().getName().getLocalPart().equals(IDENTIFIER)) {

                                                    event = eventReader.nextEvent();

                                                    // Make sure the field is valid
                                                    if(!event.isCharacters() || !event.asCharacters().getData().matches(PATTERN_IDENTIFIER)){
                                                            System.out.println("Error: Person encountered with a blank or invalid identifier. This entry will be skipped.");
                                                            validperson = false;
                                                            continue;
                                                    }
                                                    else if(peopleidentifiers.contains(event.asCharacters().getData())){
                                                            System.out.println("Error: Person identifier '" + event.asCharacters().getData() + "' already exists. This entry will be skipped.");
                                                            validperson = false;
                                                            continue;
                                                    }

                                                    peopleidentifiers.add(event.asCharacters().getData());
                                                    person.setIdentifier(event.asCharacters().getData());
                                                    continue;
                                            }
                                    }
                                    if (event.asStartElement().getName().getLocalPart().equals(FNAME)) {
                                            event = eventReader.nextEvent();

                                            // Make sure the field is valid
                                            if(!event.isCharacters() || !event.asCharacters().getData().matches(PATTERN_TITLE)){
                                                    System.out.println("Error: Person encountered with a blank or invalid name. This entry will be skipped.");
                                                    validperson = false;
                                                    continue;
                                            }

                                            person.setFName(event.asCharacters().getData());
                                            continue;
                                    }

                                    if (event.asStartElement().getName().getLocalPart().equals(LNAME)) {
                                            event = eventReader.nextEvent();

                                            // Make sure the field is valid
                                            if(!event.isCharacters() || !event.asCharacters().getData().matches(PATTERN_TITLE)){
                                                    System.out.println("Error: Person encountered with a blank or invalid name. This entry will be skipped.");
                                                    validperson = false;
                                                    continue;
                                            }

                                            person.setLName(event.asCharacters().getData());
                                            continue;
                                    }
                                    if (event.asStartElement().getName().getLocalPart().equals(JOBTITLE)) {
                                            event = eventReader.nextEvent();

                                            // Make sure the field is valid
                                            if(!event.isCharacters()){
                                                    System.out.println("Warning: Task " + person.getIdentifier() + " has a blank or invalid job title.");
                                                    continue;	
                                            }

                                            person.setJobTitle(event.asCharacters().getData());
                                            continue;
                                    }

                                    if (event.asStartElement().getName().getLocalPart().equals(JOBDESCRIPTION)) {
                                            event = eventReader.nextEvent();

                                            // Make sure the field is valid
                                            if(!event.isCharacters() || !event.asCharacters().getData().matches(PATTERN_DESCRIPTION)){
                                                    System.out.println("Warning: Task " + person.getIdentifier() + " has a blank or invalid job description.");
                                                    continue;	
                                            }

                                            person.setJobDescription(event.asCharacters().getData());
                                            continue;
                                    }

                                    if (event.asStartElement().getName().getLocalPart().equals(CLEARANCE)) {
                                            event = eventReader.nextEvent();

                                            // Make sure the field is valid
                                            if(!event.isCharacters()){
                                                    System.out.println("Warning: Person " + person.getIdentifier() + " has a blank clearance.");
                                                    continue;
                                            }

                                            person.setClearance(event.asCharacters().getData());
                                            continue;
                                    }

                            }
                            // If we reach the end of an item element we add it to the list
                            if (event.isEndElement()) {
                                    EndElement endElement = event.asEndElement();
                                    if (endElement.getName().getLocalPart().equals(PERSON) && validperson == true) {
                                            if(person.getIdentifier() == null || person.getFName() == null || person.getLName() == null){
                                                    System.out.println("Error: Person has undefined identifier or name. This entry will be skipped.");
                                            }
                                            else{
                                                    people.add(person);
                                            }		
                                    }
                                    else if(endElement.getName().getLocalPart().equals(PERSON)){
                                            validperson = true;
                                    }
                            }

                    }
            }
            catch (FileNotFoundException e)
            {
                System.out.println("XML File cannot be found. Unable to Read.");
                System.exit(0); 
            }
            catch (XMLStreamException e)
            {
                System.out.println("XML File is blank or has an invalid format. Cancelling Read.");
                System.exit(0); 
            } 
            return people;
    }
}

