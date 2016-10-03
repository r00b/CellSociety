package simulations.AntForaging;

import java.util.HashMap;
import java.util.Random;

import simulations.Simulation;
import simulations.Tuple;
import xml.ForagingAntsXMLParser;

public class ForagingAnts extends Simulation{
	private ForagingAntsXMLParser myParser;
	private ForagingAntGrid myGrid;
	private Tuple nestLocation;
	private Tuple foodSourceLocation;
	private int percentOfObstacles;
	private String XMLFileName;
	
	public ForagingAnts(String XMLFileName){
		super(XMLFileName);
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
				else if(isFoodSourceLocation(row, col)){
					currCell = new FoodSource(row, col, XMLFileName);
				}
				else if(isObstacle()){
					currCell = new Obstacle(row, col, XMLFileName);
				}
				else{
					currCell.setEmpty();
				}
			}
		}
		
	}
	
	private boolean isObstacle() {
		Random rand = new Random();
		int randNum = rand.nextInt(101);
		return randNum <= percentOfObstacles;
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

	@Override
	protected void setStateMap() {
		myStateMap.put(ForagingAntCell.ANTS, "Ants");
		myStateMap.put(ForagingAntCell.EMPTY, "Empty");
		myStateMap.put(ForagingAntCell.FULL, "Full");
		myStateMap.put(ForagingAntCell.OBSTACLE, "Obstacle");
	}

}
