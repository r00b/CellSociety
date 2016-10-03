package simulations.AntForaging;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import simulations.Tuple;

public class Ant {
	private boolean hasFoodItem;
	private boolean movedThisTurn;
	private Orientation myOrientation;
	private int lifetime;
	private ForagingAntCell currentLocation;
	private ForagingAntGrid myGrid;
	
	public Ant(ForagingAntCell location, int life){
		hasFoodItem = false;
		currentLocation = location;
		movedThisTurn = false;
		lifetime = life;
		myOrientation = new Orientation();
		ForagingAntCell desiredLocation = currentLocation.getNeighborWithMaxFoodPheremones();
		myOrientation.setOrientation(currentLocation, desiredLocation);
	}
	
	public Ant(Nest nest, int life) {
		this((ForagingAntCell) nest, life);
	}

	public void forage(){
		if(movedThisTurn == false){
			if(hasFoodItem){
				returnToNest();
			}
			else{
				findFoodSource();
			}
			movedThisTurn = true;
		}
		
	}

	private void findFoodSource() {
		if(currentLocation.getCurrState() == Nest.NEST){
			ForagingAntCell desiredLocation = currentLocation.getNeighborWithMaxFoodPheremones();
			myOrientation.setOrientation(currentLocation, desiredLocation);
		}
		ForagingAntCell nextSpot = selectLocation(getForwardLocations());
		if(nextSpot == null){
			nextSpot = selectLocation(getNonForwardLocations());
		}
		if(!(nextSpot == null)){
			dropHomePheromones();
			myOrientation.setOrientation(currentLocation, nextSpot);
			moveToSpot(nextSpot);
			if(currentLocation.getCurrState() == FoodSource.FOOD){
				currentLocation.topOffFoodPheremones();
				hasFoodItem = true;
			}
		}
	}
	
	private void returnToNest() {
		if(currentLocation.getCurrState() == FoodSource.FOOD){
			ForagingAntCell desiredLocation = currentLocation.getNeighborWithMaxHomePheremones();
			myOrientation.setOrientation(currentLocation, desiredLocation);
		}
		ForagingAntCell nextSpot = currentLocation.getNeighborWithMaxHomePheremones();
		if(nextSpot == null){
			nextSpot = currentLocation.getNeighborWithMaxHomePheremones();
		}
		if(!(nextSpot == null)){
			dropFoodPheromones();
			myOrientation.setOrientation(currentLocation, nextSpot);
			moveToSpot(nextSpot);
			if(currentLocation.getCurrState() == Nest.NEST){
				currentLocation.topOffHomePheremones();
				hasFoodItem = false;
			}
		}
	}

	private List<ForagingAntCell> getNonForwardLocations() {
		ArrayList<ForagingAntCell> nonForwardLocations = new ArrayList<>();
		ArrayList<ForagingAntCell> forwardLocations = getForwardLocations();
		if(forwardLocations == null){
			forwardLocations = new ArrayList<>();
		}
		for(Iterator i = currentLocation.getNeighbors(); i.hasNext();){
			ForagingAntCell neighbor = (ForagingAntCell) i.next();
			if(!forwardLocations.contains(neighbor)
					&& !neighbor.isFull() 
					&& !(neighbor.getCurrState() == Obstacle.OBSTACLE)){
						nonForwardLocations.add(neighbor);
			}	
		}
		if(nonForwardLocations.size() > 0){
			return nonForwardLocations;
		}
		return null;
	}

	private ForagingAntCell selectLocation(List<ForagingAntCell> locations) {
		Random rand = new Random();
		if(locations == null){
			return null;
		}
		else{
			float minX = 0.0f;
			float maxX = 1.0f;
			float finalX = rand.nextFloat() * (maxX - minX) + minX;
			for(ForagingAntCell cell : locations){
				if(finalX <= cell.getProbability()){
					return cell;
				}
			}
		}
		return locations.get(rand.nextInt(locations.size()));
	}

	private ArrayList<ForagingAntCell> getForwardLocations() {
		ArrayList<ForagingAntCell> forwardCells = new ArrayList<>();
		ArrayList<Tuple> forwardCoordinates = myOrientation.getForwardLocations(currentLocation);
		for(Iterator i = currentLocation.getNeighbors(); i.hasNext();){
			ForagingAntCell neighbor = (ForagingAntCell) i.next();
			for(Tuple spot : forwardCoordinates){
				if(spot.equals(neighbor.getPosition())
						&& !neighbor.isFull() && !(neighbor.getCurrState() == Obstacle.OBSTACLE)){
					forwardCells.add(neighbor);
				}
			}
		}
		if(forwardCells.size() > 0){
			return forwardCells;
		}
		return null;
	}


	private void moveToSpot(ForagingAntCell nextSpot) {
		currentLocation.dropAnt(this);
		nextSpot.addAnt(this);
		currentLocation = nextSpot;
	}

	private void dropFoodPheromones() {
		if(currentLocation.getCurrState() == FoodSource.FOOD){
			currentLocation.topOffFoodPheremones();
		}
		else{
			ForagingAntCell maxNeighbor = currentLocation.getNeighborWithMaxFoodPheremones();
			float max = maxNeighbor.getNumFoodPheremones();
			max = max - 2 - currentLocation.getNumFoodPheremones();
			if(max > 0){
				currentLocation.depositFoodPheremones(max);
			}
		}
	}
	
	private void dropHomePheromones(){
		if(currentLocation.getCurrState() == Nest.NEST){
			currentLocation.topOffHomePheremones();
		}
		else{
			ForagingAntCell maxNeighbor = currentLocation.getNeighborWithMaxHomePheremones();
			float max = maxNeighbor.getNumHomePheremones();
			max = max - 2 - currentLocation.getNumHomePheremones();
			if(max > 0){
				currentLocation.depositHomePheremones(max);
			}
		}
	}

	public void reset() {
		movedThisTurn = false;
		lifetime --;
		if(lifetime == 0){
			currentLocation.dropAnt(this);
			
		}
	}
}
