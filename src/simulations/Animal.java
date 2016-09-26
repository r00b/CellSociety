package simulations;

public class Animal {
	protected int myTimeLeftUntilBreed;
	protected Tuple myPosition;
	protected final int myInitialTimeUntilBreed;
	
	public Animal(Tuple pos, int timeUntilBreed) {
		myPosition = pos;
		myTimeLeftUntilBreed = timeUntilBreed;
		myInitialTimeUntilBreed = timeUntilBreed;
	}
	
	public int getTimeLeftToBreed() {
		return myTimeLeftUntilBreed;
	}
	public Tuple getPosition() {
		return myPosition;
	}
	public void setPosition(Tuple pos) {
		myPosition = pos;
	}
	
	public void resetTimeToBreed() {
		myTimeLeftUntilBreed = myInitialTimeUntilBreed;
	}
}
