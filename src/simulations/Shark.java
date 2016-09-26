package simulations;

public class Shark extends Animal{
	private Fish myNextMeal; //fish that the shark will eat next
	private int starveThreshold;
	private int timeUntilStarve;
	
	public Shark(Tuple pos, int timeUntilBreed) {
		super(pos, timeUntilBreed);
	}
	public Shark (Tuple pos, int timeUntilBreed, int starveCapacity, int timeTilDeath) {
		super(pos, timeUntilBreed);
		starveThreshold = starveCapacity;
		timeUntilStarve = timeTilDeath;
	}

	public Fish getMyNextMeal() {
		return myNextMeal;
	}

	public void setMyNextMeal(Fish myNextMeal) {
		this.myNextMeal = myNextMeal;
	}
	
	
	
}
