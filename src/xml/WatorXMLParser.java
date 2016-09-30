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
	
	public WatorXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * gets required time a fish must survive in order for it to breed
	 * @return int - number of rounds a fish must survive to breed
	 */
	public int getFishBreedTime() {
		return getIntValueByTagName(myResources.getString("fishBreedTimeTag"));
	}
	
	/**
	 * gets required time a shark must survive in order for it to breed
	 * @return int - number of rounds a shark must survive to breed
	 */
	public int getSharkBreedTime() {
		return getIntValueByTagName(myResources.getString("sharkBreedTimeTag"));
	}
	
	/**
	 * gets maximum time a shark can go without eating before dying
	 * @return int- number of rounds a shark can go without eating before dying
	 */
	public int getSharkStarveTime() {
		return getIntValueByTagName(myResources.getString("sharkStarveTimeTag"));
	}
	
	/**
	 * gets percent of grid that will be populated by sharks
	 * @return int - percent (out of 100) of grid populated by sharks
	 */
	public int getPercentShark() {
		return getIntValueByTagName(myResources.getString("percentSharkTag"));
	}
	
	/**
	 * gets percent of grid that will be populated by fish
	 * @return int - percent (out of 100) of grid populated by fish
	 */
	public int getPercentFish() {
		return getIntValueByTagName(myResources.getString("percentFishTag"));
	}
	
	/**
	 * gets percent of grid that will be populated by water (empty cells)
	 * @return int - percent (out of 100) of grid populated by water
	 */
	public int getPercentEmpty() {
		return 100 - (getPercentShark() + getPercentFish());
	}
	
	public Color getFishColor() {
		String colorString = getTextValueByTagName(myResources.getString("fishColorTag"));
		return Color.valueOf(colorString);
	}
	
	public Color getSharkColor() {
		String colorString = getTextValueByTagName(myResources.getString("sharkColorTag"));
		return Color.valueOf(colorString);
	}
	
	public Color getEmptyColor() {
		String colorString = getTextValueByTagName(myResources.getString("emptyColorTag"));
		return Color.valueOf(colorString);
	}
	
	
	
}
