package gui;

import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Slider;
import javafx.util.Duration;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         The SimEvents class contains all of the functions that are called by
 *         the event handlers created in the SimControls class when each GUI
 *         element is initially created. These events handle the control flow of
 *         the simulation.
 */
public class SimEvents {
    private Animation myAnimation;
    private Timeline myTimeline;
    private ResourceBundle myResources;

    SimEvents(Animation simulation, Timeline timeline, ResourceBundle resources) {
        myAnimation = simulation;
        myTimeline = timeline;
        myResources = resources;
    }

    /**
     * Changes the speed of a step function according to a value given by a
     * slider
     *
     * @param speedSlider is the slider object containing the speed value
     */
    protected void handleSlider(Slider speedSlider) {
        // set MaxSpeed in resource file lower for higher allowed max speed
        double framesPerSecond = speedSlider.getValue() / Integer.parseInt(myResources.getString("MaxSpeed"));
        double millisecondDelay = 1000 / framesPerSecond;
        double secondDelay = 1.0 / framesPerSecond;
        myTimeline.stop(); // stop the current run
        myTimeline.getKeyFrames().clear(); // clear out the old frame
        KeyFrame frame = new KeyFrame(Duration.millis(millisecondDelay), e -> myAnimation.step(secondDelay));
        myTimeline.getKeyFrames().add(frame);
        myTimeline.play();
    }

    /**
     * Handle click to XML file change button by prompting user for a new XML
     * file and resetting the simulation with the new file
     */
    protected void handleXMLFileChange() {
        myAnimation.resetSimulation(myTimeline, true);
    }

    /**
     * Handle click to stop button by stopping the simulation
     */
    protected void handleStop() {
        // pass false because only want to reset the current simulation
        myAnimation.resetSimulation(myTimeline, false);
    }

    /**
     * Handle click to pause button by pausing the simulation
     */
    protected void handlePause() {
        myTimeline.pause();
    }

    /**
     * Handle click to step button by stepping through one cycle of the
     * simulation
     */
    protected void handleStep() {
        myTimeline.pause(); // in case we are already playing
        myAnimation.step(1000 / Integer.parseInt(myResources.getString("DefaultFPS")));
    }

    /**
     * Handle click to play button by starting the simulation
     */
    protected void handlePlay() {
        myTimeline.play();
    }
}
