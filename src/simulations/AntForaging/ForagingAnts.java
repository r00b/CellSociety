package simulations.AntForaging;

import java.util.Random;

import simulations.Cell;
import simulations.Simulation;
import simulations.Tuple;
import xml.ForagingAntsXMLParser;

public class ForagingAnts extends Simulation{
	private ForagingAntsXMLParser myParser;
	private ForagingAntGrid myGrid;
	/*private Tuple nestLocation;
	private Tuple foodSourceLocation;
	private int percentOfObstacles;*/
	private String XMLFileName;
	
	public ForagingAnts(String XMLFileName){
		myParser = new ForagingAntsXMLParser(XMLFileName);
		myGrid = new ForagingAntGrid(myParser.getGridHeight(), myParser.getGridWidth(), XMLFileName);
		this.XMLFileName = XMLFileName;
		//setInitialParameters();
		setGrid(myGrid);
		setInitialGridState();
	}
	
	/*private void setInitialParameters() {
		percentOfObstacles = myParser.getPercentObstacles();
		int nestILocation = myParser.getNestILocation();
		int nestJLocation = myParser.getNestJLocation();
		nestLocation = new Tuple(nestILocation, nestJLocation);
		int foodSourceILocation = myParser.getSourceILocation();
		int foodSourceJLocation = myParser.getSourceJLocation();
		foodSourceLocation = new Tuple(foodSourceILocation, foodSourceJLocation);
	}
*/
	@Override
	protected void setInitialGridState() {
		for (int row = 0; row < myGrid.getHeight(); row++){
			for (int col = 0; col < myGrid.getWidth(); col++){
				ForagingAntCell currCell = (ForagingAntCell) myGrid.getCell(row, col);
				currCell.setNeighborhood(myGrid);
			}
		}
	}
	
	@Override
	protected void updateNextStates() {
		for (int row = 0; row < myGrid.getHeight(); row++){
			for (int col = 0; col < myGrid.getWidth(); col++){
				ForagingAntCell currCell = (ForagingAntCell) myGrid.getCell(row, col);
				if(currCell.getCurrState() == Obstacle.OBSTACLE){
					continue;
				}
				else if(currCell.getCurrState() == FoodSource.FOOD){
					currCell.forageAnts();;
				}
				else if(currCell.isEmpty()){
					continue;
				}
				else if(currCell.getCurrState() == Nest.NEST){
					Nest nest = (Nest) currCell;
					nest.birthAnts();
					nest.forageAnts();
				}
				else if(currCell.isFull() || currCell.hasAnts()){
					currCell.forageAnts();
				}
				currCell.resetsAnt();
				currCell.diffuse();
				currCell.evaporate();
				currCell.updateProbability();
				
			}
		}
		
	}
	
	@Override
	public void updateGrid() {
		updateNextStates();
	}
}
