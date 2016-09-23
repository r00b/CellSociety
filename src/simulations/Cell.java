package simulations;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Cell {
	private final ArrayList<Cell> neighbors;
	private final Tuple myPosition;
	private String currentState;
	private String nextState;
	private Color stateColor;
	
	public Cell(int i, int j){
		myPosition = new Tuple(i,j);
		neighbors = new ArrayList<Cell>();
	}
	
	public void setNextState(String next){
		nextState = next;
	}
	 
	public void commitState(Color color){
		currentState = nextState;
		stateColor = color;
	}
	
	public String getCurrState(){
		return currentState;
	}
	
	public Color getStateColor(){
		return stateColor;
	}
	
	
	public void addNeighbor(Cell neighbor){
		neighbors.add(neighbor);
	}
	public void setCurrState(String state, Color color){
		currentState = state;
		stateColor = color;
	}
	
	public String getNextState(){
		return nextState;
	}
	
	public Tuple getPosition(){
		return myPosition;
	}
	
	public ArrayList<Cell> getNeighbors(){
		return neighbors;
	}
	
	
	
}
