package simulations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.scene.paint.Color;
import xml.SegregationXMLParser;

public class Segregation extends Simulation{
	private static final int EMPTY = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	
	private Grid myGrid;
	private SegregationXMLParser myParser;
	private HashMap<Integer, Color> stateToColorMap;
	private ArrayList<Cell> myVacantList; //updated in real time
	private int satisfactionThreshold;
	private int percentAgentOne;
	private int percentAgentTwo;
	private int percentEmpty;
	
	public Segregation() {
		myParser = new SegregationXMLParser("data/Segregation.xml");
		satisfactionThreshold = myParser.getSatisfactionThreshold();
		percentAgentOne = myParser.getPercentOfAgentOne();
		percentAgentTwo = myParser.getPercentOfAgentTwo();
		percentEmpty = myParser.getPercentEmpty();
		myGrid = new Grid(myParser.getGridWidth(), myParser.getGridHeight());
		stateToColorMap = new HashMap<Integer, Color>();
		mapStatesToColors();
		setInitialGridState();
	}
	

	protected void mapStatesToColors() {
		stateToColorMap.put(EMPTY, Color.WHITE);
		stateToColorMap.put(ONE, Color.BLUE);
		stateToColorMap.put(TWO, Color.RED);
	}

	@Override
	protected void setInitialGridState() {
		for (int row = 0; row < myGrid.getHeight(); row++) {
			for (int col = 0; col < myGrid.getWidth(); col++) {
				Cell currCell = myGrid.getCell(row, col);
				setRandomInitialState(currCell);
				addNeighbors(currCell);
			}
		}
	}
	
	private void setRandomInitialState(Cell currCell) {
		Random randomNumGenerator = new Random();
		int randNum = randomNumGenerator.nextInt(101);
		if (randNum < percentAgentOne) {
			currCell.setCurrState(ONE, stateToColorMap.get(ONE));
		}
		else if (randNum >= percentAgentOne && randNum < percentAgentOne + percentAgentTwo) {
			currCell.setCurrState(TWO, stateToColorMap.get(TWO));
		}
		else {
			currCell.setCurrState(EMPTY, stateToColorMap.get(EMPTY));
			myVacantList.add(currCell);
		}
	}

	@Override
	protected void updateNextStates() {
		for (int row = 0; row < myGrid.getHeight(); row++) {
			for (int col = 0; col < myGrid.getWidth(); col++) {
				Cell currCell = myGrid.getCell(row, col);
				if (currCell.getCurrState() == EMPTY) {
					continue;
				}
				//these lines only execute if cell is not empty
				boolean isSatisfied = calculateIfSatisfied(currCell);
				if (isSatisfied == false) {
					//moving current cell to first spot in the vacant list (make random?)
					myVacantList.get(0).setNextState(currCell.getCurrState());
					currCell.setNextState(EMPTY);
					myVacantList.remove(0);
					myVacantList.add(currCell);
				}
			}
		}
	}
	
	private boolean calculateIfSatisfied(Cell curCell) {
		int numAlike = 0;
		int numEmpty = 0;
		for (Cell neighbor : curCell.getNeighbors()) {
			if (neighbor.getCurrState() == curCell.getCurrState()) {
				numAlike += 1;
			}
			else if (neighbor.getCurrState() == EMPTY){
				numEmpty += 1;
			}
			else {
			}
		}
		double percentAlike = (double) (numAlike/(curCell.getNeighbors().size() - numEmpty));
		if (percentAlike*100 < satisfactionThreshold) {
			return false;
		}
		else {
			return true;
		}
	}
}
