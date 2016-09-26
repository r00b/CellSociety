package simulations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import xml.GameOfLifeXMLParser;;


/**
 * @author samuelcurtis
 * 
 * GameOfLife class handles the logic of initializing a grid of cells and updating their states 
 * according to the Game of Life simulation rules. Offers two public methods, getGrid() and updateGrid() so
 * that an Animation object can get the grid and continually update it to allow for the visualization of the simulation.
 *
 */

public class GameOfLife extends Simulation{
	private final GameOfLifeXMLParser myParser;
	private final int probCellAlive;
	private final int DEAD = 0;
	private final int ALIVE = 1;
	
	/**
	 * Reads in the data for probCellAlive and the grid dimensions using the GameOfLifeXMLParser object. 
	 * Initializes the grid. 
	 */
	public GameOfLife() {
		myParser = new GameOfLifeXMLParser("data/GameOfLife.xml");
		probCellAlive = myParser.getProbOfCellAlive();
		stateToColorMap = new HashMap<>();
		myGrid = new Grid(myParser.getGridWidth(),myParser.getGridHeight());
		mapStatesToColors();
		setInitialGridState();
	}
	
	
	protected void mapStatesToColors() {
		stateToColorMap.put(DEAD,Color.RED);
		stateToColorMap.put(ALIVE, Color.BLUE);
	}
	
	/**
	 * For each cell in the grid, a random initial state is set, and its neighbors are calculated.
	 */
	protected void setInitialGridState(){
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j < myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				setRandomInitialState(probCellAlive, currCell);
				addNeighbors(currCell);
			}
		}

	}
	
	
	/**
	 * @param prob_Cell_Alive represents the probability that a cell is alive at the start of the simulation.
	 * @param currCell is the cell for which we want an initial state to be randomly chosen
	 */
	private void setRandomInitialState(int prob_Cell_Alive, Cell currCell) {
		Random random = new Random();
		int randNum = random.nextInt(101);
		if(randNum > prob_Cell_Alive){
			currCell.setCurrState(DEAD,stateToColorMap.get(DEAD));
		}
		else{
			currCell.setCurrState(ALIVE,stateToColorMap.get(ALIVE));
		}
		
	}
	
	
	
	
	/**
	 * Goes through each cell in the grid and calculates what its next state will be 
	 * based on the Game of Life rules.
	 */
	protected void updateNextStates(){
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j<myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				int numNeighborsAlive = calculateNumNeighborsAlive(currCell);
				if(currCell.getCurrState() == DEAD){
					if(numNeighborsAlive == 3){
						currCell.setNextState(ALIVE);
					}
					else{
						currCell.setNextState(DEAD);
					}
				}
				else{
					if(numNeighborsAlive == 2 || numNeighborsAlive == 3){
						currCell.setNextState(ALIVE);
						continue;
					}
					else{
						currCell.setNextState(DEAD);
					}
				}
				
			}
		}
	}
	
	
	/**
	 * @param currCell is the cell for which we are calculating neighbors for
	 * @return the number of neighbors of currCell that are currently alive.
	 */
	private int calculateNumNeighborsAlive(Cell currCell) {
		int numAlive = 0;
		for(Cell neighbor : currCell.getNeighbors()){
			if(neighbor.getCurrState() == ALIVE){
				numAlive += 1;
			}
		}
		return numAlive;
	}
}
