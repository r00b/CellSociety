package simulations.AntForaging;

import java.util.List;
import java.util.Random;

import com.sun.javafx.scene.paint.GradientUtils.Parser;

import javafx.scene.paint.Color;
import simulations.Cell;
import simulations.Grid;
import xml.ForagingAntsXMLParser;
import xml.XMLParser;

public class ForagingAntCell extends Cell {
	private static final int EMPTY = 0;
	private static final int OBSTACLE = 1;
	private static final int ANTS = 2;
	private static final int FULL = 3;
	private int foodPheromones;
	private int homePheromones;
	private static int maxAntsPerCell;
	private int percentOfObstacles;
	private List<Ant> myAnts;
	
	private final ForagingAntsXMLParser myParser;
	
	public ForagingAntCell(int i, int j, String xmlFilename) {
		super(i, j);
		myParser = new ForagingAntsXMLParser(xmlFilename);
		maxAntsPerCell = myParser.getMaxAntsPerLocation();
	}

	@Override
	public void mapStatesToColors() {
		updateColorMap(EMPTY, Color.GRAY);
		updateColorMap(OBSTACLE, myParser.getObstacleColor());
		
	}

	@Override
	public void setRandomInitialState() {
		Random rand = new Random();
		int randNum = rand.nextInt(101);
		if(randNum < percentOfObstacles){
			setCurrState(OBSTACLE, findStateColor(OBSTACLE));
		}
		
	}

	@Override
	public void setNeighborhood(Grid grid) {
		
	}

	public void setEmpty() {
		setCurrState(EMPTY, Color.GRAY);
	}
	
}
