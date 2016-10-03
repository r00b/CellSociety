package simulations.Segregation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;
import simulations.Cell;
import simulations.Simulation;
import simulations.GameOfLife.GameOfLifeCell;
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

	private SegregationXMLParser myParser;
	private List<SegregationCell> myVacantList; //updated in real time
	private SegregationGrid myGrid;

	public Segregation(String XMLFileName) {
		super(XMLFileName);
		myParser = new SegregationXMLParser(XMLFileName);
		myGrid = new SegregationGrid(myParser.getGridWidth(), myParser.getGridHeight(),XMLFileName);
		myVacantList = new ArrayList<>();
		setGrid(myGrid);
		setInitialGridState();
	}


	@Override
	protected void setInitialGridState() {
		for (int row = 0; row < myGrid.getHeight(); row++) {
			for (int col = 0; col < myGrid.getWidth(); col++) {
				SegregationCell currCell = (SegregationCell) myGrid.getCell(row, col);
				currCell.setRandomInitialState();
				if(currCell.isEmpty()){
					myVacantList.add(currCell);
				}
				currCell.setNeighborhood(myGrid);
			}
		}
	}


	@Override
	protected void updateNextStates() {
		ArrayList<Cell> alreadyTouched = new ArrayList<>();
		for (int row = 0; row < myGrid.getHeight(); row++) {
			for (int col = 0; col < myGrid.getWidth(); col++) {
				SegregationCell currCell = (SegregationCell) myGrid.getCell(row, col);
				if (currCell.isEmpty()) {
					if(!myVacantList.contains(currCell) && !alreadyTouched.contains(currCell)){
						myVacantList.add(currCell);
					}
					continue;
				}
				//these lines only execute if cell is not empty
				boolean isSatisfied = currCell.isSatisfied(currCell);
				if (isSatisfied) {
					currCell.stateDoesntChange();
				}
				else {
					boolean foundSpot = false;
					if(myVacantList.size() > 0){
						for(SegregationCell possibleSpot : myVacantList){
							if(currCell.isSatisfied(possibleSpot)){
								possibleSpot.setNextStateToOtherCellState(currCell);
								alreadyTouched.add(possibleSpot);
								myVacantList.remove(possibleSpot);
								currCell.setNextStateEmpty();
								foundSpot = true;
								break;
							}
						}
						if(foundSpot == false){
							int randSpot = getRandomVacantSpot();
							SegregationCell  vacantCell = (SegregationCell) myVacantList.get(randSpot);
							vacantCell.setNextStateToOtherCellState(currCell);
							alreadyTouched.add(myVacantList.get(randSpot));
							currCell.setNextStateEmpty();
							myVacantList.remove(randSpot);
						}
					}
					else{
						currCell.stateDoesntChange();
					}
				}
			}
		}
	}

	
	private int getRandomVacantSpot() {
		Random myRandom = new Random();
		return myRandom.nextInt(myVacantList.size());

	}


	@Override
	protected void setStateMap() {
		myStateMap.put(SegregationCell.EMPTY, "Empty");
		myStateMap.put(SegregationCell.ONE, "Agent One");
		myStateMap.put(SegregationCell.TWO, "Agent Two");
		
	}
}
