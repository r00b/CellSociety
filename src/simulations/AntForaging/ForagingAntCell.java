package simulations.AntForaging;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


import javafx.scene.paint.Color;
import simulations.Cell;
import simulations.Grid;
import xml.ForagingAntsXMLParser;
import xml.XMLParser;

public class ForagingAntCell extends Cell {
	public static final int EMPTY = 0;
	public static final int ANTS = 2;
	public static final int FULL = 3;
	private float foodPheromones;
	private float homePheromones;
	private float maxPheromones;
	private float diffuseRate;
	private float evaporationRate;
	private static int maxAntsPerCell;
	private List<Ant> myAnts;
	private float probability;
	private float K;
	private float N;
	
	private final ForagingAntsXMLParser myParser;
	
	public ForagingAntCell(int i, int j, String xmlFilename) {
		super(i, j);
		myParser = new ForagingAntsXMLParser(xmlFilename);
		maxAntsPerCell = myParser.getMaxAntsPerLocation();
		K = myParser.getK();
		N = myParser.getN();
		diffuseRate = myParser.getDiffusionRatio();
		evaporationRate = myParser.getEvaporationRatio();
		foodPheromones = 0;
		homePheromones = 0;
		maxPheromones = myParser.getMaxPheremone();
		myAnts = new ArrayList<Ant>();
		updateProbability();
	}
	
	public void evaporate(){
		foodPheromones = foodPheromones * evaporationRate;
		homePheromones = homePheromones * evaporationRate;
	}
	
	public void diffuse(){
		for(Iterator i = getNeighbors(); i.hasNext();){
			ForagingAntCell neighbor = (ForagingAntCell) i.next();
			neighbor.depositFoodPheremones(foodPheromones * diffuseRate);
			neighbor.depositHomePheremones(homePheromones * diffuseRate);
			}
	}
	
	public void updateProbability() {
		probability = (float) Math.pow((K + foodPheromones), N);
	}
	
	public float getProbability(){
		return probability;
	}

	@Override
	public void mapStatesToColors() {
		updateColorMap(EMPTY, myParser.getEmptyColor());
		updateColorMap(ANTS, myParser.getAntsColor());
		updateColorMap(FULL, myParser.getFullCellColor());
	}

	@Override
	public void setRandomInitialState() {
	}

	@Override
	public void setNeighborhood(Grid grid) {
		getMyNeighborhood().set_EightNeighbor_NoWraparound(this, grid);;
	}

	public void setEmpty() {
		setCurrState(EMPTY, myParser.getEmptyColor());
	}

	public boolean isFull() {
		return getCurrState() == FULL;
	}
	
	public ForagingAntCell getNeighborWithMaxFoodPheremones() {
		ForagingAntCell maxCell = null;
		float maxPheremones = 0;
		for(Iterator i = getNeighbors(); i.hasNext();){
			ForagingAntCell neighbor = (ForagingAntCell) i.next();
			if(neighbor.getNumFoodPheremones() >= maxPheremones){
				maxCell = neighbor;
				maxPheremones = neighbor.getNumFoodPheremones();
			}
		}
		return maxCell;
	}
	
	public ForagingAntCell getNeighborWithMaxHomePheremones() {
		ForagingAntCell maxCell = null;
		float maxPheremones = 0;
		for(Iterator i = getNeighbors(); i.hasNext();){
			ForagingAntCell neighbor = (ForagingAntCell) i.next();
			if(neighbor.getNumHomePheremones() >= maxPheremones){
				maxCell = neighbor;
				maxPheremones = neighbor.getNumHomePheremones();
			}
		}
		return maxCell;
	}

	public float getNumHomePheremones() {
		return homePheromones;
	}

	public float getNumFoodPheremones() {
		return foodPheromones;
	}

	public void dropAnt(Ant ant) {
		myAnts.remove(ant);
		if(myAnts.size() == 0 && getCurrState() != Nest.NEST && getCurrState() != FoodSource.FOOD){
			setCurrState(EMPTY, findStateColor(EMPTY));
		}
	}
	
	public void addAnt(Ant ant){
		if(myAnts.size() == 0 && getCurrState() != Nest.NEST && getCurrState() != FoodSource.FOOD){
			setCurrState(ANTS, findStateColor(ANTS));
		}
		myAnts.add(ant);
		if(myAnts.size() == maxAntsPerCell){
			setCurrState(FULL, findStateColor(FULL));
		}
	}
	
	public void forageAnts(){
		ArrayList<Ant> myAntsCopy = new ArrayList<>(myAnts);
		for(Ant ant : myAntsCopy){
			ant.forage();
		}
	}

	public void topOffFoodPheremones() {
		foodPheromones = maxPheromones;
	}
	public void topOffHomePheremones() {
		homePheromones = maxPheromones;
	}

	public void depositFoodPheremones(float max) {
		foodPheromones += max;
	}
	
	public void depositHomePheremones(float max) {
		homePheromones += max;
	}

	public boolean isEmpty() {
		return getCurrState() == EMPTY;
	}

	public void resetsAnt() {
		ArrayList<Ant> myAntsCopy = new ArrayList<>(myAnts);
		for(Ant ant : myAntsCopy){
			ant.reset();
		}
		
	}

	public boolean hasAnts() {
		// TODO Auto-generated method stub
		return getCurrState() == ANTS;
	}
	
}
