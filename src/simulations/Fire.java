package simulations;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;
import xml.FireXMLParser;
import xml.GameOfLifeXMLParser;


public class Fire extends Simulation {
	private final FireXMLParser myParser;
	private Grid myGrid;
	private final int probCatchFire;
	private final int burnTime;
	private final HashMap<Integer, Color> stateToColorMap;
	private final int EMPTY;
	private final int BURNING;
	private final int TREE;


	public Fire(){
		EMPTY = 0;
		BURNING = 1;
		TREE = 2;
		myParser = new FireXMLParser("data/Fire.xml");
		probCatchFire = myParser.getProbCatchFire();
		burnTime = myParser.getBurnDownTime();
		stateToColorMap = new HashMap<>();
		myGrid = new Grid(myParser.getGridWidth(),myParser.getGridHeight());
		setPossibleStates();
		mapStatesToColors();
		setInitialGridState();
	}


	private void mapStatesToColors() {
		stateToColorMap.put(0, Color.GRAY);
		stateToColorMap.put(1, Color.RED);
		stateToColorMap.put(1, Color.GREEN);
	}

	/**
	 * For each cell in the grid, a random initial state is set, and its neighbors are calculated.
	 */
	protected void setInitialGridState(){
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j < myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				if(isEdgeCell(currCell)){
					currCell.setCurrState(EMPTY, stateToColorMap.get(EMPTY));
				}
				else if(currCell.getPosition().getIPos() == myGrid.getHeight()/2 && currCell.getPosition().getJPos() == myGrid.getWidth()/2 ){
					currCell.setCurrState(BURNING, stateToColorMap.get(BURNING));
				}
				else{
					currCell.setCurrState(TREE, stateToColorMap.get(TREE));
				}
				addNeighbors(currCell);
			}
		}
	}


	private boolean isEdgeCell(Cell currCell) {
		int i = currCell.getPosition().getIPos();
		int j = currCell.getPosition().getJPos();
		if(i == 0 || j == 0 || i == myGrid.getHeight() || j == myGrid.getWidth()){
			return true;
		}
		return false;
	}
	
	protected void addNeighbors(Cell currCell){
		if(isEdgeCell(currCell)){
			return;
		}
		else{
			int i = currCell.getPosition().getIPos();
			int j = currCell.getPosition().getJPos();
			currCell.addNeighbor(myGrid.getCell(i-1, j));
			currCell.addNeighbor(myGrid.getCell(i+1, j));
			currCell.addNeighbor(myGrid.getCell(i, j-1));
			currCell.addNeighbor(myGrid.getCell(i, j+1));
		}
	}


}
