package simulations;

public class Shark extends Animal{
	private Fish myNextMeal; //fish that the shark will eat next
	
	public Shark(Tuple pos, int timeToBreed) {
		super(pos, timeToBreed);
	}

	public Fish getMyNextMeal() {
		return myNextMeal;
	}

	public void setMyNextMeal(Fish myNextMeal) {
		this.myNextMeal = myNextMeal;
	}
	
	
	
}
