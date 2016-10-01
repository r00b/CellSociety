package simulations.GameOfLife;

import simulations.Cell;
import simulations.Grid;

public class GameOfLifeGrid extends Grid {

	public GameOfLifeGrid(int width, int Height) {
		super(width, Height);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Cell getNewCell(int i, int j){
		return new GameOfLifeCell(i, j);
	}
	

}
