package simulations.Wator;

import simulations.Cell;
import simulations.Grid;

/**
 * @author samuelcurtis
 *WatorGrid is a subclass of Grid that is used for the Wator/Predator-Prey simulation type. It 
 *overrides the getNewCell method to return a WatorCell so that the grid 
 *is populated by WatorCells upon initialization. 
 */
public class WatorGrid extends Grid{

	public WatorGrid(int width, int Height, String XMLFileName) {
		super(width, Height,XMLFileName);
		
	}

	@Override
	protected Cell getNewCell(int i, int j, String XMLFileName) {
		
		return new WatorCell(i, j, XMLFileName);
	}

}
