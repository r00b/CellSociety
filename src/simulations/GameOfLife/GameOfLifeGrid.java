package simulations.GameOfLife;

import simulations.Cell;
import simulations.Grid;

public class GameOfLifeGrid extends Grid {

	public GameOfLifeGrid(int width, int Height, String XMLFileName) {
		super(width, Height, XMLFileName);
	}

	@Override
	public Cell getNewCell(int i, int j, String xml) {
		return new GameOfLifeCell(i, j, xml);
	}

}
