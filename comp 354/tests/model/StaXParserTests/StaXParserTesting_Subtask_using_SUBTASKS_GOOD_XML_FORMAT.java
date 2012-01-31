package model.StaXParserTests;

import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static org.junit.Assert.*;

import junit.framework.Assert;

import org.junit.Test;

//@RunWith(Parameterized.class)
public class StaXParserTesting_Subtask_using_SUBTASKS_GOOD_XML_FORMAT {

	//private static StaXParser __StaXParser__;
	
	private static String dataFile = "tests/resources/SUBTASKS_GOOD_XML_FORMAT.xml";
	private static String dataFile2 = "tests/resources/SUBTASK_DTD_SCHEMA.dtd";

	/*
	public StaXParserTest_using_BAD_XML (	String _dummy_, 	Boolean _b1_) {				
 		//__StaXParser__= new StaXParser(); 
	}
	
	@Parameterized.Parameters 
    public static List<Object[]> data() {
 		System.out.println("Testing using file: "+ dataFile + " and using DTD schema: " + dataFile2 );   	
		System.out.println("___________________________________________________________________________________________________________" );   	
 		    	 
    	 return Arrays.asList(new Object[][] {
                 { "dummy", true }                                                   
         });    	 
    }
    */
	
	
	
	@Test
	public void test() {
		//fail("Not yet implemented");
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
										Assert.assertTrue(!true);
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
		                 transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dataFile2);  
		                 transformer.transform(source, result);  
			}  
		    catch (Exception e) {  
				System.out.println(e.getMessage());  
				Assert.assertTrue(!true);
		     }      	
	}

}
