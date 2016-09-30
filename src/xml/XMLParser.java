package xml;
import java.io.IOException;
import java.util.ResourceBundle;

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
 * It assumes that all simulation XML files have tags type, name, author, gridWidth, and gridHeight
 * @author Aaron Chang
 *
 */
public class XMLParser {
	protected ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
	protected static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	protected static final String LANGUAGE = "XmlTags";
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private Element ROOT;
	
	public XMLParser(String xmlFilename) {
		ROOT = getRootElement(xmlFilename);
	}
	
	//creates DocumentBuilder to navigate DOM tree
	//this method is static because it returns the value for a static constant
	protected static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) {
			//print error message (pop up screen)
			throw new XMLParserException(e);
		}
		
	}
	
	protected Element getRootElement(String xmlFileName) {
		DOCUMENT_BUILDER.reset();
		Document xmlDocument;
		try {
			xmlDocument = DOCUMENT_BUILDER.parse(xmlFileName);
			return xmlDocument.getDocumentElement();
		} 
		catch (SAXException | IOException e) {
			//print error message (pop up screen)
			throw new XMLParserException(e);
		}
	}
	
	/**
	 * This method should take the name of a tag and return the node
	 * The XMLParser subclasses will call this method to get necessary game parameters
	 * It assumes that the subclasses knows the tag names of the xml files
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
			throw new XMLParserException();
		}
	}
	
	/**
	 * This method returns takes the name of an xml tag and returns the int value of the node
	 * Used by XMLParser subclasses to retrieve specific data from xml files
	 * Depends on getTextValueByTagname
	 * @param String - tagName
	 * @return int value of node
	 */
	public int getIntValueByTagName(String tagName) {
		return Integer.parseInt(getTextValueByTagName(tagName));
	}
	
	/**
	 * returns type of simulation
	 * @return String - simulation type
	 */
	public String getSimulationType() {
		return getTextValueByTagName(myResources.getString("simTypeTag"));
	}
	
	/**
	 * returns name of simulation
	 * @return String - simulation name
	 */
	public String getSimulationName() {
		return getTextValueByTagName(myResources.getString("simNameTag"));
	}
	
	/**
	 * returns author of simulation
	 * @return String - author
	 */
	public String getAuthor() {
		return getTextValueByTagName(myResources.getString("authorTag"));
	}
	
	/**
	 * returns width of grid
	 * @return int - number of columns of cells
	 */
	public int getGridWidth() {
		return getIntValueByTagName(myResources.getString("gridWidthTag"));
	}
	
	/**
	 * returns height of grid
	 * @return int - number of rows of cells
	 */
	public int getGridHeight() {
		return getIntValueByTagName(myResources.getString("gridHeightTag"));
	}
}
