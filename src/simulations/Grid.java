package simulations;

/**
 * @author samuelcurtis
 *         <p>
 *         Used to represent a grid of cells. Used by each simulation class to provide a template for the cells interactions.
 */
public abstract class Grid {
    private final int gridWidth;
    private final int gridHeight;
    private Cell[][] grid;
    private String xmlFile;

    /**
     * @param width  is the number of columns in the grid
     * @param Height is the number of rows in the grid
     */
    public Grid(int width, int Height, String xmlFile) {
        gridWidth = width;
        gridHeight = Height;
        this.xmlFile = xmlFile;
        initializeGrid();
    }

    /**
     * @param i       is the row number of the location we want to populate with a new cell
     * @param j       is the column number of the location we want to populate with a new cell
     * @param xmlFile is the name of the xmlfile being used to set the parameters of a given
     *                simulation
     * @return a new cell located at location i,j
     */
    protected abstract Cell getNewCell(int i, int j, String xmlFile);

    /**
     * @param i is the row number of the cell desired
     * @param j is the column number of the cell desired
     * @return the cell at position (i,j) in the grid
     */
    public Cell getCell(int i, int j) {
        return grid[i][j];
    }

    /**
     * @return the width (the number of columns) in the grid
     */
    public int getWidth() {
        return gridWidth;
    }

    /**
     * @return the height (the number of rows) in the grid
     */
    public int getHeight() {
        return gridHeight;
    }

    /**
     * Populate the grid with cells, tell each cell what its position within the grid is upon initialization
     */
    private void initializeGrid() {
        grid = new Cell[gridHeight][gridWidth];
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                Cell currCell = getNewCell(i, j, xmlFile);
                currCell.mapStatesToColors();
                grid[i][j] = currCell;
            }
        }
    }
}
