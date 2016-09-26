package simulations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javafx.scene.paint.Color;

/**
 * @author samuelcurtis
 *
 *Cells all have a position, currentState, nextState, neighbors, and stateColor. Cells used to populate grids.
 */
public class Cell{
	private final ArrayList<Cell> neighbors;
	private final Tuple myPosition;
	private int currentState;
	private int nextState;
	private Color stateColor;
	
	/**
	 * @param i the rom number of the row this cell is in 
	 * @param j the colum number of the column this cell is in
	 */
	public Cell(int i, int j){
		myPosition = new Tuple(i,j);
		neighbors = new ArrayList<Cell>();
	}
	
	/**
	 * @param next - the State that this cell will be in in the next generation
	 */
	public void setNextState(int next){
		nextState = next;
	}
	 
	/**
	 * @param color is the color of the cells new state. This method updates the current state to the next date,
	 * should be called once all cells have their next generation state calculated 
	 */
	public void commitState(Color color){
		currentState = nextState;
		stateColor = color;
	}
	
	/**
	 * @return the current state of this cell
	 */
	public int getCurrState(){
		return currentState;
	}
	
	/**
	 * @return the color associated with the cells current state as defined by a simulation class
	 */
	public Color getStateColor(){
		return stateColor;
	}
	
	
	/**
	 * @param neighbor is a cell that neighbors this cell. this method adds that cell to the instance variable
	 * neighbors, which stores all of the cells neighboring cells
	 */
	public void addNeighbor(Cell neighbor){
		neighbors.add(neighbor);
	}
	/**
	 * @param state is the state the cell will be set to next 
	 * @param color is the color associated with that state 
	 */
	public void setCurrState(int state, Color color){
		currentState = state;
		stateColor = color;
	}
	
	/**
	 * @return the state the cell will be in next generation 
	 */
	public int getNextState(){
		return nextState;
	}
	
	/**
	 * @return the tuple representing the (i,j) position of the cell within a grid
	 */
	public Tuple getPosition(){
		return myPosition;
	}
	
	/**
	 * @return an arraylist of the cells neighboring cells 
	 */
	public ArrayList<Cell> getNeighbors(){
		return neighbors;
	}
}
