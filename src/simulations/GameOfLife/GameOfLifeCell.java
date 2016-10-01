package simulations.GameOfLife;

import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;
import simulations.Cell;
import simulations.Grid;
import xml.GameOfLifeXMLParser;

public class GameOfLifeCell extends Cell {
	private final int DEAD = 0;
	private final int ALIVE = 1;
	private final int probCellAlive;
	private GameOfLifeXMLParser myParser;
	
	
	public GameOfLifeCell(int i, int j, String xmlFile) {
		super(i, j);
		myParser = new GameOfLifeXMLParser(xmlFile);
		probCellAlive = myParser.getProbOfCellAlive();
	}
	
	
	
	public void setNextStateDead(){
		setNextState(DEAD);
	}
	
	/**
	 * @param prob_Cell_Alive
	 *            represents the probability that a cell is alive at the start
	 *            of the simulation.
	 * @param currCell
	 *            is the cell for which we want an initial state to be randomly
	 *            chosen
	 */
	public void setRandomState(int prob_Cell_Alive) {
		Random random = new Random();
		int randNum = random.nextInt(101);
		if (randNum > prob_Cell_Alive) {
			setCurrState(DEAD, findStateColor(DEAD));
		} else {
			setCurrState(ALIVE, findStateColor(ALIVE));;
		}
	}
	
	@Override
	public void mapStatesToColors() {
		updateColorMap(DEAD,myParser.getDeadColor());
		updateColorMap(ALIVE,myParser.getAliveColor());
	}
	
	
	public void setNextStateAlive(){
		this.setNextState(ALIVE);
	}
	
	public boolean isDead(){
		return this.getCurrState() == DEAD;
	}

	@Override
	public void setRandomInitialState() {
		setRandomState(probCellAlive);
	}
	
	public int getNumNeighborsAlive(){
		int numAlive = 0;
		for (Iterator i = getNeighbors(); i.hasNext();) {
			GameOfLifeCell neighbor = (GameOfLifeCell) i.next();
			if (!neighbor.isDead()) {
				numAlive += 1;
			}
		}
		return numAlive;
	}

	@Override
	public void setNeighborhood(Grid grid) {
		getMyNeighborhood().set_EightNeighbor_Wraparound_Neighborhood(this, grid);
	}
	
}
