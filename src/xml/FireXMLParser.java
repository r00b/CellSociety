package xml;
import javafx.scene.paint.Color;

/**
 * FireXMLParser is an XMLParser used specifically for Fire Spreading simulations
 * It has methods to retrieve attributes specific to Fire simulations (eg. burnDownTime)
 * It assumes that the Fire files have tags called probCatchfire and burnDownTime
 * @author Aaron Chang
 *
 */
public class FireXMLParser extends XMLParser{
	public static final String DEFAULT_EMPTY_COLOR = myDefaultValueResources.getString("defaultBurntColor");
	public static final String DEFAULT_BURNING_COLOR = myDefaultValueResources.getString("defaultBurningColor");
	public static final String DEFAULT_TREE_COLOR = myDefaultValueResources.getString("defaultTreeColor");
	public static final String DEFAULT_BURNDOWN_TIME = myDefaultValueResources.getString("defaultBurnDownTime");
	public static final String DEFAULT_PROB_CATCH = myDefaultValueResources.getString("defaultProbCatch");

	public FireXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * gets probability of a live tree to catch fire (if an adjacent tree is on fire?)
	 * @return int - percentage (out of 100) probability
	 */
	public int getProbCatchFire() {
		return getIntValueByTagName(myXmlTagResources.getString("probCatchFireTag"), DEFAULT_PROB_CATCH);
	}
	
	/**
	 * gets time taken after a tree catches fire for it to burn down completely
	 * @return int - number of rounds after a tree catches fire for it to burn down
	 */
	public int getBurnDownTime() {
		return getIntValueByTagName(myXmlTagResources.getString("burnDownTimeTag"), DEFAULT_BURNDOWN_TIME);
	}
	
	public Color getBurningColor() {
		String colorString = getTextValueByTagName(myXmlTagResources.getString("burningColorTag"), DEFAULT_BURNING_COLOR);
		return Color.valueOf(colorString);
	}
	
	public Color getEmptyColor() {
		String colorString = getTextValueByTagName(myXmlTagResources.getString("emptyColorTag"), DEFAULT_EMPTY_COLOR);
		return Color.valueOf(colorString);
	}
	public Color getTreeColor() {
		String colorString = getTextValueByTagName(myXmlTagResources.getString("treeColorTag"), DEFAULT_TREE_COLOR);
		return Color.valueOf(colorString);
	}
}
