//This entire file is part of my masterpiece.
//Sam Curtis 

//I believe this code is well designed because it makes smart use of a superclass.
//This class is a subclass of Grid. The only thing that it needs to do differently 
//is override the getNewCell method that is called when a grid is created.
//It needs to do this because getNewCell is an abstract method. Here, since 
//a game of life grid is composed of Game of Life cells, this method simpy returns
//a new GameOfLifeCell at the specified (i,j) location within the grid.
package simulations.GameOfLife;

import simulations.Cell;
import simulations.Grid;


/**
 * @author samuelcurtis
 *         GameOfLifeGrid is a subclass of Grid that is used for the Game Of Life simulation type. It
 *         overrides the getNewCell method to return a GameOfLifeCell so that the grid
 *         is populated by GameOfLifeCells upon initialization.
 */
public class GameOfLifeGrid extends Grid {

    public GameOfLifeGrid(int width, int Height, String XMLFileName) {
        super(width, Height, XMLFileName);
    }

    @Override
    public Cell getNewCell(int i, int j, String XMLFileName) {
        return new GameOfLifeCell(i, j, XMLFileName);
    }
}
