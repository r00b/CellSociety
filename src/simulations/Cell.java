package simulations;

import java.util.ArrayList;

public class Cell {
	private final ArrayList<Cell> neighbors;
	private final Tuple myPosition;
	private String currentState;
	
	public Cell(int i, int j){
		myPosition = new Tuple(i,j);
		neighbors = new ArrayList<Cell>();
	}
	
	
	
	
}
