package simulations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.paint.Color;
import xml.WatorXMLParser;
/**
 * The Wator class handles the logic of modeling the Wa-Tor World simulation
 * This class depends on the Shark and Fish classes to model behavior of the cells
 * They also depend on WatorXMLParser objects to provide the specific information given in wator xml files
 * Wator objects are instantiated by Animation.java, which uses these them to visualize the CA simulation
 * 
 * NOTE: this class needs a lot of refactoring
 * I will continue to refactor the code and fix the logic of this simulation
 * @author Aaron Chang
 *
 */
public class Wator extends Simulation{
	private static final int EMPTY = 0;
	private static final int SHARK = 1;
	private static final int FISH = 2;
	
	private WatorXMLParser myParser;
	private HashMap<Cell, Fish> myFish = new HashMap<Cell, Fish>();
	private HashMap<Cell, Shark> mySharks = new HashMap<Cell, Shark>();
	private HashMap<Cell, Fish> tempFishMap = new HashMap<Cell, Fish>();
	private HashMap<Cell, Shark> tempSharkMap = new HashMap<Cell, Shark>();
	private final int myFishBreedTime;
	private final int mySharkBreedTime;
	private final int mySharkStarveTime;
	private final int myPercentShark;
	private final int myPercentFish;
	
	public Wator() {
		myParser = new WatorXMLParser("data/Wator.xml");
		myFishBreedTime = myParser.getFishBreedTime();
		mySharkBreedTime = myParser.getSharkBreedTime();
		mySharkStarveTime = myParser.getSharkStarveTime();
		myPercentShark = myParser.getPercentShark();
		myPercentFish = myParser.getPercentFish();
		myGrid = new Grid(myParser.getGridWidth(), myParser.getGridHeight());
		stateToColorMap = new HashMap<Integer, Color>();
		mapStatesToColors();
		setInitialGridState();
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
	/**
	 * sets a random initial state for each cell
	 * The initial state is determined by the probability assigned to each state
	 * These probabilities are provided by the xml files
	 * @param currCell
	 */
	private void setRandomInitialState(Cell currCell) {
		Random randomNumGenerator = new Random();
		int randNum = randomNumGenerator.nextInt(101);
		if (randNum < myPercentShark) {
			currCell.setCurrState(SHARK, stateToColorMap.get(SHARK));
			mySharks.put(currCell, new Shark(mySharkBreedTime, mySharkStarveTime));
		}
		else if (randNum >= myPercentShark && randNum < myPercentShark + myPercentFish) {
			currCell.setCurrState(FISH, stateToColorMap.get(FISH));
			myFish.put(currCell, new Fish(myFishBreedTime));
		}
		else {
			currCell.setCurrState(EMPTY, stateToColorMap.get(EMPTY));
		}
	}
	@Override
	protected void addNeighbors(Cell currCell) {
		for (Cell neighbor : makeNeighborList(currCell)) {
			currCell.addNeighbor(neighbor);
		}
	}
	
	//returns a list of the four neighbors of each cell 
	private ArrayList<Cell> makeNeighborList(Cell currCell) {
		int row = currCell.getPosition().getIPos();
		int col = currCell.getPosition().getJPos();
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(myGrid.getCell(getNeighborIPosition(row, -1), col));
		neighbors.add(myGrid.getCell(getNeighborIPosition(row, 1), col));
		neighbors.add(myGrid.getCell(row, getNeighborJPosition(col, -1)));
		neighbors.add(myGrid.getCell(row, getNeighborJPosition(col, 1)));
		return neighbors;
	}
	
	//commits states of each cell and also commits the new values to mySharks and myFish
	//tempSharkMap and tempFishMap are cleared so they can be used in the next 'round'
	@Override
	protected void commitStates(){
		for(int i = 0; i < myGrid.getHeight(); i++){
			for(int j = 0; j < myGrid.getWidth(); j++){
				Cell currCell = myGrid.getCell(i, j);
				currCell.commitState(stateToColorMap.get(currCell.getNextState()));
			}
		}
		mySharks = new HashMap<Cell, Shark>(tempSharkMap);
		myFish = new HashMap<Cell, Fish>(tempFishMap);
		tempSharkMap.clear();
		tempFishMap.clear();
	}
	@Override
	protected void updateNextStates() {
		for (int row = 0; row < myGrid.getHeight(); row++) {
			for (int col = 0; col < myGrid.getWidth(); col++) {
				Cell currCell = myGrid.getCell(row, col);
				if (currCell.getCurrState() == FISH) {
					handleFish(currCell);
				}
				else if (currCell.getCurrState() == SHARK) {
					handleShark(currCell);
				}
			}
		}
	}
	//logic when a fish is encountered
	private void handleFish(Cell currCell) {
		//if fish was already eaten this round, do nothing
		if (!myFish.containsKey(currCell)) return;
		//check if neighbors are sharks, and handle scenario if they are
		boolean hasBeenEaten = false;
		ArrayList<Cell> neighbors = makeNeighborList(currCell);
		for (Cell neighbor : neighbors) {
			if (neighbor.getCurrState() == SHARK && !tempSharkMap.containsKey(neighbor)) { //if shark hasn't been handled yet
				//shark eats fish
				Shark currShark = mySharks.get(neighbor);
				sharkEatsFish(currShark, neighbor, currCell);
				hasBeenEaten = true;
				break;
			}
		}
		if (!hasBeenEaten) {
			Fish currFish = myFish.get(currCell);
			currFish.decrementBreedTime();
			Cell neighbor = getRandomNeighborByState(neighbors, EMPTY);
			if (neighbor != null) { //if there is an empty adjacent neighbor
				if (currFish.getTimeLeftToBreed() == 0) { //breed fish
					currFish.resetTimeToBreed();
					currCell.setNextState(FISH);
					neighbor.setNextState(FISH);
					tempFishMap.put(currCell, currFish);
					tempFishMap.put(neighbor, new Fish(myFishBreedTime));
				}
				else if (currFish.getTimeLeftToBreed() > 0) { //move fish
					neighbor.setNextState(FISH);
					currCell.setNextState(EMPTY);
					tempFishMap.put(neighbor, currFish);
				}
			}
			else { //no empty adjacent cells, keep cell in place
				currCell.setNextState(FISH);
				tempFishMap.put(currCell, currFish);
			}
		}
	}
	
	//logic for when a shark is encountered
	private void handleShark(Cell currCell) {
		Shark currShark = mySharks.get(currCell);
		ArrayList<Cell> neighbors = makeNeighborList(currCell);
		if (!tempSharkMap.containsKey(currCell)) { //shark hasn't been handled already this round
			boolean hasEaten = false;
			for (Cell neighbor : neighbors) {
				if (myFish.containsKey(neighbor)) { //found a fish, so shark eats fish
					sharkEatsFish(currShark, currCell, neighbor);
					hasEaten = true;
					break;
				}
			}
			if (!hasEaten) {
				currShark.decrementTimeUntilStarve();
				if (currShark.getTimeUntilStarve() == 0) { //if shark dies
					currCell.setNextState(EMPTY);
					return;
				}
				else {
					Cell neighbor = getRandomNeighborByState(neighbors, EMPTY);
					if (neighbor != null) { //move shark
						neighbor.setNextState(SHARK);
						currCell.setNextState(EMPTY);
						currShark.decrementBreedTime();
						tempSharkMap.put(neighbor, currShark);
						return;
					}
				}
			}
		}
		//breed
		currShark.decrementBreedTime();
		if (currShark.getTimeLeftToBreed() == 0) {
			Cell neighbor = getRandomNeighborByState(neighbors, EMPTY);
			if (neighbor != null) {
				currShark.resetTimeToBreed();
				neighbor.setNextState(SHARK);
				currCell.setNextState(SHARK);
				tempSharkMap.put(currCell, currShark);
				tempSharkMap.put(neighbor, new Shark(mySharkBreedTime, mySharkStarveTime));
			}
		}
		currCell.setNextState(SHARK);
		tempSharkMap.put(currCell, currShark);
		
	}
	
	/**
	 * helper method
	 * @param currShark
	 * @param sharkCell
	 * @param fishCell
	 */
	private void sharkEatsFish(Shark currShark, Cell sharkCell, Cell fishCell) {
		myFish.remove(fishCell);
		fishCell.setNextState(EMPTY);
		sharkCell.setNextState(SHARK);
		currShark.markAsFull();
		tempSharkMap.put(sharkCell, currShark);
	}
	/**
	 * this method is a helper method for the handleShark and handleFish methods
	 * It takes a cell's list of neighbors, filters it by a specified state, and returns a random neighbor of that state
	 * if there are no neighbors of that state, it returns null
	 */
	public Cell getRandomNeighborByState(ArrayList<Cell> neighbors, int state) {
		ArrayList<Cell> filteredNeighbors = new ArrayList<Cell>();
		for (Cell neighbor : neighbors) {
			if (neighbor.getCurrState() == state) {
				filteredNeighbors.add(neighbor);
			}
		}
		if (filteredNeighbors.size() > 0) {
			Random randGenerator = new Random();
			int randIndex = randGenerator.nextInt(filteredNeighbors.size());
			return filteredNeighbors.get(randIndex);
		}
		else {
			return null;
		}
	}
	
	
		
	
	@Override
	protected void mapStatesToColors() {
		stateToColorMap.put(EMPTY, Color.AQUAMARINE);
		stateToColorMap.put(SHARK, Color.RED);
		stateToColorMap.put(FISH, Color.GREEN);	
	}
	
	
}