import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * An XMLParser reads an XML file and has methods to return elements of that file by their tag
 * It is used by the different simulations of CellSociety
 * Each simulation will have an XMLParser object which it can use to get values like name, size, etc.
 * 
 * The XMLParser class depends on the XMLParserException class for exception handling
 * @author Aaron Chang
 *
 */
public class XMLParser {
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private Element ROOT;
	
	public XMLParser(String xmlFilename) {
		ROOT = getRootElement(xmlFilename);
	}
	
	//creates DocumentBuilder to navigate DOM tree
	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) {
			throw new XMLParserException(e);
		}
		
	}
	
	private Element getRootElement(String xmlFileName) {
		DOCUMENT_BUILDER.reset();
		Document xmlDocument;
		try {
			xmlDocument = DOCUMENT_BUILDER.parse(xmlFileName);
			return xmlDocument.getDocumentElement();
		} 
		catch (SAXException | IOException e) {
			throw new XMLParserException(e);
		}
	}
	
	/**
	 * This method should take the name of a tag and return the node
	 * The Simulation classes will call this method to get necessary game parameters
	 * It assumes that the Simulation class knows the tag names
	 * @param String - tagName: name of tag in XML file
	 * @return String value of the node in XML file
	 */
	public String getTextValueByTagName(String tagName) {
		NodeList nodeList = ROOT.getElementsByTagName(tagName);
		if (nodeList != null && nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent();
		}
		else {
			//need to implement a more robust else case (if can't find tag name)
			throw new NullPointerException("The tag " + tagName + " does not exist");
		}
	}
	
	/**
	 * This method returns takes the name of an xml tag and returns the int value of the node
	 * Used by Simulation objects to retrieve data from xml files
	 * Depends on getTextValueByTagname
	 * @param String - tagName
	 * @return int value of node
	 */
	public int getIntValueByTagName(String tagName) {
		return Integer.parseInt(getTextValueByTagName(tagName));
	}
	
	
	
}
