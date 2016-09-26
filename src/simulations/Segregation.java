package simulations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.paint.Color;
import xml.SegregationXMLParser;

/**
 * Segregation class is used to handle the logic of simulating CA model of segregation.
 * This class's logic depends on the rules of the simulation as described by Schelling's model of segregation.
 * These rules describe 3 possible cell states (empty, agentOne, or agentTwo)
 * This class also depends on Grid and Cell to help model the simulation.
 * It assumes that the SegregationXMLParser has specific methods to return
 * @author Aaron Chang
 *
 */
public class Segregation extends Simulation{
	private static final int EMPTY = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;

	private SegregationXMLParser myParser;
	private ArrayList<Cell> myVacantList; //updated in real time
	private int satisfactionThreshold;
	private int percentAgentOne;
	private int percentAgentTwo;

	public Segregation() {
		myParser = new SegregationXMLParser(myResources.getString("DefaultSegregationFile"));
		satisfactionThreshold = myParser.getSatisfactionThreshold();
		percentAgentOne = myParser.getPercentOfAgentOne();
		percentAgentTwo = myParser.getPercentOfAgentTwo();
		myGrid = new Grid(myParser.getGridWidth(), myParser.getGridHeight());
		stateToColorMap = new HashMap<Integer, Color>();
		myVacantList = new ArrayList<>();
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
		ArrayList<Cell> alreadyTouched = new ArrayList<>();
		for (int row = 0; row < myGrid.getHeight(); row++) {
			for (int col = 0; col < myGrid.getWidth(); col++) {
				Cell currCell = myGrid.getCell(row, col);
				if (currCell.getCurrState() == EMPTY) {
					if(!myVacantList.contains(currCell) && !alreadyTouched.contains(currCell)){
						myVacantList.add(currCell);
					}
					continue;
				}
				//these lines only execute if cell is not empty
				boolean isSatisfied = calculateIfSatisfied(currCell, currCell, false);
				if (isSatisfied) {
					currCell.setNextState(currCell.getCurrState());
				}
				else {
					boolean foundSpot = false;
					if(myVacantList.size() > 0){
						for(Cell possibleSpot : myVacantList){
							if(calculateIfSatisfied(currCell, possibleSpot, true)){
								possibleSpot.setNextState(currCell.getCurrState());
								alreadyTouched.add(possibleSpot);
								myVacantList.remove(possibleSpot);
								currCell.setNextState(EMPTY);
								foundSpot = true;
								break;
							}
						}
						if(foundSpot == false){
							int randSpot = getRandomVacantSpot();
							myVacantList.get(randSpot).setNextState(currCell.getCurrState());
							alreadyTouched.add(myVacantList.get(randSpot));
							currCell.setNextState(EMPTY);
							myVacantList.remove(randSpot);
						}
					}
					else{
						currCell.setNextState(currCell.getCurrState());
					}
				}
			}
		}
	}

	private int getRandomVacantSpot() {
		Random myRandom = new Random();
		return myRandom.nextInt(myVacantList.size());

	}


	private boolean calculateIfSatisfied(Cell curCell, Cell newSpot, boolean checkingNewSpot) {
		int numAlike = 0;
		int numEmpty = 0;
		double percentAlike;
		Cell spotToCheck;
		if(checkingNewSpot == true){
			spotToCheck = newSpot;
		}
		else{
			spotToCheck = curCell;
		}
		for (Cell neighbor : spotToCheck.getNeighbors()) {
			if (neighbor.getCurrState() == curCell.getCurrState()) {
				numAlike += 1;
			}
			else if (neighbor.getCurrState() == EMPTY){
				numEmpty += 1;
			}
		}
		if((spotToCheck.getNeighbors().size() - numEmpty) == 0){
			return false;
		}
		else{
			percentAlike = (numAlike/ (double) (spotToCheck.getNeighbors().size() - numEmpty));
		}
		if (percentAlike*100 < satisfactionThreshold) {
			return false;
		}
		else {
			return true;
		}
	}
}
