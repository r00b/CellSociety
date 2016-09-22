package gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Animation {
	private static final String TITLE = "CellSociety";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	/**
	 * Get the window title for the scene
	 * 
	 * @return the title as a String
	 */
	public String getTitle() {
		return TITLE;
	}
	
	/**
	 * Handle click to pause button by pausing the simulation
	 * 
	 * @param e the MouseEvent registering the click
	 * @param t the Timeline that is running through the step function
	 */
	private void handlePause(MouseEvent e, Timeline t) {
		t.pause();
	}
	
	/**
	 * Handle click to stop button by stopping the simulation
	 * 
	 * @param e the MouseEvent registering the click
	 * @param t the Timeline that is running through the step function
	 */
	private void handleStop(MouseEvent e, Timeline t) {
		t.stop();
	}
	
	/**
	 * Handle click to step button by stepping through one cycle of the simulation
	 * 
	 * @param e the MouseEvent registering the click
	 * @param t the Timeline that is running through the step function
	 */
	private void handleStep(MouseEvent e, Timeline t) {
		t.pause(); // in case we are already playing
		t.play();
		t.pause();
	}
	
	/**
	 * Handle click to play button by starting the simulation
	 * 
	 * @param e the MouseEvent registering the click
	 * @param t the Timeline that is running through the step function
	 */
	private void handlePlay(MouseEvent e, Timeline t) {
		t.play();
	}

	/**
	 * Set up variables for the step function
	 */
	private Timeline initStep() {
		Timeline t = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE);
		t.getKeyFrames().add(frame);
		return t;
	}

	/**
	 * Step through the gameplay
	 * 
	 * @param elapsedTime
	 */
	private void step(double elapsedTime) {
	}

	/**
	 * Initialize simulation stage
	 * 
	 * @param width
	 *            the width of the window
	 * @param height
	 *            the height of the window
	 * @return the scene
	 */
	public Scene init() {
		Controls controllers = new Controls();
		Pane root = controllers.getControlPane();
		Scene simulation = new Scene(root, WIDTH, HEIGHT, Color.GRAY);
		Timeline t = initStep();
		//TODO not sure if there's a better way to get a child
		//System.out.println(root.getChildren());
		root.getChildren().get(1).setOnMouseClicked(e -> handlePlay(e,t));
		root.getChildren().get(2).setOnMouseClicked(e -> handleStep(e,t));
		root.getChildren().get(3).setOnMouseClicked(e -> handlePause(e,t));
		root.getChildren().get(4).setOnMouseClicked(e -> handleStop(e,t));
		return simulation;
	}
}
