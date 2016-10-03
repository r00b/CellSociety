package simulations.Segregation;

import java.util.Iterator;
import java.util.Random;

import simulations.Cell;
import simulations.Grid;
import xml.SegregationXMLParser;

/**
 * @author samuelcurtis
 *The SegregationCell class is a subcass of the Cell class. It is used to represent 
 *cells in the Segregation simulation type. These cells can be either empty, in state
 *ONE, or in state TWO. What these states represent is irrelevant, the cells only 
 *care about finding cells that are the same state as they are. The initial state
 *of a cell is determined randomly based on the percentages given in an XML file.
 */
public class SegregationCell extends Cell {
	public static final int EMPTY = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	private final SegregationXMLParser myParser;
	private int satisfactionThreshold;
	private int percentAgentOne;
	private int percentAgentTwo;
	
	
	public SegregationCell(int i, int j,String XMLFileName) {
		super(i, j);
		myParser = new SegregationXMLParser(XMLFileName);
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
		getMyNeighborhood().set_EightNeighbor_Wraparound(this,grid);
	}
	
	
	/**
	 * @return true if the current state of the cell is EMPTY, false otherwise
	 */
	public boolean isEmpty() {
		return getCurrState() == EMPTY;
	}

	/**
	 * Set the next state of the cell to EMPTY
	 */
	public void setNextStateEmpty() {
		setNextState(EMPTY);
	}
	
	/**
	 * The cells next state is the same as its current state. 
	 */
	public void stateDoesntChange() {
		setNextState(getCurrState());
	}
	
	/**
	 * @param otherCell is the cell whose state we want this cell's state to match
	 * Set the next state of this cell to whatever the state of otherCell is
	 */
	public void setNextStateToOtherCellState(SegregationCell otherCell){
		setNextState(otherCell.getCurrState());
	}
	
	/**
	 * @param newSpot represents the spot for which we want to determine if a 
	 * cell is satisfied in. If the newSpot cell is equivalent to the cell 
	 * calling the method, we are determining whether a cell is satisfied where it is.
	 * If newSpot is a different cell, then this method determines if a given cell 
	 * would be satisfied in a different location. 
	 * @return true if the cell is satisfied where it is or would be satisfied 
	 * in a given location. false otherwise
	 * a cell is satisfied if at least a certain percentage of its neighbors
	 * are in the same state as it is.
	 */
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
}
