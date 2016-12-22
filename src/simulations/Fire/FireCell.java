package simulations.Fire;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import simulations.Cell;
import simulations.Grid;
import simulations.GameOfLife.GameOfLifeCell;
import xml.FireXMLParser;

/**
 * @author samuelcurtis
 *         Subclass of cell used in the Fire.java simulation. Populates the FireGrid.
 *         FireCells can either be empty, burning, or contain a tree.
 *         FireCells take a certain number of time steps to burn down depending
 *         on the value given in the XML file. Also, the probability of a tree catching
 *         on fire given that a neighbor is on fire is given by the XML file as well.
 */
public class FireCell extends Cell {
    public static final int EMPTY = 0;
    public static final int BURNING = 1;
    public static final int TREE = 2;
    private final FireXMLParser myParser;
    private final int probCatchFire;
    private final int burnTime;
    private int timeLeftToBurn;

    public FireCell(int i, int j, String XMLFileName) {
        super(i, j);
        myParser = new FireXMLParser(XMLFileName);
        probCatchFire = myParser.getProbCatchFire();
        burnTime = myParser.getBurnDownTime();
        timeLeftToBurn = 0;
    }

    /**
     * Set the next state of the cell to be empty
     */
    public void setNextStateEmpty() {
        setNextState(EMPTY);
    }

    /**
     * Set the next state of the cell to burning. If this is the first time
     * the cell is catching fire, set its timeLeftToBurn to however long
     * the time it takes to burn down is.
     */
    public void setNextStateBurning() {
        setNextState(BURNING);
        if (timeLeftToBurn == 0) {
            timeLeftToBurn = burnTime;
        }
    }

    /**
     * Set the next state of the cell to TREE
     */
    public void setNextStateTree() {
        setNextState(TREE);
    }

    /**
     * Set the current state of the cell to EMPTY
     */
    public void setEmpty() {
        setCurrState(EMPTY, findStateColor(EMPTY));
    }

    /**
     * Set the current state of the cell to TREE
     */
    public void setTree() {
        setCurrState(TREE, findStateColor(TREE));
    }

    /**
     * Set the current state of the cell to BURNING
     */
    public void setBurning() {
        setCurrState(BURNING, findStateColor(BURNING));
        timeLeftToBurn = burnTime;
    }

    /**
     * @return true if the current state of the cell is BURNING, false otherwise
     */
    public boolean isBurning() {
        return getCurrState() == BURNING;
    }

    /**
     * @return true if the current state of the cell is EMPTY, false otherwise
     */
    public boolean isEmpty() {
        return getCurrState() == EMPTY;
    }


    @Override
    public void mapStatesToColors() {
        updateColorMap(EMPTY, myParser.getEmptyColor());
        updateColorMap(BURNING, myParser.getBurningColor());
        updateColorMap(TREE, myParser.getTreeColor());
    }

    @Override
    public void setRandomInitialState() {
        // TODO Auto-generated method stub

    }

    /**
     * @param currCell the cell for which we want to check if it is done burning
     * @return A boolean value representing whether or not the cell has finished burning down
     */
    public boolean isDoneBurning() {
        return timeLeftToBurn == 0;
    }


    /**
     * @param currCell - a cell that is burning at the start of a generation
     *                 Makes sure that a cell is noted as having burned for a previous turn
     */
    public void updateBurnTime() {
        timeLeftToBurn--;
    }

    /**
     * @return randomly returns true or false based on value of probCatchFire
     * Used to see if a cell that has a tree in it catches fire if one if its neighboring cells is on fire
     */
    public boolean doesCatchFire() {
        Random random = new Random();
        int randNum = random.nextInt(101);
        if (randNum < probCatchFire) {
            return true;
        }
        return false;
    }

    /**
     * @param currCell - the cell for which we want to see if any of its neighbors are burning
     * @return boolean -> true if any of currCells neighbor are in the state BURNING, false otherwise
     */
    public boolean isNeighborBurning() {
        for (Iterator i = getNeighbors(); i.hasNext(); ) {
            FireCell neighbor = (FireCell) i.next();
            if (neighbor.isBurning()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setNeighborhood(Grid grid) {
        int numVertices = myParser.getNumCellVertices();
        boolean isToroidal = myParser.isToroidal();
        if (numVertices == 3 || numVertices == 8) {
            if (isToroidal) {
                getMyNeighborhood().set_FourNeighbor_Wraparound(this, grid);
            } else {
                getMyNeighborhood().setDefaultFireNeighborhood(this, grid);
            }
        } else {
            if (isToroidal) {
                getMyNeighborhood().set_Six_Neighbor_Wraparound(this, grid);
            } else {
                getMyNeighborhood().set_Six_Neighbor_NoWraparound(this, grid);
            }
        }

    }

    /**
     * @return the number of generations that a burning tree still needs to completely
     * burn down
     */
    public int getTimeLeftBurning() {
        return timeLeftToBurn;
    }


}
