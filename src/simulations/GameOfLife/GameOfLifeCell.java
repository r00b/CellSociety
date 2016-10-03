package simulations.GameOfLife;

import java.util.Iterator;
import java.util.Random;
import simulations.Cell;
import simulations.Grid;
import xml.GameOfLifeXMLParser;

/**
 * @author samuelcurtis
 *The GameOfLifeCell class is a subclass of the Cell class. These cells represent
 *cells used in the Game of Life simulation. They can be either dead or alive, and
 *their initial state is determined randomly by the probability read in from an 
 *xml file. 
 */
public class GameOfLifeCell extends Cell {
	public static final int DEAD = 0;
	public static final int ALIVE = 1;
	private final int probCellAlive;
	private GameOfLifeXMLParser myParser;
	
	
	public GameOfLifeCell(int i, int j, String xmlFile) {
		super(i, j);
		myParser = new GameOfLifeXMLParser(xmlFile);
		probCellAlive = myParser.getProbOfCellAlive();
	}
	
	/**
	 * Set the next state of the cell to DEAD
	 */
	public void setNextStateDead(){
		setNextState(DEAD);
	}
	
	/**
	 * Set the next state of the cell to ALIVE
	 */
	public void setNextStateAlive(){
		this.setNextState(ALIVE);
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
	
	
	/**
	 * @return True if the current state of the cell is DEAD, false otherwise
	 */
	public boolean isDead(){
		return this.getCurrState() == DEAD;
	}

	@Override
	public void setRandomInitialState() {
		setRandomState(probCellAlive);
	}
	
	/**
	 * @return the number of neighbors that have state ALIVE
	 */
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
		int numVertices = myParser.getNumCellVertices();
		boolean isToroidal = myParser.isToroidal();
		if(numVertices == 3 || numVertices == 8){
			if(isToroidal){
				getMyNeighborhood().set_EightNeighbor_Wraparound(this,grid);
			}
			else{
				getMyNeighborhood().set_EightNeighbor_NoWraparound(this, grid);
			}
		}
		else{
			if(isToroidal){
				getMyNeighborhood().set_Six_Neighbor_Wraparound(this, grid);
			}
			else{
				getMyNeighborhood().set_Six_Neighbor_NoWraparound(this, grid);
			}
		}
		
	}
	
}
