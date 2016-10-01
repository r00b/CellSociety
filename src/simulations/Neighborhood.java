package simulations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



public class Neighborhood implements Iterable<Cell> {
	private List<Cell> myNeighbors;
	
	public Neighborhood(){
		myNeighbors = new ArrayList<Cell>();
	}
	
	public void addNeighbor(Cell neighboringCell){
		myNeighbors.add(neighboringCell);
	}
	
	/**
	 * @param currCell is the cell for which we want to calculate its neighbors and store
	 * them. 
	 * 
	 * This method calls two helper methods, getNeighborIPosition and getNeighborJposition,
	 * which assist in the logic of calculating the neighbors of edge cells. 
	 */
	public void set_EightNeighbor_Wraparound_Neighborhood(Cell currCell, Grid grid) {
		Tuple position = currCell.getPosition();
		for(int k = -1; k<2; k++){
			for(int g = -1; g<2; g++){
				int i = getNeighborIPosition(position.getIPos(), k,grid);
				int j = getNeighborJPosition(position.getJPos(), g, grid);
				if (!(i == position.getIPos() && j == position.getJPos())) {
					currCell.addNeighbor(grid.getCell(i, j));
				}
			}
		}
	}
	
	public void setDefaultFireNeighborhood(Cell currCell, Grid grid){
		if(isEdgeCell(currCell,grid)){
			return;
		}
		else{
			int i = currCell.getPosition().getIPos();
			int j = currCell.getPosition().getJPos();
			currCell.addNeighbor(grid.getCell(i-1, j));
			currCell.addNeighbor(grid.getCell(i+1, j));
			currCell.addNeighbor(grid.getCell(i, j-1));
			currCell.addNeighbor(grid.getCell(i, j+1));
		}
	}
	
	/**
	 *
	 * @param j - the j poisition of the current cell for which we are calculating the j position for a given neighbor
	 * @param g - an int between -1 and 1 that represents a relative J position to the current cell of a given neighbor
	 * @return the position of the current neighbor, with edge cell case accounted for
	 */
	protected int getNeighborJPosition(int j, int g, Grid grid) {
		int jPos = j + g;
		if(jPos < 0){
			jPos = grid.getWidth()-1;
		}
		
		if(jPos > grid.getWidth() -1){
			jPos = 0;
		}
		return jPos;
	}

	/**
	 *
	 * @param i - the I poisition of the current cell for which we are calculating the I position for a given neighbor
	 * @param k - an int between -1 and 1 that represents a relative I position to the current cell of a given neighbor
	 * @return the position of the current neighbor, with edge cell case accounted for
	 */
	protected int getNeighborIPosition(int i, int k,Grid grid){
		int iPos = i + k;
		if(iPos < 0){
			iPos = grid.getHeight()-1;
		}
		
		if(iPos > grid.getHeight()-1){
			iPos = 0;
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
		if(i == 0 || j == 0 || i == (grid.getHeight() - 1) || j == (grid.getWidth() - 1)){
			return true;
		}
		return false;
	}
	
	public int getNeighborhoodSize(){
		return myNeighbors.size();
	}
	
	@Override
	public Iterator<Cell> iterator() {
		List<Cell> neighbors = Collections.unmodifiableList(myNeighbors);
		return neighbors.iterator();
	}
	
}
