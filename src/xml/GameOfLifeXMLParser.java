package xml;
import javafx.scene.paint.Color;
/**
 * GameOfLifeXMLParser is an XMLParser used specifically for game of life simulations
 * It has methods to retrieve attributes specific to game of life simulations (eg. probCellAlive)
 * It assumes that the Game of Life xml files have a tag called "probCellAlive"
 * @author Aaron Chang
 *
 */

public class GameOfLifeXMLParser extends XMLParser{
	public static final String DEFAULT_ALIVE_COLOR = myDefaultValueResources.getString("defaultAliveColor");
	public static final String DEFAULT_DEAD_COLOR = myDefaultValueResources.getString("defaultDeadColor");
	public static final String DEFAULT_PROB_CELL_ALIVE = myDefaultValueResources.getString("defaultProbCellAlive");
	
	public GameOfLifeXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * returns probability of each cell being alive at start of simulation
	 * @return int - percentage probability (out of 100) of cell being alive 
	 */
	public int getProbOfCellAlive() {
		return getIntValueByTagName(myXmlTagResources.getString("probCellAliveTag"), DEFAULT_PROB_CELL_ALIVE);
	}
	
	public Color getAliveColor() {
		String colorString = getTextValueByTagName(myXmlTagResources.getString("aliveColorTag"), DEFAULT_ALIVE_COLOR);
		return Color.valueOf(colorString);
	}
	
	public Color getDeadColor() {
		String colorString;
		colorString = getTextValueByTagName(myXmlTagResources.getString("deadColorTag"), DEFAULT_DEAD_COLOR);
		return Color.valueOf(colorString);
	}
}
