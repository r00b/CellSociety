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
	
	public FireXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * gets probability of a live tree to catch fire (if an adjacent tree is on fire?)
	 * @return int - percentage (out of 100) probability
	 */
	public int getProbCatchFire() {
		return getIntValueByTagName(myResources.getString("probCatchFireTag"));
	}
	
	/**
	 * gets time taken after a tree catches fire for it to burn down completely
	 * @return int - number of rounds after a tree catches fire for it to burn down
	 */
	public int getBurnDownTime() {
		return getIntValueByTagName(myResources.getString("burnDownTimeTag"));
	}
	
	public Color getBurningColor() {
		String colorString = getTextValueByTagName(myResources.getString("burningColorTag"));
		return Color.valueOf(colorString);
	}
	
	public Color getEmptyColor() {
		String colorString = getTextValueByTagName(myResources.getString("emptyColorTag"));
		return Color.valueOf(colorString);
	}
	public Color getTreeColor() {
		String colorString = getTextValueByTagName(myResources.getString("treeColorTag"));
		return Color.valueOf(colorString);
	}
}
