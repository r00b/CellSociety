package simulations.Segregation;

import java.util.Iterator;
import java.util.Random;

import simulations.Cell;
import simulations.Grid;
import xml.SegregationXMLParser;

public class SegregationCell extends Cell {
	private final int EMPTY = 0;
	private final int ONE = 1;
	private final int TWO = 2;
	private final SegregationXMLParser myParser;
	private int satisfactionThreshold;
	private int percentAgentOne;
	private int percentAgentTwo;
	
	
	public SegregationCell(int i, int j) {
		super(i, j);
		myParser = new SegregationXMLParser(myResources.getString("DefaultSegregationFile"));
		satisfactionThreshold = myParser.getSatisfactionThreshold();
		percentAgentOne = myParser.getPercentOfAgentOne();
		percentAgentTwo = myParser.getPercentOfAgentTwo();
	}

	@Override
	public void mapStatesToColors() {
		updateColorMap(EMPTY, myParser.getEmptyColor());
		updateColorMap(ONE, myParser.getAgentOneColor());
		updateColorMap(TWO, myParser.getAgentTwoColor());

	}

	@Override
	public void setRandomInitialState() {
		Random randomNumGenerator = new Random();
		int randNum = randomNumGenerator.nextInt(101);
		if (randNum < percentAgentOne) {
			setCurrState(ONE, findStateColor(ONE));
		}
		else if (randNum >= percentAgentOne && randNum < percentAgentOne + percentAgentTwo) {
			setCurrState(TWO, findStateColor(TWO));
		}
		else {
			setCurrState(EMPTY, findStateColor(EMPTY));
		}
	}

	@Override
	public void setNeighborhood(Grid grid) {
		getMyNeighborhood().set_EightNeighbor_Wraparound_Neighborhood(this,grid);
	}
	
	
	public boolean isEmpty() {
		return getCurrState() == EMPTY;
	}

	public void setNextStateEmpty() {
		setNextState(EMPTY);
	}
	
	public void setNextStateToOtherCellState(SegregationCell otherCell){
		setNextState(otherCell.getCurrState());
	}
	
	public boolean isSatisfied(Cell newSpot) {
		int numAlike = 0;
		int numEmpty = 0;
		double percentAlike;
		Cell spotToCheck = newSpot;
		for (Iterator i = spotToCheck.getNeighbors(); i.hasNext();) {
			SegregationCell neighbor = (SegregationCell) i.next();
			if (neighbor.getCurrState() == getCurrState()) {
				numAlike += 1;
			}
			else if (neighbor.isEmpty()){
				numEmpty += 1;
			}
		}
		if(spotToCheck.getNeighborhoodSize() - numEmpty == 0){
			return false;
		}
		else{
			percentAlike = (numAlike/ (double) (spotToCheck.getNeighborhoodSize() - numEmpty));
		}
		if (percentAlike*100 < satisfactionThreshold) {
			return false;
		}
		else {
			return true;
		}
	}

	public void stateDoesntChange() {
		setNextState(getCurrState());
		
	}
}
