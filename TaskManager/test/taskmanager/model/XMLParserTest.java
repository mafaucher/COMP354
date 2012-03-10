/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package taskmanager.model;

import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class XMLParserTest {

    private static String PERSON_DTD_SCHEMA = "test/resources/PERSON_DTD_SCHEMA.dtd";
    private static String TASKS_DTD_SCHEMA = "test/resources/PERSON_DTD_SCHEMA.dtd";

    public XMLParserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // Test the XML Parser using a GOOD format for the People.xml
    @Test
    public void testXMLParser_PEOPLE_VALID() {
        String dataFile = "test/resources/PEOPLE_GOOD_XML_FORMAT.xml";
	
             try{
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    factory.setValidating(true);
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    builder.setErrorHandler(new org.xml.sax.ErrorHandler() {
                        //Ignore the fatal errors
                        public void fatalError(SAXParseException exception)
                            throws SAXException { }
                            //Validation errors
                        public void error(SAXParseException e)  throws SAXParseException {
                            System.out.println("Error at " +e.getLineNumber() + " line.");
                            System.out.println(e.getMessage());
                            assertTrue(!true);
                            //System.exit(0);
                        }
                        //Show warnings
                        public void warning(SAXParseException err)  throws SAXParseException{
                                System.out.println(err.getMessage());
                                //System.exit(0);
                        }
                    });
                     Document xmlDocument = builder.parse(new FileInputStream(dataFile));
                     DOMSource source = new DOMSource(xmlDocument);
                     StreamResult result = new StreamResult(System.out);
                     TransformerFactory tf = TransformerFactory.newInstance();
                     Transformer transformer = tf.newTransformer();
                     transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, PERSON_DTD_SCHEMA);
                     transformer.transform(source, result);
            }
            catch (Exception e) {
                        System.out.println(e.getMessage());
                        assertFalse(!true);
             }
    }

    // Test the XML Parser using a BAD format for the People.xml
    @Test
    public void testXMLParser_PEOPLE_INVALID() {
        String dataFile = "test/resources/PEOPLE_BAD_XML_FORMAT.xml";

             try{
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    factory.setValidating(true);
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    builder.setErrorHandler(new org.xml.sax.ErrorHandler() {
                        //Ignore the fatal errors
                        public void fatalError(SAXParseException exception)
                            throws SAXException { }
                            //Validation errors
                        public void error(SAXParseException e)  throws SAXParseException {
                            System.out.println("Error at " +e.getLineNumber() + " line.");
                            System.out.println(e.getMessage());
                            assertTrue(!true);
                            //System.exit(0);
                        }
                        //Show warnings
                        public void warning(SAXParseException err)  throws SAXParseException{
                                System.out.println(err.getMessage());
                                //System.exit(0);
                        }
                    });
                     Document xmlDocument = builder.parse(new FileInputStream(dataFile));
                     DOMSource source = new DOMSource(xmlDocument);
                     StreamResult result = new StreamResult(System.out);
                     TransformerFactory tf = TransformerFactory.newInstance();
                     Transformer transformer = tf.newTransformer();
                     transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, PERSON_DTD_SCHEMA);
                     transformer.transform(source, result);
            }
            catch (Exception e) {
                        System.out.println(e.getMessage());
                        assertFalse(!true);
             }
    }

    // Test the XML Parser using a GOOD format for the Tasks.xml
    @Test
    public void testXMLParser_TASKS_VALID() {
        String dataFile = "test/resources/TASKS_GOOD_XML_FORMAT.xml";
         try{
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setValidating(true);
                DocumentBuilder builder = factory.newDocumentBuilder();
                builder.setErrorHandler(new org.xml.sax.ErrorHandler() {
                   //Ignore the fatal errors
                   public void fatalError(SAXParseException exception)
                        throws SAXException { }
                        //Validation errors
                        public void error(SAXParseException e)  throws SAXParseException {
                                System.out.println("Error at " +e.getLineNumber() + " line.");
                                System.out.println(e.getMessage());
                                assertTrue(!true);
                                //System.exit(0);
                        }
                        //Show warnings
                        public void warning(SAXParseException err)  throws SAXParseException{
                                System.out.println(err.getMessage());
                                //System.exit(0);
                        }
                    });
                 Document xmlDocument = builder.parse(new FileInputStream(dataFile));
                 DOMSource source = new DOMSource(xmlDocument);
                 StreamResult result = new StreamResult(System.out);
                 TransformerFactory tf = TransformerFactory.newInstance();
                 Transformer transformer = tf.newTransformer();
                 transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, TASKS_DTD_SCHEMA);
                 transformer.transform(source, result);
                }
            catch (Exception e) {
                        System.out.println(e.getMessage());
                        assertTrue(!true);
             }
    }

    // Test the XML Parser using a BAD format for the Tasks.xml
    @Test
    public void testXMLParser_TASKS_INVALID() {
        String dataFile = "test/resources/TASKS_BAD_XML_FORMAT.xml";
         try{
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setValidating(true);
                DocumentBuilder builder = factory.newDocumentBuilder();
                builder.setErrorHandler(new org.xml.sax.ErrorHandler() {
                   //Ignore the fatal errors
                   public void fatalError(SAXParseException exception)
                        throws SAXException { }
                        //Validation errors
                        public void error(SAXParseException e)  throws SAXParseException {
                                System.out.println("Error at " +e.getLineNumber() + " line.");
                                System.out.println(e.getMessage());
                                assertTrue(!true);
                                //System.exit(0);
                        }
                        //Show warnings
                        public void warning(SAXParseException err)  throws SAXParseException{
                                System.out.println(err.getMessage());
                                //System.exit(0);
                        }
                    });
                 Document xmlDocument = builder.parse(new FileInputStream(dataFile));
                 DOMSource source = new DOMSource(xmlDocument);
                 StreamResult result = new StreamResult(System.out);
                 TransformerFactory tf = TransformerFactory.newInstance();
                 Transformer transformer = tf.newTransformer();
                 transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, TASKS_DTD_SCHEMA);
                 transformer.transform(source, result);
                }
            catch (Exception e) {
                        System.out.println(e.getMessage());
                        assertTrue(!true);
             }
	}
   

}