package simulations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.scene.paint.Color;
import xml.WatorXMLParser;

public class Wator extends Simulation{
	private static final int EMPTY = 0;
	private static final int SHARK = 1;
	private static final int FISH = 2;
	
	private WatorXMLParser myParser;
	private HashMap<Cell, Fish> myFish = new HashMap<Cell, Fish>();
	private HashMap<Cell, Shark> mySharks = new HashMap<Cell, Shark>();
	private HashMap<Cell, Fish> tempFishMap = new HashMap<Cell, Fish>();
	private HashMap<Cell, Shark> tempSharkMap = new HashMap<Cell, Shark>();
	private final int myFishBreedTime;
	private final int mySharkBreedTime;
	private final int mySharkStarveTime;
	private final int myPercentShark;
	private final int myPercentFish;
	
	public Wator() {
		myParser = new WatorXMLParser("data/Wator.xml");
		myFishBreedTime = myParser.getFishBreedTime();
		mySharkBreedTime = myParser.getSharkBreedTime();
		mySharkStarveTime = myParser.getSharkStarveTime();
		myPercentShark = myParser.getPercentShark();
		myPercentFish = myParser.getPercentFish();
		myGrid = new Grid(myParser.getGridWidth(), myParser.getGridHeight());
		stateToColorMap = new HashMap<Integer, Color>();
		mapStatesToColors();
		setInitialGridState();
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
		if (randNum < myPercentShark) {
			currCell.setCurrState(SHARK, stateToColorMap.get(SHARK));
			mySharks.put(currCell, new Shark(mySharkBreedTime, mySharkStarveTime));
		}
		else if (randNum >= myPercentShark && randNum < myPercentShark + myPercentFish) {
			currCell.setCurrState(FISH, stateToColorMap.get(FISH));
			myFish.put(currCell, new Fish(myFishBreedTime));
		}
		else {
			currCell.setCurrState(EMPTY, stateToColorMap.get(EMPTY));
		}
	}

	@Override
	protected void addNeighbors(Cell currCell) {
		for (Cell neighbor : makeNeighborList(currCell)) {
			currCell.addNeighbor(neighbor);
		}
	}
	
	private ArrayList<Cell> makeNeighborList(Cell currCell) {
		int row = currCell.getPosition().getIPos();
		int col = currCell.getPosition().getJPos();
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(myGrid.getCell(getNeighborIPosition(row, -1), col));
		neighbors.add(myGrid.getCell(getNeighborIPosition(row, 1), col));
		neighbors.add(myGrid.getCell(row, getNeighborJPosition(col, -1)));
		neighbors.add(myGrid.getCell(row, getNeighborJPosition(col, 1)));
		return neighbors;
	}
	
	@Override
	protected void commitStates(){
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j < myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				currCell.commitState(stateToColorMap.get(currCell.getNextState()));
			}
		}
		mySharks = new HashMap<Cell, Shark>(tempSharkMap);
		myFish = new HashMap<Cell, Fish>(tempFishMap);
		tempSharkMap.clear();
		tempFishMap.clear();
	}
	@Override
	protected void updateNextStates() {
		for (int row = 0; row < myGrid.getHeight(); row++) {
			for (int col = 0; col < myGrid.getWidth(); col++) {
				Cell currCell = myGrid.getCell(row, col);
				if (currCell.getCurrState() == FISH) {
					handleFish(currCell);
				}
				else if (currCell.getCurrState() == SHARK) {
					handleShark(currCell);
				}
			}
		}
	}

	private void handleFish(Cell currCell) {
		//check if the fish has been eaten 
		ArrayList<Cell> neighbors = makeNeighborList(currCell);
		if(myFish.containsKey(currCell)) {
			for (Cell neighbor : neighbors) {
				if (mySharks.containsKey(neighbor)) {
					if (!tempSharkMap.containsKey(neighbor)) { //this shark has not yet eaten this round
						Shark predator = mySharks.get(neighbor);
						currCell.setNextState(EMPTY);
						predator.markAsFull();
						myFish.remove(currCell);
						tempSharkMap.put(neighbor, predator);
						break;
					}
				}
			}
		}
		
		//if the fish still has not been eaten
		if (myFish.containsKey(currCell)) {
			Fish currFish = myFish.get(currCell);
			for (Cell neighbor : neighbors) {
				if (neighbor.getCurrState() == EMPTY) {
					if (currFish.getTimeLeftToBreed() == 0) { //breed new fish in adjacent cell
						neighbor.setNextState(FISH);
						tempFishMap.put(neighbor, new Fish(myFishBreedTime));
						currFish.resetTimeToBreed();
						tempFishMap.put(currCell, currFish);
						break;
					}
					else { //move fish to adjacent cell
						currCell.setNextState(EMPTY);
						neighbor.setNextState(FISH);
						currFish.decrementBreedTime();
						tempFishMap.put(neighbor, currFish);
					}
				}
			}
		}
		
	}
	
	private void handleShark(Cell currCell) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		//check if the shark has already eaten a fish this round
		//we do this by checking if the shark was already added to the tempMap
		if (tempSharkMap.containsKey(currCell)) {
			Shark currShark = tempSharkMap.get(currCell);
			currCell.setNextState(SHARK);
			if (currShark.getTimeLeftToBreed() == 0) {
				for (Cell neighbor : neighbors) {
					if (neighbor.getCurrState() == EMPTY) {
						neighbor.setNextState(SHARK);
						tempSharkMap.put(neighbor, new Shark(mySharkBreedTime, mySharkStarveTime));
						currShark.resetTimeToBreed();
						tempSharkMap.put(currCell, currShark);
					}
				}
			}
		}
		else { //if shark hasn't eaten yet
			Shark currShark = mySharks.get(currCell);
			for (Cell neighbor : neighbors) {
				if (myFish.containsKey(neighbor)) { //shark can eat
					currShark.markAsFull();
					neighbor.setNextState(EMPTY);
					currCell.setNextState(SHARK);
				}
				else {//shark doesn't eat
					currShark.decrementTimeUntilStarve(); 
					if (currShark.getTimeUntilStarve() == 0) { //shark is dead
						mySharks.remove(currCell);
						currCell.setNextState(EMPTY);
					}
				}
			}
		}
		//if shark is still alive
		if (mySharks.containsKey(currCell)) {
			Shark currShark = mySharks.get(currCell);
			currCell.setNextState(SHARK);
			if (currShark.getTimeLeftToBreed() == 0) {
				for (Cell neighbor : neighbors) {
					if (neighbor.getCurrState() == EMPTY) {
						neighbor.setNextState(SHARK);
						tempSharkMap.put(neighbor, new Shark(mySharkBreedTime, mySharkStarveTime));
						currShark.resetTimeToBreed();
						tempSharkMap.put(currCell, currShark);
					}
				}
			}
		}
		
	}
		
	
	@Override
	protected void mapStatesToColors() {
		stateToColorMap.put(EMPTY, Color.AQUAMARINE);
		stateToColorMap.put(SHARK, Color.RED);
		stateToColorMap.put(FISH, Color.GREEN);	
	}
	
	

}
