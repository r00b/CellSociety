package simulations;

public class Grid {
	private final int SIZE;
	private Cell[][] grid;
	
	public Grid(int mySize){
		SIZE = mySize;
		initializeGrid();
	}

	private void initializeGrid() {
		grid = new Cell[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				Cell currCell = new Cell(i,j);
				grid[i][j] = currCell;
			}
		}
		
	}
}
