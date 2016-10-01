package simulations;

public class Animal {
	protected int myType;
	protected int myTimeLeftUntilBreed;
	protected final int myInitialTimeUntilBreed;
	
	public Animal(int timeUntilBreed) {
		myTimeLeftUntilBreed = timeUntilBreed;
		myInitialTimeUntilBreed = timeUntilBreed;
	}
	
	public boolean canBreed() {
		if (myTimeLeftUntilBreed == 0) {
			return true;
		}
		else {
			return false;
		}
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
	
	public int getType() {
		return myType;
	}
}
