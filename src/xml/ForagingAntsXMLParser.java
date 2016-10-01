package xml;

public class ForagingAntsXMLParser extends XMLParser{
	
	public ForagingAntsXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	public int getNestILocation() {
		String defaultLocation = myDefaultValueResources.getString("defaultNestLocationI");
		return getIntValueByTagName(myXmlTagResources.getString("NestLocationITag"), defaultLocation);
	}
}
