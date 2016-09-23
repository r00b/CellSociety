package simulations;


import java.util.Random;
import java.util.ResourceBundle;

import xml.GameOfLifeXMLParser;;

public class GameOfLife {
	private Grid myGrid;
	private final GameOfLifeXMLParser myParser;
	private final int probCellAlive;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String LANGUAGE = "English";
	
	
	public GameOfLife() {
		myParser = new GameOfLifeXMLParser("../data/GameOfLife.xml");
		probCellAlive = myParser.getProbOfCellAlive();
		myGrid = new Grid(getGridWidth(),getGridHeight());
	}
	
	
	private int getGridWidth() {
		Integer.parseInt(myParser.getTextValueByTagName("gridWidth"));
		return 0;
	}


	private int getGridHeight(){
		return Integer.parseInt(myParser.getTextValueByTagName("gridHeight"));
	}
	
	private void setInitialGridState(){
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j < myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				setPossibleStates(currCell);
				setRandomInitialState(probCellAlive, currCell);
			}
		}
	}
	
	private void setRandomInitialState(int prob_Cell_Alive, Cell currCell) {
		Random random = new Random();
		int randNum = random.nextInt(101);
		if(randNum < 50){
			currCell.setCurrState(currCell.getPossibleStates().get(0));
		}
		else{
			currCell.setCurrState(currCell.getPossibleStates().get(1));
		}
		
	}

	private void setPossibleStates(Cell currCell) {
		ResourceBundle resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
		currCell.addState(resources.getString("Dead"));
		currCell.addState(resources.getString("Alive"));
		
	}
	
	
	
	
	
}
