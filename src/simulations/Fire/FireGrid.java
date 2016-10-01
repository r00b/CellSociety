package simulations.Fire;

import simulations.Cell;
import simulations.Grid;

public class FireGrid extends Grid{

	public FireGrid(int width, int Height) {
		super(width, Height);
	}

	@Override
	protected Cell getNewCell(int i, int j) {
		return new FireCell(i,j);
	}

}
