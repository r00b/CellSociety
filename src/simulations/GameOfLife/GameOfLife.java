//This entire file is part of my masterpiece.
//Sam Curtis

//I believe that this class represents good design because it does one job and 
//does it simply. It sets up an initial grid for the game of life simulation using
//the methods of the myGrid variable and using methods associated with each cell within
//the grid. Then, it defines a function for updating the grid, that can be called
//when necessary. Both methods are well named, simple to understand, and readable.
//Finally it has a method that maps the states to strings, which are taken from a resource file
//instead of being hard-coded in. 
package simulations.GameOfLife;


import simulations.Simulation;
import xml.GameOfLifeXMLParser;


/**
 * @author samuelcurtis
 *         <p>
 *         GameOfLife class handles the logic of initializing a grid of cells
 *         and updating their states according to the Game of Life simulation
 *         rules. Offers two public methods, getGrid() and updateGrid() so that
 *         an Animation object can get the grid and continually update it to
 *         allow for the visualization of the simulation.
 */

public class GameOfLife extends Simulation {
    private final GameOfLifeXMLParser myParser;
    private final GameOfLifeGrid myGrid;

    /**
     * Reads in the data for probCellAlive and the grid dimensions using the
     * GameOfLifeXMLParser object. Initializes the grid.
     */
    public GameOfLife(String xmlFile) {
        super(xmlFile);
        myParser = new GameOfLifeXMLParser(xmlFile);
        myGrid = new GameOfLifeGrid(myParser.getGridWidth(), myParser.getGridHeight(), xmlFile);
        setGrid(myGrid);
        setInitialGridState();
    }

    /**
     * For each cell in the grid, a random initial state is set, and its
     * neighbors are calculated.
     */
    protected void setInitialGridState() {
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j = 0; j < myGrid.getWidth(); j++) {
                GameOfLifeCell currCell = (GameOfLifeCell) myGrid.getCell(i, j);
                currCell.setRandomInitialState();
                currCell.setNeighborhood(myGrid);
            }
        }
    }

    /**
     * Goes through each cell in the grid and calculates what its next state
     * will be based on the Game of Life rules.
     */
    protected void updateNextStates() {
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j = 0; j < myGrid.getWidth(); j++) {
                GameOfLifeCell currCell = (GameOfLifeCell) myGrid.getCell(i, j);
                int numNeighborsAlive = currCell.getNumNeighborsAlive();
                if (currCell.isDead()) {
                    if (numNeighborsAlive == 3) { //new cell born
                        currCell.setNextStateAlive();

                    } else {
                        currCell.setNextStateDead(); //stays dead
                    }
                } else { //cell is currently alive
                    if (numNeighborsAlive == 2 || numNeighborsAlive == 3) {//stays alive
                        currCell.setNextStateAlive();
                    } else {
                        currCell.setNextStateDead(); //dies
                    }
                }
            }
        }
    }

    @Override
    protected void setStateMap() {
        myStateMap.put(GameOfLifeCell.ALIVE, myResources.getString("AliveState"));
        myStateMap.put(GameOfLifeCell.DEAD, myResources.getString("DeadState"));
    }

}

