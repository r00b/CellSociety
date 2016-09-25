package simulations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.scene.paint.Color;
import xml.FireXMLParser;
import xml.GameOfLifeXMLParser;



/**
 * @author samuelcurtis
 *
 *Fire class used to handle the logic of simulating a CA simulation with the Spread of Fire parameters.
 *Needs a grid to store all of the cells, has three possible states for cells, EMPTY, BURNING, or TREE. Trees have
 *the potential to catch fire if one of their neighbors is on fire -- how often this happens determined by 
 *probCatchFire. Trees that are burnt down become empty sites. Neighbors are defined as cells to the north, east, south, 
 *and west of a given cell. (i.e. cells diagonally connected to a cell are not neighbors, the rest are).
 *
 */
public class Fire extends Simulation {
	private final FireXMLParser myParser;
	private Grid myGrid;
	private final int probCatchFire;
	private final int burnTime;
	private final HashMap<Integer, Color> stateToColorMap;
	private final int EMPTY;
	private final int BURNING;
	private final int TREE;


	/**
	 * Sets each possible state to a different int, so that the rest of the program doesn't need to worry 
	 * about what the values are and can just reference the state variables. Uses a parser object to obtain
	 * the sizes for the grid, the probability of a tree catching fire if one of its neighbors is on fire,
	 * and the number of generations a tree burns for. 
	 */
	public Fire(){
		EMPTY = 0;
		BURNING = 1;
		TREE = 2;
		myParser = new FireXMLParser("data/Fire.xml");
		probCatchFire = myParser.getProbCatchFire();
		burnTime = myParser.getBurnDownTime();
		stateToColorMap = new HashMap<>();
		myGrid = new Grid(myParser.getGridWidth(),myParser.getGridHeight());
		mapStatesToColors();
		setInitialGridState();
	}


	private void mapStatesToColors() {
		stateToColorMap.put(EMPTY, Color.GRAY);
		stateToColorMap.put(BURNING, Color.RED);
		stateToColorMap.put(TREE, Color.GREEN);
	}

	/**
	 * For each cell in the grid, a random initial state is set, and its neighbors are calculated.
	 */
	protected void setInitialGridState(){
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j < myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				if(isEdgeCell(currCell)){
					currCell.setCurrState(EMPTY, stateToColorMap.get(EMPTY));
				}
				else if(currCell.getPosition().getIPos() == myGrid.getHeight()/2 && currCell.getPosition().getJPos() == myGrid.getWidth()/2 ){
					currCell.setCurrState(BURNING, stateToColorMap.get(BURNING));
				}
				else{
					currCell.setCurrState(TREE, stateToColorMap.get(TREE));
				}
				addNeighbors(currCell);
			}
		}
	}
	
	


	private boolean isEdgeCell(Cell currCell) {
		int i = currCell.getPosition().getIPos();
		int j = currCell.getPosition().getJPos();
		if(i == 0 || j == 0 || i == getGridHeight() || j == getGridWidth()){
			return true;
		}
		return false;
	}
	
	protected void addNeighbors(Cell currCell){
		if(isEdgeCell(currCell)){
			return;
		}
		else{
			int i = currCell.getPosition().getIPos();
			int j = currCell.getPosition().getJPos();
			currCell.addNeighbor(myGrid.getCell(i-1, j));
			currCell.addNeighbor(myGrid.getCell(i+1, j));
			currCell.addNeighbor(myGrid.getCell(i, j-1));
			currCell.addNeighbor(myGrid.getCell(i, j+1));
		}
	}


	@Override
	protected void updateNextStates() {
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j<myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				if(currCell.getCurrState() == EMPTY){
					currCell.setNextState(EMPTY);
				}
				//TODO adjust this for burning time 
				else if(currCell.getCurrState() == BURNING){
					currCell.setNextState(EMPTY);
				}
				else{
					if(isNeighborBurning(currCell)){
						if(doesTreeCatchFire()){
							currCell.setNextState(BURNING);
						}
						else{
							currCell.setNextState(TREE);
						}
					}
				}
			}
		}
		
		
	}

	
	
	
	
	private boolean doesTreeCatchFire() {
		Random random = new Random();
		int randNum = random.nextInt(101);
		if(randNum < probCatchFire){
			return true;
		}
		return false;
	}


	private boolean isNeighborBurning(Cell currCell) {
		for(Cell neighbor : currCell.getNeighbors()){
			if(neighbor.getCurrState() == BURNING){
				return true;
			}
		}
		return false;
	}


	/**
	 * Goes through every cell in the grid and finalizes their updated state after the correct
	 * next state has been calculated for all cells. 
	 */
	@Override
	protected void commitStates() {
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j<myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				currCell.commitState(stateToColorMap.get(currCell.getNextState()));
				}
			}
	}
		


	@Override
	public Grid getGrid() {
		return myGrid;
	}


	@Override
	public int getGridWidth() {
		return myGrid.getWidth();
	}


	@Override
	public int getGridHeight() {
		return myGrid.getHeight();
	}


}
