package simulations.Wator;

import simulations.Cell;
import simulations.Grid;

public class WatorGrid extends Grid{

	public WatorGrid(int width, int Height, String XMLFileName) {
		super(width, Height,XMLFileName);
		
	}

	@Override
	protected Cell getNewCell(int i, int j, String XMLFileName) {
		
		return new WatorCell(i, j, XMLFileName);
	}

}
