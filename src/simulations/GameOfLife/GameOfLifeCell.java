//This entire file is part of my masterpiece. 
//Sam Curtis 

//I believe this class is well designed because it is a good usage of inheritance
//This class is a sub-class of the cell superclass. It overrides the appropriate abstract
//methods. It also handles logic that is specifically related to a Game of Life cell.
//For example, these cells can only be in two states, DEAD or ALIVE, so these two 
//instance variables are included here, but are not included in the superclass,
//since not all cells will have those as possible states. 
//This class is also an example of good design because it has a clear purpose 
//and it sticks to it. This class only contains methods that relate 
//to the behavior of an individual cell within the Game of Life simulation. 
package simulations.GameOfLife;

import java.util.Iterator;
import java.util.Random;

import simulations.Cell;
import simulations.Grid;
import xml.GameOfLifeXMLParser;

/**
 * @author samuelcurtis
 *         The GameOfLifeCell class is a subclass of the Cell class. These cells represent
 *         cells used in the Game of Life simulation. They can be either dead or alive, and
 *         their initial state is determined randomly by the probability read in from an
 *         xml file.
 */
public class GameOfLifeCell extends Cell {
    public static final int DEAD = 0;
    public static final int ALIVE = 1;
    private final int probCellAlive;
    private GameOfLifeXMLParser myParser;


    public GameOfLifeCell(int i, int j, String xmlFile) {
        super(i, j);
        myParser = new GameOfLifeXMLParser(xmlFile);
        probCellAlive = myParser.getProbOfCellAlive();
    }

    /**
     * Set the next state of the cell to DEAD
     */
    public void setNextStateDead() {
        setNextState(DEAD);
    }

    /**
     * Set the next state of the cell to ALIVE
     */
    public void setNextStateAlive() {
        setNextState(ALIVE);
    }

    /**
     * @param prob_Cell_Alive represents the probability that a cell is alive at the start
     *                        of the simulation.
     * @param currCell        is the cell for which we want an initial state to be randomly
     *                        chosen
     */
    public void setRandomState(int prob_Cell_Alive) {
        int randNum = getRandInt_FromZero_ToOneHundred();
        if (randNum > prob_Cell_Alive) {
            setCurrState(DEAD, findStateColor(DEAD));
        } else {
            setCurrState(ALIVE, findStateColor(ALIVE));
            ;
        }
    }

    @Override
    public void mapStatesToColors() {
        updateColorMap(DEAD, myParser.getDeadColor());
        updateColorMap(ALIVE, myParser.getAliveColor());
    }


    /**
     * @return True if the current state of the cell is DEAD, false otherwise
     */
    public boolean isDead() {
        return this.getCurrState() == DEAD;
    }

    @Override
    public void setRandomInitialState() {
        setRandomState(probCellAlive);
    }

    /**
     * @return the number of neighbors that have state ALIVE
     */
    public int getNumNeighborsAlive() {
        int numAlive = 0;
        for (Iterator i = getNeighbors(); i.hasNext(); ) {
            GameOfLifeCell neighbor = (GameOfLifeCell) i.next();
            if (!neighbor.isDead()) {
                numAlive += 1;
            }
        }
        return numAlive;
    }

    @Override
    public void setNeighborhood(Grid grid) {
        getMyNeighborhood().set_EightNeighbor_Wraparound(this, grid);
//		int numVertices = myParser.getNumCellVertices();
//		boolean isToroidal = myParser.isToroidal();
//		if(numVertices == 3 || numVertices == 8){
//			if(isToroidal){
//				getMyNeighborhood().set_EightNeighbor_Wraparound(this,grid);
//			}
//			else{
//				getMyNeighborhood().set_EightNeighbor_NoWraparound(this, grid);
//			}
//		}
//		else{
//			if(isToroidal){
//				getMyNeighborhood().set_Six_Neighbor_Wraparound(this, grid);
//			}
//			else{
//				getMyNeighborhood().set_Six_Neighbor_NoWraparound(this, grid);
//			}
//		}

    }

    private int getRandInt_FromZero_ToOneHundred() {
        Random random = new Random();
        return random.nextInt(101);
    }

}
