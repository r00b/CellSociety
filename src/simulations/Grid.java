package simulations;

/**
 * @author samuelcurtis
 *
 *Used to represent a grid of cells. Used by each simulation class to provide a template for the cells interactions.
 */
public class Grid {
	private final int gridWidth;
	private final int gridHeight;
	private Cell[][] grid;
	
	/**
	 * @param width is the number of columns in the grid
	 * @param Height is the number of rows in the grid
	 */
	public Grid(int width, int Height){
		gridWidth = width;
		gridHeight = Height;
		initializeGrid();
	}

	/**
	 * Populate the grid with cells, tell each cell what its position within the grid is upon initialization
	 */
	private void initializeGrid() {
		grid = new Cell[gridHeight][gridWidth];
		for(int i = 0; i < gridHeight; i++){
			for(int j = 0; j < gridWidth; j++){
				Cell currCell = new Cell(i,j);
				grid[i][j] = currCell;
			}
		}
		
	}
	
	/**
	 * @param i - the row number of the cell dsired
	 * @param j - the column number of the cell desired
	 * @return the cell at position (i,j)
	 */
	public Cell getCell(int i, int j){
		return grid[i][j];
	}
	
	/**
	 * @return the width (the number of columns) in the grid
	 */
	public int getWidth(){
		return gridWidth;
	}
	
	/**
	 * @return the height (the number of rows) in the grid
	 */
	public int getHeight(){
		return gridHeight;
	}
}
