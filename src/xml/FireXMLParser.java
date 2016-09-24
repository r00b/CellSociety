package xml;
/**
 * FireXMLParser is an XMLParser used specifically for Fire Spreading simulations
 * It has methods to retrieve attributes specific to Fire simulations (eg. burnDownTime)
 * It assumes that the Fire files have tags called probCatchfire and burnDownTime
 * @author Aaron Chang
 *
 */
public class FireXMLParser extends XMLParser{
	private static final String PROB_CATCH_FIRE_TAG = "probCatchFire";
	private static final String BURNDOWN_TIME_TAG = "burnDownTime";
	
	public FireXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * gets probability of a live tree to catch fire (if an adjacent tree is on fire?)
	 * @return int - percentage (out of 100) probability
	 */
	public int getProbCatchFire() {
		return getIntValueByTagName(PROB_CATCH_FIRE_TAG);
	}
	
	/**
	 * gets time taken after a tree catches fire for it to burn down completely
	 * @return int - number of rounds after a tree catches fire for it to burn down
	 */
	public int getBurnDownTime() {
		return getIntValueByTagName(BURNDOWN_TIME_TAG);
	}
}
