package simulations;

public class Shark extends Animal{
	private boolean hasEaten; 
	private int starveThreshold;
	private int timeUntilStarve;
	
	public Shark(int timeUntilBreed) {
		super(timeUntilBreed);
		hasEaten = false;
	}
	public Shark (int timeUntilBreed, int starveCapacity) {
		super(timeUntilBreed);
		starveThreshold = starveCapacity;
		timeUntilStarve = starveCapacity;
	}

	public boolean getHasEaten() {
		return hasEaten;
	}
	
	public int getTimeUntilStarve() {
		return timeUntilStarve;
	}

	public void markAsFull() {
		timeUntilStarve = starveThreshold;
	}
	
	public void decrementTimeUntilStarve() {
		timeUntilStarve -= 1;
	}
	
	
	
}
