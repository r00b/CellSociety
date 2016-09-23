package gui;

import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import simulations.Grid;

public class Animation {
	private static final String TITLE = "CellSociety";
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String LANGUAGE = "English";
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private double DEFAULT_FPS = 5;
	private double DEFAULT_MILLISECOND_DELAY = 1000 / DEFAULT_FPS;
	private double DEFAULT_SECOND_DELAY = 1.0 / DEFAULT_FPS;
//	private Simulation mySimulation;
	private Grid myGrid;
	ResourceBundle myResources;

	/**
	 * Get the window title for the scene
	 * 
	 * @return the title as a String
	 */
	public String getTitle() {
		return TITLE;
	}

	/**
	 * Handle user inputs to the speed slider and change the simulation step
	 * speed
	 * 
	 * @param e
	 *            the MouseEvent registering the click
	 * @param t
	 *            the Timeline that is running through the step function
	 */
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
		t.play();
		t.pause();
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

	/**
	 * Set the simulation to a specified choice
	 * 
	 * @param sim a String corresponding to the desired simulation
	 */
	private void setSimulation(String sim) {
		if (sim.equals(myResources.getString("GameOfLifeSim"))) {
//			mySimulation = new gameOfLifeSimulation();
		}
		if (sim.equals(myResources.getString("SegregationSim"))) {
//			mySimulation = new segregationSimulation();
		}
		if (sim.equals(myResources.getString("PredatorPreySim"))) {
//			mySimulation = new predatorPreySimulation();
		}
		if (sim.equals(myResources.getString("FireSim"))) {
//			mySimulation = new fireSimulation();
		}
//		myGrid = mySimulation.getGrid();
	}

	/**
	 * Set up variables for the step function
	 */
	private Timeline initStep() {
		setSimulation(myResources.getString("GameOfLifeSim"));
		Timeline t = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(DEFAULT_MILLISECOND_DELAY), e -> step(DEFAULT_SECOND_DELAY));
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
//		mySimulation.updateGrid();
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
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
		Controls controllers = new Controls();
		Pane root = controllers.getControlPane();
		HashMap<String, Node> nodes = controllers.getControls(root);
		Scene simulation = new Scene(root, WIDTH, HEIGHT, Color.GRAY);
		Timeline t = initStep();
		nodes.get(myResources.getString("PlayButton")).setOnMouseClicked(e -> handlePlay(e, t));
		nodes.get(myResources.getString("StepButton")).setOnMouseClicked(e -> handleStep(e, t));
		nodes.get(myResources.getString("PauseButton")).setOnMouseClicked(e -> handlePause(e, t));
		nodes.get(myResources.getString("StopButton")).setOnMouseClicked(e -> handleStop(e, t));
		nodes.get("slider").setOnMouseDragged(e -> handleSlider(nodes.get("slider"), t));
		nodes.get("slider").setOnKeyPressed(e -> handleSlider(nodes.get("slider"), t));
		@SuppressWarnings("unchecked")
		// TODO ask about this
		ComboBox<String> cb = (ComboBox<String>) nodes.get("simChoice");
		cb.valueProperty().addListener(e -> setSimulation(cb.getValue()));

		return simulation;
	}
}
