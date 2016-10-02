package simulations.AntForaging;

import java.util.List;

import com.sun.javafx.scene.paint.GradientUtils.Parser;

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
	private List<Ant> myAnts;
	
	private final ForagingAntsXMLParser myParser;
	
	public ForagingAntCell(int i, int j, String xmlFilename) {
		super(i, j);
		myParser = new ForagingAntsXMLParser(xmlFilename);
		maxAntsPerCell = myParser.getMaxAntsPerLocation();
	}

	@Override
	public void mapStatesToColors() {
		
		
	}

	@Override
	public void setRandomInitialState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNeighborhood(Grid grid) {
		
	}
	
}
