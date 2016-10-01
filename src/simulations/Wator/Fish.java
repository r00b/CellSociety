package simulations.Wator;

public class Fish extends Animal{
	private boolean hasBeenEaten;
	
	public Fish(int timeToBreed) {
		super(timeToBreed);
		hasBeenEaten = false;
		myType = WatorCell.FISH;
	}
	
	public void markAsDead() {
		hasBeenEaten = true;
	}
	
	public boolean getHasBeenEaten() {
		return hasBeenEaten;
	}
	
	
	
}
	
