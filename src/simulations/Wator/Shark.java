package simulations.Wator;
/**
 * The Shark object represents predators in Wa-Tor world simulation
 * It is used by Wator.java to determine state of cells given the information stored in Shark objects
 * It assumes that WatorCell objects have an int value SHARK that represents the shark state
 * @author Aaron Chang
 *
 */
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
	/**
	 * determines if a shark will starve or not
	 * used by Wator.java to determine the state of cells
	 * @return boolean - true if shark will die, false if shark will survive
	 */
	public boolean willStarve() {
		if (timeUntilStarve == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * resets the shark's timeUntilStarve attribute
	 * Called by Wator.java whenever a shark eats a fish
	 */
	public void markAsFull() {
		timeUntilStarve = starveThreshold;
	}
	/**
	 * decrements the shark's timeUntilStarve attribute
	 * called by Wator.java whenever a shark fails to eat a fish during one round
	 */
	public void decrementTimeUntilStarve() {
		timeUntilStarve -= 1;
	}
	
	
	
}
