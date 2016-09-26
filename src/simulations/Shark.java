package simulations;

public class Shark extends Animal{
	private boolean hasEaten; 
	private int starveThreshold;
	private int timeUntilStarve;
	
	public Shark(Tuple pos, int timeUntilBreed) {
		super(pos, timeUntilBreed);
		hasEaten = false;
	}
	public Shark (Tuple pos, int timeUntilBreed, int starveCapacity, int timeTilDeath) {
		super(pos, timeUntilBreed);
		starveThreshold = starveCapacity;
		timeUntilStarve = timeTilDeath;
	}

	public boolean getHasEaten() {
		return hasEaten;
	}

	public void markAsFull() {
		hasEaten = true;
		timeUntilStarve = starveThreshold;
	}
	
	
	
}
