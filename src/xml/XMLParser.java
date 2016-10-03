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

import javafx.scene.paint.Color;

/**
 * An XMLParser reads an XML file and has methods to return elements of that file by their tag
 * It is used by the different simulations of CellSociety
 * Each simulation will have an XMLParser object which it can use to get values like name, size, etc.
 * 
 * The XMLParser class depends on the XMLParserException class for exception handling
 * It assumes that all simulation XML files have tags type, name, author, gridWidth, and gridHeight
 * It uses XmlTags.properties to get the tags that it uses for searching through the XML files
 * It also uses DefaultParameters.properties to retrieve default values if the xml files contain bad/nonexistent data
 * @author Aaron Chang
 *
 */
public class XMLParser {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String XML_TAGS = "XmlTags";
	public static final String DEFAULT_PARAMETERS = "DefaultParameters";
	protected static ResourceBundle myXmlTagResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + XML_TAGS);
	protected static ResourceBundle myDefaultValueResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_PARAMETERS);
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
			//print error message: Error reading file or file DNE
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
	public String getTextValueByTagName(String tagName, String defaultValue) {
		NodeList nodeList = ROOT.getElementsByTagName(tagName);
		if (nodeList != null && nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent();
		}
		else {
			//if tag name does not exist in XML file, return default value of that parameter
			return defaultValue;
		}
	}
	
	/**
	 * This method returns takes the name of an xml tag and returns the int value of the node
	 * Used by XMLParser subclasses to retrieve specific data from xml files
	 * Depends on getTextValueByTagname
	 * @param String - tagName
	 * @return int value of node
	 */
	public int getIntValueByTagName(String tagName, String defaultStringValue) {
		try {
			int intValue = Integer.parseInt(getTextValueByTagName(tagName, defaultStringValue));
			return intValue;
		}
		catch (NumberFormatException e) {
			int intValue = Integer.parseInt(defaultStringValue);
			return intValue;
		}
	}
	
	public float getFloatValueByTagName(String tagName, String defaultStringValue) {
		try {
			float floatValue = Float.valueOf(getTextValueByTagName(tagName, defaultStringValue));
			return floatValue;
		}
		catch (NumberFormatException e) {
			float floatValue= Float.valueOf(defaultStringValue);
			return floatValue;
		}
	}
	
	public Color getColor(String colorTag, String defaultColorString) {
		try {
			String colorString = getTextValueByTagName(myXmlTagResources.getString(colorTag), defaultColorString);
			return Color.valueOf(colorString);
		}
		catch (IllegalArgumentException e) {
			return Color.valueOf(defaultColorString);
		}
	}
	
	/**
	 * returns width of grid
	 * @return int - number of columns of cells
	 */
	public int getGridWidth() {
		String defaultGridWidth = myDefaultValueResources.getString("defaultGridWidth");
		return getIntValueByTagName(myXmlTagResources.getString("gridWidthTag"), defaultGridWidth);
	}
	
	/**
	 * returns height of grid
	 * @return int - number of rows of cells
	 */
	public int getGridHeight() {
		String defaultGridHeight = myDefaultValueResources.getString("defaultGridHeight");
		return getIntValueByTagName(myXmlTagResources.getString("gridHeightTag"), defaultGridHeight);
	}
	
	/**
	 * returns number of vertices for grid cell
	 * @return int the number of vertices
	 */
	public int getNumCellVertices() {
		String defaultNumCellVertices = myDefaultValueResources.getString("defaultNumCellVertices");
		return getIntValueByTagName(myXmlTagResources.getString("numCellVerticesTag"), defaultNumCellVertices);
	}
}
