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
	 * and the number of generations a tree burns for. Calls methods to map states to colors and initialize 
	 * the grid properly.
	 */
	public Fire(){
		EMPTY = 0;
		BURNING = 1;
		TREE = 2;
		myParser = new FireXMLParser("data/Fire.xml");
		probCatchFire = myParser.getProbCatchFire();
		burnTime = myParser.getBurnDownTime();
		stateToColorMap = new HashMap<>();
		mapStatesToColors();
		myGrid = new Grid(myParser.getGridWidth(),myParser.getGridHeight());
		setInitialGridState();
		
	}


	/**
	 * Maps each possible state of a cell to an appropriate color.
	 */
	protected void mapStatesToColors() {
		stateToColorMap.put(EMPTY, Color.GRAY);
		stateToColorMap.put(BURNING, Color.RED);
		stateToColorMap.put(TREE, Color.GREEN);
	}

	/**
	 * All cells on the edge of the grid are initialized to be empty. 
	 * Cell in the very middle of the grid is the only one burning at first.
	 * All other cells set to TREE.
	 */
	@Override
	protected void setInitialGridState(){
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j < myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				if(isEdgeCell(currCell)){
					currCell.setCurrState(EMPTY, stateToColorMap.get(EMPTY));
				}
				else if(isCenterCell(currCell)){
					currCell.setCurrState(BURNING, stateToColorMap.get(BURNING));
				}
				else{
					currCell.setCurrState(TREE, stateToColorMap.get(TREE));
				}
				addNeighbors(currCell);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see simulations.Simulation#addNeighbors(simulations.Cell)
	 *
	 *Edge cells can never catch fire, so we do not care about their neighbors. 
	 *For other cells, their neighbors are cells that are above, below, left, and right of the current cell.
	 *No need to worry about bounds checking because every cell that is not an edge cell is guaranteed to 
	 *have all four of these neighbors be valid positions. 
	 */
	@Override
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


	/* (non-Javadoc)
	 * @see simulations.Simulation#updateNextStates()
	 * 
	 * Cell that is empty always stays empty.
	 * Cell that is burning burns down to be empty.
	 * Cell that is a tree either remains a tree or catches on fire.
	 */
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

	
	/**
	 * @return randomly returns true or false based on value of probCatchFire
	 * Used to see if a cell that has a tree in it catches fire if one if its neighboring cells is on fire
	 */
	private boolean doesTreeCatchFire() {
		Random random = new Random();
		int randNum = random.nextInt(101);
		if(randNum < probCatchFire){
			return true;
		}
		return false;
	}


	/**
	 * @param currCell - the cell for which we want to see if any of its neighbors are burning
	 * @return boolean -> true if any of currCells neighbor are in the state BURNING, false otherwise 
	 */
	private boolean isNeighborBurning(Cell currCell) {
		for(Cell neighbor : currCell.getNeighbors()){
			if(neighbor.getCurrState() == BURNING){
				return true;
			}
		}
		return false;
	}

}
