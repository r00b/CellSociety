package simulations.AntForaging;

import simulations.Simulation;
import simulations.Tuple;
import xml.ForagingAntsXMLParser;

public class ForagingAnts extends Simulation{
	private ForagingAntsXMLParser myParser;
	private ForagingAntGrid myGrid;
	private int percentOfObstacles;
	private Tuple nestLocation;
	private Tuple foodSourceLocation;
	private String XMLFileName;
	
	public ForagingAnts(String XMLFileName){
		myParser = new ForagingAntsXMLParser(XMLFileName);
		myGrid = new ForagingAntGrid(myParser.getGridHeight(), myParser.getGridWidth(), XMLFileName);
		this.XMLFileName = XMLFileName;
		setInitialParameters();
		setGrid(myGrid);
		setInitialGridState();
	}
	
	private void setInitialParameters() {
		percentOfObstacles = myParser.getPercentObstacles();
		int nestILocation = myParser.getNestILocation();
		int nestJLocation = myParser.getNestJLocation();
		nestLocation = new Tuple(nestILocation, nestJLocation);
		int foodSourceILocation = myParser.getSourceILocation();
		int foodSourceJLocation = myParser.getSourceJLocation();
		foodSourceLocation = new Tuple(foodSourceILocation, foodSourceJLocation);
	}

	@Override
	protected void setInitialGridState() {
		for (int row = 0; row < myGrid.getHeight(); row++){
			for (int col = 0; col < myGrid.getWidth(); col++){
				ForagingAntCell currCell = (ForagingAntCell) myGrid.getCell(row, col);
				if(isNestLocation(row,col)){
					currCell = new Nest(row, col, XMLFileName);
				}
				if(isFoodSourceLocation(row, col)){
					currCell.setFoodSource
				}
			
			}
		}
		
	}
	
	private boolean isFoodSourceLocation(int row, int col){
		return (foodSourceLocation.getIPos() == row) && (foodSourceLocation.getJPos() == col);
	}

	private boolean isNestLocation(int row, int col) {
		return (nestLocation.getIPos() == row) && (nestLocation.getJPos() == col);
	}

	@Override
	protected void updateNextStates() {
		// TODO Auto-generated method stub
		
	}

}
