package simulations;

/**
 * @author samuelcurtis
 *         This class used to hold a cells location in a grid.
 */
public class Tuple {
    private final int I;
    private final int J;

    public Tuple(int i, int j) {
        this.I = i;
        this.J = j;
    }

    public int getIPos() {
        return I;
    }

    public int getJPos() {
        return J;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Tuple)) return false;
        Tuple otherTuple = (Tuple) other;
        return this.getIPos() == otherTuple.getIPos() && this.getJPos() == otherTuple.getJPos();
    }
}
