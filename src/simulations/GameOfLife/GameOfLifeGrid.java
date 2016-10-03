package simulations.GameOfLife;

import simulations.Cell;
import simulations.Grid;


/**
 * @author samuelcurtis
 *GameOfLifeGrid is a subclass of Grid that is used for the Game Of Life simulation type. It 
 *overrides the getNewCell method to return a GameOfLifeCell so that the grid 
 *is populated by GameOfLifeCells upon initialization. 
 */
public class GameOfLifeGrid extends Grid {
	
	public GameOfLifeGrid(int width, int Height, String XMLFileName) {
		super(width, Height, XMLFileName);
	}
	
	@Override
	public Cell getNewCell(int i, int j, String XMLFileName){
		return new GameOfLifeCell(i, j,XMLFileName);
	}
}
