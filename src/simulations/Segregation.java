package simulations;

import java.util.HashMap;
import java.util.Random;

import javafx.scene.paint.Color;
import xml.SegregationXMLParser;

public class Segregation extends Simulation{
	private static final int EMPTY = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	
	private Grid myGrid;
	private SegregationXMLParser myParser;
	private HashMap<Integer, Color> stateToColorMap;
	private int satisfactionThreshold;
	private int percentAgentOne;
	private int percentAgentTwo;
	private int percentEmpty;
	
	public Segregation() {
		myParser = new SegregationXMLParser("data/Segregation.xml");
		satisfactionThreshold = myParser.getSatisfactionThreshold();
		percentAgentOne = myParser.getPercentOfAgentOne();
		percentAgentTwo = myParser.getPercentOfAgentTwo();
		percentEmpty = myParser.getPercentEmpty();
		myGrid = new Grid(myParser.getGridWidth(), myParser.getGridHeight());
		stateToColorMap = new HashMap<Integer, Color>();
		mapStatesToColors();
		setInitialGridState();
		
		
	}
	

	protected void mapStatesToColors() {
		stateToColorMap.put(EMPTY, Color.WHITE);
		stateToColorMap.put(ONE, Color.BLUE);
		stateToColorMap.put(TWO, Color.RED);
	}

	@Override
	protected void setInitialGridState() {
		for (int row = 0; row < myGrid.getHeight(); row++) {
			for (int col = 0; col < myGrid.getWidth(); col++) {
				Cell currCell = myGrid.getCell(row, col);
				setRandomInitialState(currCell);
				addNeighbors(currCell);
			}
		}
	}
	
	private void setRandomInitialState(Cell currCell) {
		Random randomNumGenerator = new Random();
		int randNum = randomNumGenerator.nextInt(101);
		if (randNum < percentAgentOne) {
			currCell.setCurrState(ONE, stateToColorMap.get(ONE));
		}
		else if (randNum >= percentAgentOne && randNum < percentAgentOne + percentAgentTwo) {
			currCell.setCurrState(TWO, stateToColorMap.get(TWO));
		}
		else {
			currCell.setCurrState(EMPTY, stateToColorMap.get(EMPTY));
		}
	}

	@Override
	protected void updateNextStates() {
		for (int row = 0; row < myGrid.getHeight(); row++) {
			for (int col = 0; col < myGrid.getWidth(); col++) {
				Cell currCell = myGrid.getCell(row, col);
				int numNeighborsAlike = 0;
				
			}
		}
		
	}
	
	private int calculateNumNeighborsAlike(Cell curCell) {
		int numAlike = 0;
		for (Cell neighbor : curCell.getNeighbors()) {
			if (neighbor.getCurrState().eq)
		}
	}

	@Override
	protected void commitStates() {
		for(int row = 0; row < myGrid.getHeight(); row++){
			for(int col = 0; col <myGrid.getWidth(); col++){
				Cell currCell = myGrid.getCell(row, col);
				currCell.commitState(stateToColorMap.get(currCell.getNextState()));
			}
		}
		
	}

	@Override
	protected void addNeighbors(Cell currCell) {
		Tuple position = currCell.getPosition();
		for(int k = -1; k<2; k++){
			for(int g = -1; g<2; g++){
				int i = getNeighborIPosition(position.getIPos(), k);
				int j = getNeighborJPosition(position.getJPos(), g);
				if (!(i == position.getIPos() && j == position.getJPos())) {
					currCell.addNeighbor(myGrid.getCell(i, j));
				}
			}
		}
	}
	private int getNeighborJPosition(int j, int g) {
		int jPos = j + g;
		if(jPos < 0){
			jPos = myGrid.getHeight()-1;
		}
		
		if(jPos > myGrid.getHeight()-1){
			jPos = 0;
		}
		return jPos;
	}
	
	private int getNeighborIPosition(int i, int k){
		int iPos = i + k;
		if(iPos < 0){
			iPos = myGrid.getWidth()-1;
		}
		
		if(iPos > myGrid.getWidth()-1){
			iPos = 0;
		}
		return iPos;
	}

	@Override
	public Grid getGrid() {
		return myGrid;
	}

	@Override
	public int getGridWidth() {
		return myGrid.getWidth();
	}

	@Override
	public int getGridHeight() {
		return myGrid.getHeight();
	}

}
