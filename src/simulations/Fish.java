package simulations;

public class Fish extends Animal{
	private boolean hasBeenEaten;
	
	public Fish(Tuple pos, int timeToBreed) {
		super(pos, timeToBreed);
		hasBeenEaten = false;
	}
	
	public void markAsDead() {
		hasBeenEaten = true;
	}
	
	public boolean getHasBeenEaten() {
		return hasBeenEaten;
	}
	
	
	
}
	
