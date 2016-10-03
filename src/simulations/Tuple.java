package simulations;

/**
 * @author samuelcurtis
 * This class used to hold a cells location in a grid.
 *
 */
public class Tuple {
	private final int I;
	private final int J;
	
	public Tuple(int i, int j){
		this.I = i;
		this.J = j;
	}

	public int getIPos(){
		return I;
	}
	
	public int getJPos(){
		return J;
	}
}
