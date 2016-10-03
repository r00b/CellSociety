package simulations.Wator;
import java.util.HashMap;
import java.util.Iterator;
import simulations.Cell;
import simulations.Simulation;
import xml.WatorXMLParser;
/**
 * The Wator class handles the logic of modeling the Wa-Tor World simulation
 * This class depends on the Shark and Fish classes to model behavior of the cells
 * They also depend on WatorXMLParser objects to provide the specific information given in wator xml files
 * Wator objects are instantiated by Animation.java, which uses these them to visualize the CA simulation
 * 
 * @author Aaron Chang
 *
 */
public class Wator extends Simulation{
	private WatorXMLParser myParser;
	private HashMap<Cell, Fish> myFish = new HashMap<Cell, Fish>();
	private HashMap<Cell, Shark> mySharks = new HashMap<Cell, Shark>();
	private HashMap<Cell, Fish> tempFishMap = new HashMap<Cell, Fish>();
	private HashMap<Cell, Shark> tempSharkMap = new HashMap<Cell, Shark>();
	private final int myFishBreedTime;
	private final int mySharkBreedTime;
	private final int mySharkStarveTime;
	private final WatorGrid myGrid;
	
	public Wator(String XMLFileName) {
		super(XMLFileName);
		myParser = new WatorXMLParser(XMLFileName);
		myFishBreedTime = myParser.getFishBreedTime();
		mySharkBreedTime = myParser.getSharkBreedTime();
		mySharkStarveTime = myParser.getSharkStarveTime();
		myGrid = new WatorGrid(myParser.getGridWidth(), myParser.getGridHeight(),XMLFileName);
		setGrid(myGrid);
		setInitialGridState();
	}
	
	@Override
	protected void setInitialGridState() {
		for (int row = 0; row < myGrid.getHeight(); row++) {
			for (int col = 0; col < myGrid.getWidth(); col++) {
				WatorCell currCell = (WatorCell) myGrid.getCell(row, col);
				currCell.setRandomInitialState();
				updateMap(currCell);
				currCell.setNeighborhood(myGrid);
			}
		}
	}
	
	private void updateMap(WatorCell currCell) {
		if(currCell.isShark()){
			mySharks.put(currCell, new Shark(mySharkBreedTime, mySharkStarveTime));
		}
		else if(currCell.isFish()){
			myFish.put(currCell, new Fish(myFishBreedTime));
		}
	}
	
	//commits states of each cell and also commits the new values to mySharks and myFish
	//tempSharkMap and tempFishMap are cleared so they can be used in the next 'round'
	@Override
	protected void commitStates(){
		super.commitStates();
		mySharks = new HashMap<Cell, Shark>(tempSharkMap);
		myFish = new HashMap<Cell, Fish>(tempFishMap);
		tempSharkMap.clear();
		tempFishMap.clear();
	}
	
	@Override
	protected void updateNextStates() {
		for (int row = 0; row < myGrid.getHeight(); row++) {
			for (int col = 0; col < myGrid.getWidth(); col++) {
				WatorCell currCell = (WatorCell) myGrid.getCell(row, col);
				if (currCell.isFish()) {
					handleFish(currCell);
				}
				else if (currCell.isShark()) {
					handleShark(currCell);
				}
			}
		}
	}
	
	/**
	 * this method handles the logic for cells with the state FISH
	 * it is called by updateNextStates when updating the grid
	 * @param currCell - the current cell that contains a Fish object
	 */
	private void handleFish(WatorCell currCell) {
		//if fish was already eaten this round, do nothing
		if (!myFish.containsKey(currCell)) return;
		//check if neighbors are sharks, and handle scenario if they are
		boolean hasBeenEaten = false;
		//ArrayList<Cell> neighbors = makeNeighborList(currCell);
		for (Iterator i =  currCell.getNeighbors(); i.hasNext();) {
			WatorCell neighbor = (WatorCell) i.next();
			if (neighbor.isShark() && !tempSharkMap.containsKey(neighbor)) { //if shark hasn't been handled yet
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
			WatorCell neighbor = currCell.getRandomNeighborByState(WatorCell.EMPTY);
			if (neighbor != null) { //if there is an empty adjacent neighbor
				if (currFish.canBreed()) { //breed fish
					breedAnimal(currFish, currCell, neighbor);
				}
				else { 
					moveAnimal(currFish, currCell, neighbor);
				}
			}
			else { //no empty adjacent cells, keep cell in place
				currCell.setNextStateFish();
				tempFishMap.put(currCell, currFish);
			}
		}
	}
	/**
	 * this method handles the logic for cells with the state SHARK
	 * it is called by updateNextStates when updating the grid
	 * @param currCell - the current cell that contains a Shark object
	 */
	private void handleShark(WatorCell currCell) {
		Shark currShark = mySharks.get(currCell);
		currShark.decrementBreedTime();
		if (!tempSharkMap.containsKey(currCell)) { //shark hasn't been handled already this round
			boolean hasEaten = false;
			for (Iterator i = currCell.getNeighbors();  i.hasNext();) {
				WatorCell neighbor = (WatorCell) i.next();
				if (myFish.containsKey(neighbor)) { //found a fish, so shark eats fish
					sharkEatsFish(currShark, currCell, neighbor);
					hasEaten = true;
					break;
				}
			}
			if (!hasEaten) {
				currShark.decrementTimeUntilStarve();
				if (currShark.willStarve()) { //if shark dies
					currCell.setNextStateEmpty();
					return;
				}
				else {
					WatorCell neighbor = currCell.getRandomNeighborByState(WatorCell.EMPTY);
					if (neighbor != null) { //move shark
						moveAnimal(currShark, currCell, neighbor);
						return;
					}
				}
			}
		}
		if (currShark.canBreed()) { 
			WatorCell neighbor = currCell.getRandomNeighborByState(WatorCell.EMPTY);
			if (neighbor != null) { //found empty adjacent neighbor, so shark can breed
				breedAnimal(currShark, currCell, neighbor);
			}
		}
		currCell.setNextStateShark();
		tempSharkMap.put(currCell, currShark);
	}
	/**
	 * helper method used by handleShark and handleFish to handle logic for when an animal breeds
	 * the method signature must be general so both fish and shark can be bred, but specific enough to breed the right animal
	 * @param currAnimal - Animal that is breeding
	 * @param currCell - Cell that that the breeding animal is currently on
	 * @param neighbor - Cell on which the new animal wil appear
	 */
	private void breedAnimal(Animal currAnimal, WatorCell currCell, WatorCell neighbor) {
		currAnimal.resetTimeToBreed();
		int currAnimalType = currAnimal.getType();
		if (currAnimalType == WatorCell.SHARK) { //animal is a shark
			tempSharkMap.put(currCell, (Shark) currAnimal);
			tempSharkMap.put(neighbor, new Shark(mySharkBreedTime, mySharkStarveTime));
			currCell.setNextStateShark();
			neighbor.setNextStateShark();
		}
		else {
			tempFishMap.put(currCell, (Fish) currAnimal);
			tempFishMap.put(neighbor, new Fish(myFishBreedTime));
			currCell.setNextStateFish();
			neighbor.setNextStateFish();
		}
	}
	/**
	 * helper method that is used by handleShark and handleFish to move the animal to a new Cell
	 * @param currAnimal - animal that is moving
	 * @param currCell - cell that the animal is currently on
	 * @param nextCell - cell that the animal will move to
	 */
	private void moveAnimal(Animal currAnimal, WatorCell currCell, WatorCell nextCell) {
		if (currAnimal.getType() == WatorCell.SHARK) { //animal is a shark
			tempSharkMap.put(nextCell, (Shark) currAnimal);
			nextCell.setNextStateShark();
		}
		else {
			tempFishMap.put(nextCell, (Fish) currAnimal);
			nextCell.setNextStateFish();
		}
		currCell.setNextStateEmpty();
		
	}
	
	/**
	 * helper method that handles the event that a shark eats a fish
	 * This is called by handleFish (if a fish's neighbor is a shark) or handleShark (if a shark's neighbor is a fish)
	 * @param currShark - Shark that is eating
	 * @param sharkCell - Cell of shark that is eating
	 * @param fishCell - Cell of fish that is being eaten
	 */
	private void sharkEatsFish(Shark currShark, WatorCell sharkCell, WatorCell fishCell) {
		myFish.remove(fishCell);
		fishCell.setNextStateEmpty();
		sharkCell.setNextStateShark();
		currShark.markAsFull();
		tempSharkMap.put(sharkCell, currShark);
	}

	@Override
	protected void setStateMap() {
		myStateMap.put(WatorCell.EMPTY, "Empty");
		myStateMap.put(WatorCell.FISH, "Prey");
		myStateMap.put(WatorCell.SHARK, "Predator");
		
	}
}