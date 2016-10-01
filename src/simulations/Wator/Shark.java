package simulations.Wator;

public class Shark extends Animal{
	private int starveThreshold;
	private int timeUntilStarve;
	
	public Shark(int timeUntilBreed) {
		super(timeUntilBreed);
		myType = WatorCell.SHARK;
	}
	public Shark (int timeUntilBreed, int starveCapacity) {
		super(timeUntilBreed);
		starveThreshold = starveCapacity;
		timeUntilStarve = starveCapacity;
		myType = WatorCell.SHARK;
	}
	
	public boolean willStarve() {
		if (timeUntilStarve == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void markAsFull() {
		timeUntilStarve = starveThreshold;
	}
	
	public void decrementTimeUntilStarve() {
		timeUntilStarve -= 1;
	}
	
	
	
}
