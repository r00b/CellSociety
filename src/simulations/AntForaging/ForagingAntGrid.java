package simulations.AntForaging;

import simulations.Cell;
import simulations.Grid;

public class ForagingAntGrid extends Grid {

	public ForagingAntGrid(int width, int Height, String xmlFileName) {
		super(width, Height, xmlFileName);
	}

	@Override
	protected Cell getNewCell(int i, int j, String xmlFileName) {
		return new ForagingAntCell(i, j, xmlFileName);
	}

}
