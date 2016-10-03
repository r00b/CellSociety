package simulations.AntForaging;

import java.util.Random;

import org.omg.CORBA.INITIALIZE;

import simulations.Cell;
import simulations.Grid;
import simulations.Tuple;
import xml.ForagingAntsXMLParser;

public class ForagingAntGrid extends Grid {
	private Tuple nestLocation;
	private Tuple foodSourceLocation;
	private int percentOfObstacles;
	private ForagingAntsXMLParser myParser;
	
	
	public ForagingAntGrid(int width, int Height, String xmlFileName) {
		super(width, Height, xmlFileName);
	}

	@Override
	protected Cell getNewCell(int i, int j, String xmlFileName) {
		initializeParameters(xmlFileName);
		return getCell(i, j, xmlFileName);
	}
	
	private void initializeParameters(String xmlFileName) {
		myParser = new ForagingAntsXMLParser(xmlFileName);
		percentOfObstacles = myParser.getPercentObstacles();
		int nestILocation = myParser.getNestILocation();
		int nestJLocation = myParser.getNestJLocation();
		nestLocation = new Tuple(nestILocation, nestJLocation);
		int foodSourceILocation = myParser.getSourceILocation();
		int foodSourceJLocation = myParser.getSourceJLocation();
		foodSourceLocation = new Tuple(foodSourceILocation, foodSourceJLocation);
	}

	private ForagingAntCell getCell(int row, int col, String XMLFileName){
		if(isNestLocation(row,col)){
			return new Nest(row, col, XMLFileName);
		}
		else if(isFoodSourceLocation(row, col)){
			return new FoodSource(row, col, XMLFileName);
		}
		else if(isObstacle()){
			return new Obstacle(row, col, XMLFileName);
		}
		else{
			ForagingAntCell cell = new ForagingAntCell(row, col, XMLFileName);
			cell.setEmpty();
			return cell;
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

}
