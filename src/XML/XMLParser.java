package XML;
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
 * It assumes that all simulation XML files have tags type, name, author, gridWidth, and gridHeight
 * @author Aaron Chang
 *
 */
public class XMLParser {
	private static final String SIM_TYPE_TAG = "type";
	private static final String SIM_NAME_TAG = "name";
	private static final String AUTHOR_TAG = "author";
	private static final String GRID_WIDTH_TAG = "gridWidth";
	private static final String GRID_HEIGHT_TAG	 = "gridHeight";
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private Element ROOT;
	
	public XMLParser(String xmlFilename) {
		ROOT = getRootElement(xmlFilename);
	}
	
	//creates DocumentBuilder to navigate DOM tree
	protected static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) {
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
			throw new NullPointerException("The tag " + tagName + " does not exist");
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
		return getTextValueByTagName(SIM_TYPE_TAG);
	}
	
	/**
	 * returns name of simulation
	 * @return String - simulation name
	 */
	public String getSimulationName() {
		return getTextValueByTagName(SIM_NAME_TAG);
	}
	
	/**
	 * returns author of simulation
	 * @return String - author
	 */
	public String getAuthor() {
		return getTextValueByTagName(AUTHOR_TAG);
	}
	
	/**
	 * returns width of grid
	 * @return int - number of columns of cells
	 */
	public int getGridWidth() {
		return getIntValueByTagName(GRID_WIDTH_TAG);
	}
	
	/**
	 * returns height of grid
	 * @return int - number of rows of cells
	 */
	public int getGridHeight() {
		return getIntValueByTagName(GRID_HEIGHT_TAG);
	}
}
