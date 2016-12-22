package simulations.Wator;

/**
 * The Animal object models generic behavior/information of predators and prey
 * It is used by Wator.java to represent Sharks and Fish
 * Wator.java uses these objects to determine how whether the state of a cell should be Shark, Fish, or empty
 *
 * @author Aaron Chang
 */
public class Animal {
    protected int myType;
    protected int myTimeLeftUntilBreed;
    protected final int myInitialTimeUntilBreed;

    public Animal(int timeUntilBreed) {
        myTimeLeftUntilBreed = timeUntilBreed;
        myInitialTimeUntilBreed = timeUntilBreed;
    }

    /**
     * Determines if an animal can breed (create another animal) based off the myTimeLeftUntilBreed attribute
     * called by Wator.java
     *
     * @return boolean - true if an animal can breed
     */
    public boolean canBreed() {
        if (myTimeLeftUntilBreed == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * resets an animal's myTimeleftUntilBreed attribute
     * called by Wator.java whenever an animal breeds
     */
    public void resetTimeToBreed() {
        myTimeLeftUntilBreed = myInitialTimeUntilBreed;
    }

    /**
     * decrements an animal's myTimeLeftUntilBreed attribute, thus bringing it one round closer to breeding
     * called by Wator.java when an animal survives a round and doesn't breed
     */
    public void decrementBreedTime() {
        myTimeLeftUntilBreed -= 1;
        if (myTimeLeftUntilBreed < 0) {
            myTimeLeftUntilBreed = 0;
        }
    }

    /**
     * returns the type of an animal (shark or fish)
     *
     * @return int - type of an animal
     */
    public int getType() {
        return myType;
    }
}
