package xml;

import javafx.scene.paint.Color;

/**
 * WatorXMLParser is an XMLParser used specifically for Wa-Tor World simulations
 * It has methods to retrieve attributes specific to Wa-Tor simulations (eg. sharkBreedTime)
 * It assumes that the Wa-Tor xml files have tags called fishBreedTime, sharkBreedTime, etc.
 * If these tags do not exist, or if the data value is bad, it will use default values
 * @author Aaron Chang
 *
 */
public class WatorXMLParser extends XMLParser{

	public WatorXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * gets required time a fish must survive in order for it to breed
	 * @return int - number of rounds a fish must survive to breed
	 */
	public int getFishBreedTime() {
		String defaultFishBreedTime = myDefaultValueResources.getString("defaultFishBreedTime");
		return getIntValueByTagName(myXmlTagResources.getString("fishBreedTimeTag"), defaultFishBreedTime);
	}
	
	/**
	 * gets required time a shark must survive in order for it to breed
	 * @return int - number of rounds a shark must survive to breed
	 */
	public int getSharkBreedTime() {
		String defaultSharkBreedTime = myDefaultValueResources.getString("defaultSharkBreedTime");
		return getIntValueByTagName(myXmlTagResources.getString("sharkBreedTimeTag"), defaultSharkBreedTime);
	}
	
	/**
	 * gets maximum time a shark can go without eating before dying
	 * @return int- number of rounds a shark can go without eating before dying
	 */
	public int getSharkStarveTime() {
		String defaultSharkStarveTime = myDefaultValueResources.getString("defaultSharkStarveTime");
		return getIntValueByTagName(myXmlTagResources.getString("sharkStarveTimeTag"), defaultSharkStarveTime);
	}
	
	/**
	 * gets percent of grid that will be populated by sharks
	 * @return int - percent (out of 100) of grid populated by sharks
	 */
	public int getPercentShark() {
		String defaultPercentShark = myDefaultValueResources.getString("defaultPercentShark");
		return getIntValueByTagName(myXmlTagResources.getString("percentSharkTag"), defaultPercentShark);
	}
	
	/**
	 * gets percent of grid that will be populated by fish
	 * @return int - percent (out of 100) of grid populated by fish
	 */
	public int getPercentFish() {
		String defaultPercentFish = myDefaultValueResources.getString("defaultPercentFish");
		return getIntValueByTagName(myXmlTagResources.getString("percentFishTag"), defaultPercentFish);
	}
	
	/**
	 * gets percent of grid that will be populated by water (empty cells)
	 * @return int - percent (out of 100) of grid populated by water
	 */
	public int getPercentEmpty() {
		return 100 - (getPercentShark() + getPercentFish());
	}
	
	public Color getFishColor() {
		String defaultFishColor = myDefaultValueResources.getString("defaultFishColor");
		return getColor("fishColorTag", defaultFishColor);
	}
	
	public Color getSharkColor() {
		String defaultSharkColor = myDefaultValueResources.getString("defaultSharkColor");
		return getColor("sharkColorTag", defaultSharkColor);
	}
	
	public Color getEmptyColor() {
		String defaultEmptyColor = myDefaultValueResources.getString("defaultEmptyColor");
		return getColor("emptyColorTag", defaultEmptyColor);
	}
}
