package xml;
import javafx.scene.paint.Color;

/**
 * FireXMLParser is an XMLParser used specifically for Fire Spreading simulations
 * It has methods to retrieve attributes specific to Fire simulations (eg. burnDownTime)
 * It assumes that the Fire files have tags called probCatchfire and burnDownTime
 * If these tags do not exist, or if the values are bad, it replaces them with default values
 * @author Aaron Chang
 *
 */
public class FireXMLParser extends XMLParser{

	public FireXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * gets probability of a live tree to catch fire 
	 * If the value is not present, or if it does not exist, it is replaced by a default value
	 * @return int - percentage (out of 100) probability
	 */
	public int getProbCatchFire() {
		String defaultProbCatch = myDefaultValueResources.getString("defaultProbCatch");
		return getIntValueByTagName(myXmlTagResources.getString("probCatchFireTag"), defaultProbCatch);
	}
	public int getBurnDownTime() {
		String defaultBurndownTime = myDefaultValueResources.getString("defaultBurntColor");
		return getIntValueByTagName(myXmlTagResources.getString("burnDownTimeTag"), defaultBurndownTime);
	}
	
	public Color getBurningColor() {
		String defaultBurningColor = myDefaultValueResources.getString("defaultBurningColor");
		return getColor("burningColorTag", defaultBurningColor);
	}
	
	public Color getEmptyColor() {
		String defaultEmptyColor = myDefaultValueResources.getString("defaultBurntColor");
		return getColor("emptyColorTag", defaultEmptyColor);
	}
	public Color getTreeColor() {
		String defaultTreeColor = myDefaultValueResources.getString("defaultTreeColor");
		return getColor("treeColorTag", defaultTreeColor);
	}
}
