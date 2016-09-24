package xml;
/**
 * GameOfLifeXMLParser is an XMLParser used specifically for game of life simulations
 * It has methods to retrieve attributes specific to game of life simulations (eg. probCellAlive)
 * It assumes that the Game of Life xml files have a tag called "probCellAlive"
 * @author Aaron Chang
 *
 */
public class GameOfLifeXMLParser extends XMLParser{
	private static final String PROB_CELL_ALIVE_TAG = "probCellAlive";
	
	public GameOfLifeXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * returns probability of each cell being alive at start of simulation
	 * @return int - percentage probability (out of 100) of cell being alive 
	 */
	public int getProbOfCellAlive() {
		return getIntValueByTagName(PROB_CELL_ALIVE_TAG);
	}
	
}
