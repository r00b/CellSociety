package simulations;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.corba.se.impl.naming.namingutil.IIOPEndpointInfo;
import com.sun.javafx.scene.paint.GradientUtils.Parser;

import javafx.scene.paint.Color;

/**
 * @author samuelcurtis
 *
 *Cells all have a position, currentState, nextState, neighbors, and stateColor. Cells used to populate grids.
 */
public abstract class Cell {
	private final Neighborhood myNeighbors;
	private final Tuple myPosition;
	private int currentState;
	private int nextState;
	private Color stateColor;
	private HashMap<Integer, Color> stateToColorMap;
	protected ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String LANGUAGE = "English";
	
	/**
	 * @param i the rom number of the row this cell is in 
	 * @param j the colum number of the column this cell is in
	 */
	public Cell(int i, int j){
		myPosition = new Tuple(i,j);
		myNeighbors = new Neighborhood();
		stateToColorMap = new HashMap<>();
	}
	
	protected Color findStateColor(int state) {
		return stateToColorMap.get(state);
	}
	
	public abstract void mapStatesToColors();
	
	public abstract void setRandomInitialState();
	
	/**
	 * @param next - the State that this cell will be in in the next generation
	 */
	protected void setNextState(int next){
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
		myNeighbors.addNeighbor(neighbor);
	}
	/**
	 * @param state is the state the cell will be set to next 
	 * @param color is the color associated with that state 
	 */
	public void setCurrState(int state, Color color){
		currentState = state;
		stateColor = color;
	}
	
	protected void updateColorMap(int state, Color color){
		stateToColorMap.put(state,color);
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
	
	public abstract void setNeighborhood(Grid grid);
	
	protected Neighborhood getMyNeighborhood(){
		return myNeighbors;
	}
	/**
	 * @return an arraylist of the cells neighboring cells 
	 */
	public Iterator<Cell> getNeighbors(){
		 return myNeighbors.iterator();
	}
}
