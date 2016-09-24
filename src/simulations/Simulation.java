package simulations;
import java.util.ArrayList;

import xml.XMLParser;

public abstract class Simulation {
	
	
	 
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
	  * adds possible states to the instance variable possibleStates
	  * each state represents a state that each cell could possibly have
	  */
	 protected abstract void setPossibleStates();
	
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
	  * This method should be called after updateNextStates()
	  * It updates the current state of each cell in the Grid to be the next state (which was previously calculated)
	  */
	 protected abstract void commitStates();
	 
	 /**
	  * populates the neighbors attribute of each cell in the grid
	  * @param - Cell
	  */
	 protected abstract void addNeighbors(Cell cell);
	 
	 /**
	  * It is used by Animation.java so the Grid can be displayed
	  * @return the Grid of the Simulation
	  */
	 public abstract Grid getGrid();
	 
	 
}
