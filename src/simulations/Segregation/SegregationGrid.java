package simulations.Segregation;

import simulations.Cell;
import simulations.Grid;


public class SegregationGrid extends Grid {

	public SegregationGrid(int width, int Height) {
		super(width, Height);
	}

	@Override
	protected Cell getNewCell(int i, int j) {
		return new SegregationCell(i,j);
	}

}
