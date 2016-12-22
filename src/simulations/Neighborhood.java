package simulations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * @author samuelcurtis
 *         <p>
 *         The neighborhood class is used to store the neighbors of cells. It also contains
 *         all implemented methods for defining neighborhoods, so that cells can simply
 *         call the appropriate method to set their neighbors.
 */
public class Neighborhood implements Iterable<Cell> {
    private List<Cell> myNeighbors;

    public Neighborhood() {
        myNeighbors = new ArrayList<Cell>();
    }

    /**
     * @param neighboringCell is a cell that neighbors the cell calling this method
     */
    public void addNeighbor(Cell neighboringCell) {
        myNeighbors.add(neighboringCell);
    }

    /**
     * @param currCell is the cell for which we want to calculate its neighbors and store
     *                 them.
     *                 <p>
     *                 This method calls two helper methods, getNeighborIPosition and getNeighborJposition,
     *                 which assist in the logic of calculating the neighbors of edge cells.
     */
    public void set_EightNeighbor_Wraparound(Cell currCell, Grid grid) {
        Tuple position = currCell.getPosition();
        for (int k = -1; k < 2; k++) {
            for (int g = -1; g < 2; g++) {
                int i = getNeighborIPosition(position.getIPos(), k, grid, true);
                int j = getNeighborJPosition(position.getJPos(), g, grid, true);
                if (!(i == position.getIPos() && j == position.getJPos())) {
                    currCell.addNeighbor(grid.getCell(i, j));
                }
            }
        }
    }

    public void set_EightNeighbor_NoWraparound(Cell currCell, Grid grid) {
        Tuple position = currCell.getPosition();
        for (int k = -1; k < 2; k++) {
            for (int g = -1; g < 2; g++) {
                int i = getNeighborIPosition(position.getIPos(), k, grid, false);
                int j = getNeighborJPosition(position.getJPos(), g, grid, false);
                if (i == -1 || j == -1) {
                    continue;
                }
                if (!(i == position.getIPos() && j == position.getJPos())) {
                    currCell.addNeighbor(grid.getCell(i, j));
                }
            }
        }
    }

    public void set_Six_Neighbor_Wraparound(Cell currCell, Grid grid) {
        Tuple position = currCell.getPosition();
        for (int k = -1; k < 2; k++) {
            for (int g = -1; g < 2; g++) {
                if ((k == 0 && g == -1) || (k == -1 && g == 0)) {
                    continue;
                }
                int i = getNeighborIPosition(position.getIPos(), k, grid, true);
                int j = getNeighborJPosition(position.getJPos(), g, grid, true);
                if (i == -1 || j == -1) {
                    continue;
                }
                if (!(i == position.getIPos() && j == position.getJPos())) {
                    currCell.addNeighbor(grid.getCell(i, j));
                }
            }
        }
    }

    public void set_Six_Neighbor_NoWraparound(Cell currCell, Grid grid) {
        Tuple position = currCell.getPosition();
        for (int k = -1; k < 2; k++) {
            for (int g = -1; g < 2; g++) {
                if ((k == 0 && g == -1) || (k == -1 && g == 0)) {
                    continue;
                }
                int i = getNeighborIPosition(position.getIPos(), k, grid, false);
                int j = getNeighborJPosition(position.getJPos(), g, grid, false);
                if (i == -1 || j == -1) {
                    continue;
                }
                if (!(i == position.getIPos() && j == position.getJPos())) {
                    currCell.addNeighbor(grid.getCell(i, j));
                }
            }
        }
    }


    /**
     * @param currCell is the cell which we are calculating a neighborhood for
     * @param grid     is the grid on which the cell is located
     *                 The default fire neighborhood definition is that edge cells
     *                 have no neighbors (since they can never catch on fire) and that
     *                 each cell has one neighbor to the north, south, east, and west.
     */
    public void setDefaultFireNeighborhood(Cell currCell, Grid grid) {
        if (isEdgeCell(currCell, grid)) {
            return;
        } else {
            set_FourNeighbor_NoWraparound(currCell, grid);
        }
    }

    /**
     * @param currCell is the cell which we are calculating a neighborhood for
     * @param grid     is the grid on which the cell is located
     *                 Defines a neighborhood for a cell in which its neighbors are
     *                 to the north, east, south, and west. Edge cells have fewer neighbors.
     */
    public void set_FourNeighbor_NoWraparound(Cell currCell, Grid grid) {
        int i = currCell.getPosition().getIPos();
        int j = currCell.getPosition().getJPos();
        currCell.addNeighbor(grid.getCell(i - 1, j));
        currCell.addNeighbor(grid.getCell(i + 1, j));
        currCell.addNeighbor(grid.getCell(i, j - 1));
        currCell.addNeighbor(grid.getCell(i, j + 1));
    }


    /**
     * @param currCell is the cell which we are calculating a neighborhood for
     * @param grid     is the grid on which the cell is located
     *                 Defines a four member neighborhood but edge cells also have four neighbors
     *                 as the grid "wraps around" and connects to itself.
     */
    public void set_FourNeighbor_Wraparound(Cell currCell, Grid grid) {
        int i = currCell.getPosition().getIPos();
        int j = currCell.getPosition().getJPos();
        currCell.addNeighbor(grid.getCell(getNeighborIPosition(i, -1, grid, true), j));
        currCell.addNeighbor(grid.getCell(getNeighborIPosition(i, 1, grid, true), j));
        currCell.addNeighbor(grid.getCell(i, getNeighborJPosition(j, -1, grid, true)));
        currCell.addNeighbor(grid.getCell(i, getNeighborJPosition(j, 1, grid, true)));
    }

    /**
     * @return the number of neighbors that a cell has
     */
    public int getNeighborhoodSize() {
        return myNeighbors.size();
    }

    /* (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     * returns an iterator view of the neighbors collection
     */
    @Override
    public Iterator<Cell> iterator() {
        List<Cell> neighbors = Collections.unmodifiableList(myNeighbors);
        return neighbors.iterator();
    }

    /**
     * @param j - the j poisition of the current cell for which we are calculating the j position for a given neighbor
     * @param g - an int between -1 and 1 that represents a relative J position to the current cell of a given neighbor
     * @return the position of the current neighbor, with edge cell case accounted for
     */
    protected int getNeighborJPosition(int j, int g, Grid grid, boolean wrapAround) {
        int jPos = j + g;
        if (jPos < 0) {
            if (wrapAround) {
                jPos = grid.getWidth() - 1;
            } else {
                jPos = -1;
            }
        }

        if (jPos > grid.getWidth() - 1) {
            if (wrapAround) {
                jPos = 0;
            } else {
                jPos = -1;
            }
        }
        return jPos;
    }

    /**
     * @param i - the I poisition of the current cell for which we are calculating the I position for a given neighbor
     * @param k - an int between -1 and 1 that represents a relative I position to the current cell of a given neighbor
     * @return the position of the current neighbor, with edge cell case accounted for
     */
    protected int getNeighborIPosition(int i, int k, Grid grid, boolean wrapAround) {
        int iPos = i + k;
        if (iPos < 0) {
            if (wrapAround) {
                iPos = grid.getHeight() - 1;
            } else {
                iPos = -1;
            }
        }

        if (iPos > grid.getHeight() - 1) {
            if (wrapAround) {
                iPos = 0;
            } else {
                iPos = -1;
            }
        }
        return iPos;
    }

    /**
     * @param currCell the cell for which we would like to determine if it is an edge cell or not
     * @return boolean -> true if cell is on the edge of the grid, false if it is not
     */
    protected boolean isEdgeCell(Cell currCell, Grid grid) {
        int i = currCell.getPosition().getIPos();
        int j = currCell.getPosition().getJPos();
        if (i == 0 || j == 0 || i == (grid.getHeight() - 1) || j == (grid.getWidth() - 1)) {
            return true;
        }
        return false;
    }
}
