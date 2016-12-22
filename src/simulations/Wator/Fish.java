package simulations.Wator;

/**
 * The Fish object represents predators in Wa-Tor world simulation
 * It is used by Wator.java to determine state of cells given the information stored in Fish objects
 * It assumes that WatorCell objects have an int value FISH that represents the fish state
 *
 * @author Aaron Chang
 */
public class Fish extends Animal {

    public Fish(int timeToBreed) {
        super(timeToBreed);
        myType = WatorCell.FISH;
    }


}

