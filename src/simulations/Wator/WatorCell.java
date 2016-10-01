package simulations.Wator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import simulations.Cell;
import simulations.Grid;
import xml.WatorXMLParser;

public class WatorCell extends Cell {
	public static final int EMPTY = 0;
	public static final int SHARK = 1;
	public static final int FISH = 2;
	private final int myPercentShark;
	private final int myPercentFish;
	private final WatorXMLParser myParser;
	
	public WatorCell(int i, int j) {
		super(i, j);
		myParser = new WatorXMLParser("data/Wator.xml");
		myPercentShark = myParser.getPercentShark();
		myPercentFish = myParser.getPercentFish();
		
	}
	
	public boolean isEmpty(){
		return getCurrState() == EMPTY;
	}
	
	public boolean isShark(){
		return getCurrState() == SHARK;
	}
	
	public boolean isFish(){
		return getCurrState() == FISH;
	}
	
	public void setNextStateEmpty(){
		setNextState(EMPTY);
	}
	
	public void setNextStateShark(){
		setNextState(SHARK);
	}
	
	public void setNextStateFish(){
		setNextState(FISH);
	}

	@Override
	public void mapStatesToColors() {
		updateColorMap(EMPTY, myParser.getEmptyColor());
		updateColorMap(SHARK, myParser.getSharkColor());
		updateColorMap(FISH,myParser.getFishColor());
	}

	@Override
	public void setRandomInitialState() {
		Random randomNumGenerator = new Random();
		int randNum = randomNumGenerator.nextInt(101);
		if (randNum < myPercentShark) {
			setCurrState(SHARK, findStateColor(SHARK));
		}
		else if (randNum >= myPercentShark && randNum < myPercentShark + myPercentFish) {
			setCurrState(FISH, findStateColor(FISH));
		}
		else {
			setCurrState(EMPTY, findStateColor(EMPTY));
		}
		
	}

	@Override
	public void setNeighborhood(Grid grid) {
		getMyNeighborhood().set_FourNeighbor_Wraparound(this, grid);
	}
	
	
	/**
	 * this method is a helper method for the handleShark and handleFish methods
	 * It takes a cell's list of neighbors, filters it by a specified state, and returns a random neighbor of that state
	 * if there are no neighbors of that state, it returns null
	 */
	public WatorCell getRandomNeighborByState(int state) {
		ArrayList<WatorCell> filteredNeighbors = new ArrayList<WatorCell>();
		for (Iterator i = getNeighbors(); i.hasNext();) {
			WatorCell neighbor = (WatorCell) i.next();
			if (neighbor.getCurrState() == state) {
				filteredNeighbors.add(neighbor);
			}
		}
		if (filteredNeighbors.size() > 0) {
			Random randGenerator = new Random();
			int randIndex = randGenerator.nextInt(filteredNeighbors.size());
			return filteredNeighbors.get(randIndex);
		}
		else {
			return null;
		}
	}	
}
