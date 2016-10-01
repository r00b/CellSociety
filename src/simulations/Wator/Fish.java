package simulations;

public class Fish extends Animal{
	private boolean hasBeenEaten;
	
	public Fish(int timeToBreed) {
		super(timeToBreed);
		hasBeenEaten = false;
		myType = Wator.FISH;
	}
	
	public void markAsDead() {
		hasBeenEaten = true;
	}
	
	public boolean getHasBeenEaten() {
		return hasBeenEaten;
	}
	
	
	
}
	
