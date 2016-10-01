package simulations.GameOfLife;

import simulations.Cell;
import simulations.Grid;

public class GameOfLifeGrid extends Grid {
	private String xmlFile;
	
	public GameOfLifeGrid(int width, int Height, String xmlfile) {
		super(width, Height);
		xmlFile = xmlfile;
	}
	
	@Override
	public Cell getNewCell(int i, int j){
		return new GameOfLifeCell(i, j,xmlFile);
	}
	

}
