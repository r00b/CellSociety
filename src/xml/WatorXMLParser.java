package xml;

import javafx.scene.paint.Color;

/**
 * WatorXMLParser is an XMLParser used specifically for Wa-Tor World simulations
 * It has methods to retrieve attributes specific to Wa-Tor simulations (eg. sharkBreedTime)
 * It assumes that the Wa-Tor xml files have tags called fishBreedTime, sharkBreedTime, etc.
 * @author Aaron Chang
 *
 */
public class WatorXMLParser extends XMLParser{
	public static final String DEFAULT_SHARK_COLOR = myDefaultValueResources.getString("defaultSharkColor");
	public static final String DEFAULT_WATER_COLOR = myDefaultValueResources.getString("defaultWaterColor");
	public static final String DEFAULT_FISH_COLOR = myDefaultValueResources.getString("defaultFishColor");
	public static final String DEFAULT_PERCENT_SHARK = myDefaultValueResources.getString("defaultPercentShark");
	public static final String DEFAULT_PERCENT_FISH = myDefaultValueResources.getString("defaultPercentFish");
	public static final String DEFAULT_FISH_BREED_TIME = myDefaultValueResources.getString("defaultFishBreedTime");
	public static final String DEFAULT_SHARK_BREED_TIME = myDefaultValueResources.getString("defaultSharkBreedTime");
	public static final String DEFAULT_SHARK_STARVE_TIME = myDefaultValueResources.getString("defaultSharkStarveTime");

	public WatorXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * gets required time a fish must survive in order for it to breed
	 * @return int - number of rounds a fish must survive to breed
	 */
	public int getFishBreedTime() {
		return getIntValueByTagName(myXmlTagResources.getString("fishBreedTimeTag"), DEFAULT_FISH_BREED_TIME);
	}
	
	/**
	 * gets required time a shark must survive in order for it to breed
	 * @return int - number of rounds a shark must survive to breed
	 */
	public int getSharkBreedTime() {
		return getIntValueByTagName(myXmlTagResources.getString("sharkBreedTimeTag"), DEFAULT_SHARK_BREED_TIME);
	}
	
	/**
	 * gets maximum time a shark can go without eating before dying
	 * @return int- number of rounds a shark can go without eating before dying
	 */
	public int getSharkStarveTime() {
		return getIntValueByTagName(myXmlTagResources.getString("sharkStarveTimeTag"), DEFAULT_SHARK_STARVE_TIME);
	}
	
	/**
	 * gets percent of grid that will be populated by sharks
	 * @return int - percent (out of 100) of grid populated by sharks
	 */
	public int getPercentShark() {
		return getIntValueByTagName(myXmlTagResources.getString("percentSharkTag"), DEFAULT_PERCENT_SHARK);
	}
	
	/**
	 * gets percent of grid that will be populated by fish
	 * @return int - percent (out of 100) of grid populated by fish
	 */
	public int getPercentFish() {
		return getIntValueByTagName(myXmlTagResources.getString("percentFishTag"), DEFAULT_PERCENT_FISH);
	}
	
	/**
	 * gets percent of grid that will be populated by water (empty cells)
	 * @return int - percent (out of 100) of grid populated by water
	 */
	public int getPercentEmpty() {
		return 100 - (getPercentShark() + getPercentFish());
	}
	
	public Color getFishColor() {
		String colorString = getTextValueByTagName(myXmlTagResources.getString("fishColorTag"), DEFAULT_FISH_COLOR);
		return Color.valueOf(colorString);
	}
	
	public Color getSharkColor() {
		String colorString = getTextValueByTagName(myXmlTagResources.getString("sharkColorTag"), DEFAULT_SHARK_COLOR);
		return Color.valueOf(colorString);
	}
	
	public Color getEmptyColor() {
		String colorString = getTextValueByTagName(myXmlTagResources.getString("emptyColorTag"), DEFAULT_WATER_COLOR);
		return Color.valueOf(colorString);
	}
	
	
	
}
