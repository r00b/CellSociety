package simulations.Fire;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import simulations.Cell;
import simulations.Grid;
import simulations.GameOfLife.GameOfLifeCell;
import xml.FireXMLParser;

public class FireCell extends Cell {
	private final int EMPTY = 0;
	private final int BURNING = 1;
	private final int TREE = 2;
	private final FireXMLParser myParser;
	private final int probCatchFire;
	private final int burnTime;
	private int timeLeftToBurn;
	
	public FireCell(int i, int j,String XMLFileName) {
		super(i, j);
		myParser = new FireXMLParser(XMLFileName);
		probCatchFire = myParser.getProbCatchFire();
		burnTime = myParser.getBurnDownTime();
		timeLeftToBurn = 0;
	}
	
	public void setNextStateEmpty(){
		setNextState(EMPTY);
	}
	public void setNextStateBurning(){
		setNextState(BURNING);
		if(timeLeftToBurn == 0){
			timeLeftToBurn = burnTime;
		}
	}
	
	public void setNextStateTree(){
		setNextState(TREE);
	}
	
	public void setEmpty(){
		setCurrState(EMPTY, findStateColor(EMPTY));
	}
	
	public void setTree(){
		setCurrState(TREE, findStateColor(TREE));
	}
	
	public void setBurning(){
		setCurrState(BURNING, findStateColor(BURNING));
		timeLeftToBurn = burnTime;
	}
	
	public boolean isBurning(){
		return getCurrState() == BURNING;
	}
	
	public boolean isEmpty(){
		return getCurrState() == EMPTY;
	}
	
	
	@Override
	public void mapStatesToColors() {
		updateColorMap(EMPTY, myParser.getEmptyColor());
		updateColorMap(BURNING, myParser.getBurningColor());
		updateColorMap(TREE, myParser.getTreeColor());
	}

	@Override
	public void setRandomInitialState() {
		// TODO Auto-generated method stub

	}
	/**
	 * @param currCell the cell for which we want to check if it is done burning
	 * @return A boolean value representing whether or not the cell has finished burning down
	 */
	public boolean isDoneBurning() {
		return timeLeftToBurn == 0;
	}


	/**
	 * @param currCell - a cell that is burning at the start of a generation
	 * Makes sure that a cell is noted as having burned for a previous turn
	 * 
	 */
	public void updateBurnTime() {
			timeLeftToBurn--;
	}

	/**
	 * @return randomly returns true or false based on value of probCatchFire
	 * Used to see if a cell that has a tree in it catches fire if one if its neighboring cells is on fire
	 */
	public boolean doesCatchFire() {
		Random random = new Random();
		int randNum = random.nextInt(101);
		if(randNum < probCatchFire){
			return true;
		}
		return false;
	}
	
	/**
	 * @param currCell - the cell for which we want to see if any of its neighbors are burning
	 * @return boolean -> true if any of currCells neighbor are in the state BURNING, false otherwise 
	 */
	public boolean isNeighborBurning() {
		for (Iterator i = getNeighbors(); i.hasNext();) {
			FireCell neighbor = (FireCell) i.next();
			if (neighbor.isBurning()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setNeighborhood(Grid grid) {
		getMyNeighborhood().setDefaultFireNeighborhood(this, grid);
		
	}

	public int getTimeLeftBurning() {
		return timeLeftToBurn;
	}


}
