package simulations.Fire;

import simulations.Cell;
import simulations.Grid;

public class FireGrid extends Grid{

	public FireGrid(int width, int Height, String XMLFileName) {
		super(width, Height,XMLFileName);
	}

	@Override
	protected Cell getNewCell(int i, int j,String XMLFileName) {
		return new FireCell(i,j,XMLFileName);
	}

}
