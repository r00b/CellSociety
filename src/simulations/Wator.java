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
	private HashMap<Cell, Fish> myFish;
	private HashMap<Cell, Shark> mySharks;
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
		}
		else if (randNum >= myPercentShark && randNum < myPercentShark + myPercentFish) {
			currCell.setCurrState(FISH, stateToColorMap.get(FISH));
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
	protected void updateNextStates() {
		for (int row = 0; row < myGrid.getHeight(); row++) {
			for (int col = 0; col < myGrid.getWidth(); col++) {
				Cell currCell = myGrid.getCell(row, col);
				if (currCell.getCurrState() == FISH) {
					handleFish(currCell);
				}
			}
		}
		
	}

	private void handleFish(Cell currCell) {
		for (Cell neighbor : makeNeighborList(currCell)) {
			if (neighbor.getCurrState() == SHARK) {
				//shark moves out of cell, into fish's cell
				neighbor.setNextState(EMPTY);
				currCell.setNextState(SHARK);
				
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
