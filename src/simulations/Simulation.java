package simulations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;
import xml.XMLParser;
import gui.Animation;

public abstract class Simulation {
	
	
	protected ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
	protected static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	protected static final String LANGUAGE = "English";
	protected Grid myGrid;
	protected HashMap<Integer, Color> stateToColorMap;
	/**
	 * calculates the next state for each cell and updates the current state
	 * this method is like a step method
	 * It is constantly being called by Animation.java to update the view of the simulation
	 * It depends on the updateNextStates() method and commitStates() method
	 */
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
	
	
	
	protected abstract void mapStatesToColors();


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
				currCell.commitState(stateToColorMap.get(currCell.getNextState()));
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
	
	/**
	 * @param currCell is the cell for which we want to calculate its neighbors and store
	 * them. 
	 * 
	 * This method calls two helper methods, getNeighborIPosition and getNeighborJposition,
	 * which assist in the logic of calculating the neighbors of edge cells. 
	 */
	protected void addNeighbors(Cell currCell) {
		Tuple position = currCell.getPosition();
		for(int k = -1; k<2; k++){
			for(int g = -1; g<2; g++){
				int i = getNeighborIPosition(position.getIPos(), k);
				int j = getNeighborJPosition(position.getJPos(), g);
				if (!(i == position.getIPos() && j == position.getJPos())) {
					currCell.addNeighbor(myGrid.getCell(i, j));
				}
			}
		}
	}
	
	/**
	 *
	 * @param j - the j poisition of the current cell for which we are calculating the j position for a given neighbor
	 * @param g - an int between -1 and 1 that represents a relative J position to the current cell of a given neighbor
	 * @return the position of the current neighbor, with edge cell case accounted for
	 */
	protected int getNeighborJPosition(int j, int g) {
		int jPos = j + g;
		if(jPos < 0){
			jPos = myGrid.getWidth()-1;
		}
		
		if(jPos > myGrid.getWidth() -1){
			jPos = 0;
		}
		return jPos;
	}

	/**
	 *
	 * @param i - the I poisition of the current cell for which we are calculating the I position for a given neighbor
	 * @param k - an int between -1 and 1 that represents a relative I position to the current cell of a given neighbor
	 * @return the position of the current neighbor, with edge cell case accounted for
	 */
	protected int getNeighborIPosition(int i, int k){
		int iPos = i + k;
		if(iPos < 0){
			iPos = myGrid.getHeight()-1;
		}
		
		if(iPos > myGrid.getHeight()-1){
			iPos = 0;
		}
		return iPos;
	}


}
