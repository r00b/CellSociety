package simulations;

import java.util.ArrayList;

public class Cell {
	private final ArrayList<Cell> neighbors;
	private final Tuple myPosition;
	private String currentState;
	private final ArrayList<String> possibleStates;
	
	public Cell(int i, int j){
		myPosition = new Tuple(i,j);
		neighbors = new ArrayList<Cell>();
		possibleStates = new ArrayList<String>();
	}
	
	public void addState(String state){
		possibleStates.add(state);
	}
	
	public void setCurrState(String state){
		currentState = state;
		
	}
	
	public ArrayList<String> getPossibleStates(){
		return possibleStates;
	}
	
	
	
}
