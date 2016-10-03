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
				/*ForagingAntCell currCell = (ForagingAntCell) myGrid.getCell(row, col);
				ForagingAntCell temp = new ForagingAntCell(row, col, XMLFileName);
				if(isNestLocation(row,col)){
					System.out.println("NEST");
					 temp = new Nest(row, col, XMLFileName);
				}
				else if(isFoodSourceLocation(row, col)){
					System.out.println("FOODSOURCE");
					 temp = new FoodSource(row, col, XMLFileName);
				}
				else if(isObstacle()){
					System.out.println("OBSTACLE");
					 temp = new Obstacle(row, col, XMLFileName);
				}
				else{
					System.out.println("EMPTY");
					currCell.setEmpty();
				}
				currCell = temp;
				System.out.println(currCell.getCurrState());*/
			}
		}
	}
	@Override
	protected void updateNextStates() {
		// TODO Auto-generated method stub
		
	}

}
