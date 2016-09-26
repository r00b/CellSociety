package gui;

import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class FlowControls extends Animation {
	
	/**
	 * Handle user inputs to the speed slider and change the simulation step
	 * speed
	 * 
	 * @param e
	 *            the MouseEvent registering the click
	 * @param t
	 *            the Timeline that is running through the step function
	 */
	private Animation myAnimation;
	
	public FlowControls(Animation animation) {
		myAnimation = animation;
	}
	
	private void handleSlider(Node n, Timeline t) {
		t.stop(); // stop the current run
		t.getKeyFrames().clear(); // clear out the old Timeline with old speed
		Slider speedSlider = (Slider) n;
		double framesPerSecond = speedSlider.getValue() / 5;
		double millisecondDelay = 1000 / framesPerSecond;
		double secondDelay = 1.0 / framesPerSecond;
		KeyFrame k = new KeyFrame(Duration.millis(millisecondDelay), e -> step(secondDelay));
		t.getKeyFrames().add(k);
		t.play(); // resume simulation
		System.out.println("SLIDER WORKS");
	}

	/**
	 * Handle click to pause button by pausing the simulation
	 * 
	 * @param e
	 *            the MouseEvent registering the click
	 * @param t
	 *            the Timeline that is running through the step function
	 */
	private void handlePause(MouseEvent e, Timeline t) {
		t.pause();
	}

	/**
	 * Handle click to stop button by stopping the simulation
	 * 
	 * @param e
	 *            the MouseEvent registering the click
	 * @param t
	 *            the Timeline that is running through the step function
	 */
	private void handleStop(MouseEvent e, Timeline t) {
		t.stop();
		t.getKeyFrames().clear(); // clear out the old Timeline with old speed
	}

	/**
	 * Handle click to step button by stepping through one cycle of the
	 * simulation
	 * 
	 * @param e
	 *            the MouseEvent registering the click
	 * @param t
	 *            the Timeline that is running through the step function
	 */
	private void handleStep(MouseEvent e, Timeline t) {
		t.pause(); // in case we are already playing
		myAnimation.step(myAnimation.DEFAULT_SECOND_DELAY); // QUESTION is this okay?
	}

	/**
	 * Handle click to play button by starting the simulation
	 * 
	 * @param e
	 *            the MouseEvent registering the click
	 * @param t
	 *            the Timeline that is running through the step function
	 */
	private void handlePlay(MouseEvent e, Timeline t) {
		t.play();
	}

	@SuppressWarnings("unchecked")
	public void setEventHandlers(ResourceBundle resources, Map<String, Node> nodes, Timeline t) {
		nodes.get(resources.getString("PlayButton")).setOnMouseClicked(e -> handlePlay(e, t));
		nodes.get(resources.getString("StepButton")).setOnMouseClicked(e -> handleStep(e, t));
		nodes.get(resources.getString("PauseButton")).setOnMouseClicked(e -> handlePause(e, t));
		nodes.get(resources.getString("StopButton")).setOnMouseClicked(e -> handleStop(e, t));
		nodes.get("slider").setOnMouseDragged(e -> handleSlider(nodes.get("slider"), t));
		nodes.get("slider").setOnKeyPressed(e -> handleSlider(nodes.get("slider"), t));
		ComboBox<String> cb = (ComboBox<String>) nodes.get("simChoice"); // QUESTION
																			// ask
																			// about
																			// this

		cb.valueProperty().addListener(e -> super.initStep(cb.getValue()));
	}

}
