package simulations;

public class Animal {
	protected int myTimeLeftToBreed;
	protected Tuple myPosition;
	protected final int myInitialTimeToBreed;
	
	public Animal(Tuple pos, int timeToBreed) {
		myPosition = pos;
		myTimeLeftToBreed = timeToBreed;
		myInitialTimeToBreed = timeToBreed;
	}
	
	public int getTimeLeftToBreed() {
		return myTimeLeftToBreed;
	}
	public Tuple getPosition() {
		return myPosition;
	}
	public void setPosition(Tuple pos) {
		myPosition = pos;
	}
	
	public void resetTimeToBreed() {
		myTimeLeftToBreed = myInitialTimeToBreed;
	}
}
