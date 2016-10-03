package simulations.Fire;

import simulations.Cell;
import simulations.Grid;

/**
 * @author samuelcurtis
 *FireGrid is a subclass of Grid that is used for the Fire simulation type. It 
 *overrides the getNewCell method to return a FireCell so that the grid 
 *is populated by FireCells upon initialization. 
 */
public class FireGrid extends Grid{

	public FireGrid(int width, int Height, String XMLFileName) {
		super(width, Height,XMLFileName);
	}

	@Override
	protected Cell getNewCell(int i, int j,String XMLFileName) {
		return new FireCell(i,j,XMLFileName);
	}

}
