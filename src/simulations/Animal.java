package simulations;

public class Animal {
	protected int myTimeLeftUntilBreed;
	protected final int myInitialTimeUntilBreed;
	
	public Animal(int timeUntilBreed) {
		myTimeLeftUntilBreed = timeUntilBreed;
		myInitialTimeUntilBreed = timeUntilBreed;
	}
	
	public int getTimeLeftToBreed() {
		return myTimeLeftUntilBreed;
	}

	public void resetTimeToBreed() {
		myTimeLeftUntilBreed = myInitialTimeUntilBreed;
	}
	
	public void decrementBreedTime() {
		myTimeLeftUntilBreed -= 1;
		if (myTimeLeftUntilBreed < 0) {
			myTimeLeftUntilBreed = 0;
		}
	}
}
