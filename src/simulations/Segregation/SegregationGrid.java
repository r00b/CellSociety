package simulations.Segregation;

import simulations.Cell;
import simulations.Grid;


public class SegregationGrid extends Grid {

	public SegregationGrid(int width, int Height, String XMLFileName) {
		super(width, Height,XMLFileName);
	}

	@Override
	protected Cell getNewCell(int i, int j, String XMLFileName) {
		return new SegregationCell(i,j, XMLFileName);
	}

}
