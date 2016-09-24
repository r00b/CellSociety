package xml;
/**
 * WatorXMLParser is an XMLParser used specifically for Wa-Tor World simulations
 * It has methods to retrieve attributes specific to Wa-Tor simulations (eg. sharkBreedTime)
 * It assumes that the Wa-Tor xml files have tags called fishBreedTime, sharkBreedTime, etc.
 * @author Aaron Chang
 *
 */
public class WatorXMLParser extends XMLParser{
	private static final String FISH_BREED_TIME_TAG = "fishBreedTime";
	private static final String SHARK_BREED_TIME_TAG = "sharkBreedTime";
	private static final String SHARK_STARVE_TIME_TAG = "sharkStarveTime";
	private static final String PERCENT_SHARK_TAG = "percentShark";
	private static final String PERCENT_FISH_TAG = "percentFish";

	
	public WatorXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * gets required time a fish must survive in order for it to breed
	 * @return int - number of rounds a fish must survive to breed
	 */
	public int getFishBreedTime() {
		return getIntValueByTagName(FISH_BREED_TIME_TAG);
	}
	
	/**
	 * gets required time a shark must survive in order for it to breed
	 * @return int - number of rounds a shark must survive to breed
	 */
	public int getSharkBreedTime() {
		return getIntValueByTagName(SHARK_BREED_TIME_TAG);
	}
	
	/**
	 * gets maximum time a shark can go without eating before dying
	 * @return int- number of rounds a shark can go without eating before dying
	 */
	public int getSharkStarveTime() {
		return getIntValueByTagName(SHARK_STARVE_TIME_TAG);
	}
	
	/**
	 * gets percent of grid that will be populated by sharks
	 * @return int - percent (out of 100) of grid populated by sharks
	 */
	public int getPercentShark() {
		return getIntValueByTagName(PERCENT_SHARK_TAG);
	}
	
	/**
	 * gets percent of grid that will be populated by fish
	 * @return int - percent (out of 100) of grid populated by fish
	 */
	public int getPercentFish() {
		return getIntValueByTagName(PERCENT_FISH_TAG);
	}
	
	/**
	 * gets percent of grid that will be populated by water (empty cells)
	 * @return int - percent (out of 100) of grid populated by water
	 */
	public int getPercentEmpty() {
		return 100 - (getPercentShark() + getPercentFish());
	}
	
	
	
}
