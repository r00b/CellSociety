package simulations;
import XML.GameOfLifeXMLParser;

import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import XML.GameOfLifeXMLParser;;

public class GameOfLife {
	private Grid myGrid;
	private final GameOfLifeXMLParser myParser;
	private final int probCellAlive;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String LANGUAGE = "English";
	private final ArrayList<String> possibleStates;
	
	
	public GameOfLife() {
		myParser = new GameOfLifeXMLParser("../data/GameOfLife.xml");
		probCellAlive = myParser.getProbOfCellAlive();
		possibleStates = new ArrayList<String>();
		myGrid = new Grid(myParser.getGridWidth(),myParser.getGridHeight());
		setInitialGridState();
	}
	
	
	private void setInitialGridState(){
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j < myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				setPossibleStates(currCell);
				setRandomInitialState(probCellAlive, currCell);
				addNeighbors(currCell);
			}
		}
	}
	
	private void addNeighbors(Cell currCell) {
		Tuple position = currCell.getPosition();
		for(int k = -1; k<2; k++){
			for(int g = -1; g<2; g++){
				int i = getNeighborIPosition(position, position.getIPos(), k);
				int j = getNeighborJPosition(position, position.getJPos(), g);
				currCell.addNeighbor(myGrid.getCell(i, j));
				
			}
		}
		
	}
	
	private int getNeighborJPosition(Tuple position, int j, int g) {
		int jPos = position.getJPos() + g;
		if(jPos < 0){
			jPos = myGrid.getWidth();
		}
		
		if(jPos > 0){
			jPos = 0;
		}
		return jPos;
	}


	private int getNeighborIPosition(Tuple position,int i, int k){
		int iPos = position.getIPos() + k;
		if(iPos < 0){
			iPos = myGrid.getHeight();
		}
		
		if(iPos > 0){
			iPos = 0;
		}
		return iPos;
	}


	private void setRandomInitialState(int prob_Cell_Alive, Cell currCell) {
		Random random = new Random();
		int randNum = random.nextInt(101);
		if(randNum < prob_Cell_Alive){
			currCell.setCurrState(possibleStates.get(0));
		}
		else{
			currCell.setCurrState(possibleStates.get(1));
		}
		
	}
	//might not need this
	private void setPossibleStates(Cell currCell) {
		ResourceBundle resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
		possibleStates.add(resources.getString("Dead"));
		possibleStates.add(resources.getString("Alive"));
	}
	
	private void updateNextStates(){
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j<myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				int numNeighborsAlive = calculateNumNeighborsAlive(currCell);
				if(currCell.getCurrState() == "Dead"){
					if(numNeighborsAlive == 3){
						currCell.setNextState("Alive");
						continue;
					}
					else{
						currCell.setNextState("Dead");
						continue;
					}
				}
				else{
					if(numNeighborsAlive == 2 || numNeighborsAlive == 3){
						currCell.setNextState("Alive");
						continue;
					}
					else{
						currCell.setNextState("Dead");
					}
				}
				
			}
		}
	}
	
	private void commitStates(){
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j<myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				currCell.commitState();
			}
		}
	}
	
	public void updateGrid(){
		updateNextStates();
		commitStates();
	}

	private int calculateNumNeighborsAlive(Cell currCell) {
		int numAlive = 0;
		for(Cell neighbor : currCell.getNeighbors()){
			if(neighbor.getCurrState() == "Alive"){
				numAlive += 1;
			}
		}
		return numAlive;
	}
	
	
}
