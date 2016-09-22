package XML;

public class GameOfLifeXMLParser extends XMLParser{
	private static final String PROB_CELL_ALIVE_TAG = "probCellAlive";

	public GameOfLifeXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	public int getProbOfCellAlive() {
		return getIntValueByTagName(PROB_CELL_ALIVE_TAG);
	}
	
}
