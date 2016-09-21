import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLParser {
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	
	//creates DocumentBuilder to navigate DOM tree
	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
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
}
