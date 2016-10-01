package simulations;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;

public abstract class Simulation {
	
	
	protected ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
	protected static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	protected static final String LANGUAGE = "English";
	protected Grid myGrid;
	public String title;
	/**
	 * calculates the next state for each cell and updates the current state
	 * this method is like a step method
	 * It is constantly being called by Animation.java to update the view of the simulation
	 * It depends on the updateNextStates() method and commitStates() method
	 */
	
	public void setGrid(Grid grid){
		myGrid = grid;
	}
	
	
	public void updateGrid() {
		updateNextStates();
		commitStates();
	}


	/**
	 * Initializes the state of cells in the Grid
	 * The state of the cells is determined by Simulation parameters
	 */
	protected abstract void setInitialGridState();

	/**
	 * calculates the next state of each cell in the Grid, but does not update the current state
	 * this method simply calculates the next state of the cell so it can be updated later
	 */
	protected abstract void updateNextStates();
	


	/**
	 * It is used by Animation.java so the Grid can be displayed
	 * @return the Grid of the Simulation
	 */
	public Grid getGrid(){
		return myGrid;
	}

	/**
	 * @param currCell the cell for which we would like to determine if it is an edge cell or not 
	 * @return boolean -> true if cell is on the edge of the grid, false if it is not 
	 */
	protected boolean isEdgeCell(Cell currCell) {
		int i = currCell.getPosition().getIPos();
		int j = currCell.getPosition().getJPos();
		if(i == 0 || j == 0 || i == (getGridHeight() - 1) || j == (getGridWidth() - 1)){
			return true;
		}
		return false;
	}

	/**
	 * Goes through every cell in the grid and finalizes their updated state after the correct
	 * next state has been calculated for all cells. Also updates the color of the cell to match 
	 * what its new current state will be.
	 */
	protected void commitStates(){
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j < myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				currCell.commitState(currCell.findStateColor(currCell.getNextState()));
			}
		}
	}

	public int getGridWidth(){
		return myGrid.getWidth();
	}

	protected boolean isCenterCell(Cell currCell){
		return currCell.getPosition().getIPos() == myGrid.getHeight()/2 && 
				currCell.getPosition().getJPos() == myGrid.getWidth()/2;
	}

	public int getGridHeight(){
		return myGrid.getHeight();
	}
}
