package simulations.Wator;

import simulations.Cell;
import simulations.Grid;

public class WatorGrid extends Grid{

	public WatorGrid(int width, int Height) {
		super(width, Height);
		
	}

	@Override
	protected Cell getNewCell(int i, int j) {
		
		return new WatorCell(i, j);
	}

}
