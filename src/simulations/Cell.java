package simulations;

import java.util.ArrayList;

public class Cell {
	private final ArrayList<Cell> neighbors;
	private final Tuple myPosition;
	private String currentState;
	private String nextState;
	
	public Cell(int i, int j){
		myPosition = new Tuple(i,j);
		neighbors = new ArrayList<Cell>();
	}
	
	public void setNextState(String next){
		nextState = next;
	}
	 
	public void commitState(){
		currentState = nextState;
	}
	
	public String getCurrState(){
		return currentState;
	}
	
	
	public void addNeighbor(Cell neighbor){
		neighbors.add(neighbor);
	}
	public void setCurrState(String state){
		currentState = state;
	}
	
	
	public Tuple getPosition(){
		return myPosition;
	}
	
	public ArrayList<Cell> getNeighbors(){
		return neighbors;
	}
	
	
	
}
