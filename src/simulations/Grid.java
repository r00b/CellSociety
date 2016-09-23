package simulations;

public class Grid {
	private final int gridWidth;
	private final int gridHeight;
	private Cell[][] grid;
	
	public Grid(int width, int Height){
		gridWidth = width;
		gridHeight = Height;
		initializeGrid();
	}

	private void initializeGrid() {
		grid = new Cell[gridHeight][gridWidth];
		for(int i = 0; i < gridHeight; i++){
			for(int j = 0; j < gridWidth; j++){
				Cell currCell = new Cell(i,j);
				grid[i][j] = currCell;
			}
		}
		
	}
	
	public Cell getCell(int i, int j){
		return grid[i][j];
	}
	
	public int getWidth(){
		return gridWidth;
	}
	
	public int getHeight(){
		return gridHeight;
	}
}
