package simulations.GameOfLife;


import simulations.Simulation;
import xml.GameOfLifeXMLParser;


/**
 * @author samuelcurtis
 * 
 *         GameOfLife class handles the logic of initializing a grid of cells
 *         and updating their states according to the Game of Life simulation
 *         rules. Offers two public methods, getGrid() and updateGrid() so that
 *         an Animation object can get the grid and continually update it to
 *         allow for the visualization of the simulation.
 *
 */

public class GameOfLife extends Simulation {
	private final GameOfLifeXMLParser myParser;
	private final GameOfLifeGrid myGrid;

	/**
	 * Reads in the data for probCellAlive and the grid dimensions using the
	 * GameOfLifeXMLParser object. Initializes the grid.
	 */
	public GameOfLife(String xmlFile) {
		myParser = new GameOfLifeXMLParser(xmlFile);
		myGrid = new GameOfLifeGrid(myParser.getGridWidth(), myParser.getGridHeight(), xmlFile);
		setGrid(myGrid);
		setInitialGridState();
	}
	
	/**
	 * For each cell in the grid, a random initial state is set, and its
	 * neighbors are calculated.
	 */
	protected void setInitialGridState() {
		for (int i = 0; i < myGrid.getHeight(); i++) {
			for (int j = 0; j < myGrid.getWidth(); j++) {
				GameOfLifeCell currCell = (GameOfLifeCell) myGrid.getCell(i, j);
				currCell.setRandomInitialState();
				currCell.setNeighborhood(myGrid);
			}
		}
	}
	
	/**
	 * Goes through each cell in the grid and calculates what its next state
	 * will be based on the Game of Life rules.
	 */
	protected void updateNextStates() {
		for (int i = 0; i < myGrid.getHeight(); i++) {
			for (int j = 0; j < myGrid.getWidth(); j++) {
				GameOfLifeCell currCell =(GameOfLifeCell) myGrid.getCell(i, j);
				int numNeighborsAlive = currCell.getNumNeighborsAlive();
				if (currCell.isDead()) {
					if (numNeighborsAlive == 3) {
						currCell.setNextStateAlive();

					}
					else {
						currCell.setNextStateDead();
					}
				} 
				else {
					if (numNeighborsAlive == 2 || numNeighborsAlive == 3) {
						currCell.setNextStateAlive();
					} 
					else {
						currCell.setNextStateDead();
					}
				}
			}
		}
	}
	
}

