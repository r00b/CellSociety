package simulations.Fire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.paint.Color;
import simulations.Simulation;
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
	private final FireGrid myGrid;


	/**
	 * Sets each possible state to a different int, so that the rest of the program doesn't need to worry 
	 * about what the values are and can just reference the state variables. Uses a parser object to obtain
	 * the sizes for the grid, the probability of a tree catching fire if one of its neighbors is on fire,
	 * and the number of generations a tree burns for. Calls methods to map states to colors and initialize 
	 * the grid properly.
	 */
	public Fire(){
		myParser = new FireXMLParser(myResources.getString("DefaultFireFile"));
		myGrid = new FireGrid(myParser.getGridWidth(),myParser.getGridHeight());
		setGrid(myGrid);
		setInitialGridState();
		
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
				FireCell currCell = (FireCell) myGrid.getCell(i, j);
				if(isEdgeCell(currCell)){
					currCell.setEmpty();
				}
				else if(isCenterCell(currCell)){
					currCell.setBurning();
				}
				else{
					currCell.setTree();;
				}
				currCell.setNeighborhood(myGrid);;
			}
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
				FireCell currCell = (FireCell) myGrid.getCell(i, j);
				if(currCell.isEmpty()){
					currCell.setNextStateEmpty();
				}
				else if(currCell.isBurning()){
					currCell.updateBurnTime();
					if(currCell.isDoneBurning()){
						currCell.setNextStateEmpty();
					}
					else{
						currCell.setNextStateBurning();
					}
				}
				else{
					currCell.setNextStateTree();
					if(currCell.isNeighborBurning()){
						if(currCell.doesCatchFire()){
							currCell.setNextStateBurning();
						}
					}
				}
			}
			
		}
	}
}
