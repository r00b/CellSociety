package xml;
import javafx.scene.paint.Color;
/**
 * GameOfLifeXMLParser is an XMLParser used specifically for game of life simulations
 * It has methods to retrieve attributes specific to game of life simulations (eg. probCellAlive)
 * It assumes that the Game of Life xml files have a tag called "probCellAlive"
 * If these tags do not exist, or if the data value is bad, it will use default values
 * @author Aaron Chang
 *
 */

public class GameOfLifeXMLParser extends XMLParser{
	
	public GameOfLifeXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * returns probability of each cell being alive at start of simulation
	 * @return int - percentage probability (out of 100) of cell being alive 
	 */
	public int getProbOfCellAlive() {
		String defaultProbAlive = myDefaultValueResources.getString("defaultProbCellAlive");
		return getIntValueByTagName(myXmlTagResources.getString("probCellAliveTag"), defaultProbAlive);
	}
	
	public Color getAliveColor() {
		String defaultAliveColor = myDefaultValueResources.getString("defaultAliveColor");
		return getColor("aliveColorTag", defaultAliveColor);
	}
	
	public Color getDeadColor() {
		String defaultDeadColor = myDefaultValueResources.getString("defaultDeadColor");
		return getColor("deadColorTag", defaultDeadColor);
	}
}
