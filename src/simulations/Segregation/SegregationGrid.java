package simulations.Segregation;

import simulations.Cell;
import simulations.Grid;


/**
 * @author samuelcurtis
 *         SegregationGrid is a subclass of Grid that is used for the Segregation simulation type. It
 *         overrides the getNewCell method to return a SegregationCell so that the grid
 *         is populated by SegregationCells upon initialization.
 */
public class SegregationGrid extends Grid {

    public SegregationGrid(int width, int Height, String XMLFileName) {
        super(width, Height, XMLFileName);
    }

    @Override
    protected Cell getNewCell(int i, int j, String XMLFileName) {
        return new SegregationCell(i, j, XMLFileName);
    }

}
