package gui;

import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import simulations.*;
import simulations.Fire.Fire;
import simulations.GameOfLife.GameOfLife;
import simulations.Segregation.Segregation;

/**
 * @author Robert H. Steilberg II | rhs16
 * 
 *         The Animation class handles the GUI for the program. After creating
 *         the general Scene, the SimControls class is used to populate the
 *         scene with control elements. The SimEvents class adds functionality
 *         to these elements. Then, according to the default simulation set in
 *         the properties file, the grid is initialized via GridParser class to
 *         that simulation's parameters. The step function is prepared for
 *         stepping, and the simulation begins when the play button is clicked.
 *         The step function updates the simulation grid on each frame via the
 *         GridParser class. Each grid iteration is drawn via the CellNode class
 *         that creates each cell according to a specified shape and then
 *         returns the cell with the correct state according to the simulation.
 *         The simulation continues until it is stopped by the user.
 * 
 *         Dependencies: SimControls.java, SimEvents.java, CellNode.java,
 *         GridParser.java
 */
public class Animation {
	private static final String TITLE = "CellSociety";
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String LANGUAGE = "English";
	private static final String STYLESHEET = "style.css";
	private ResourceBundle myResources;
	private Pane myRoot;
	private Grid myGrid;
	private Simulation mySimulation;
	private Timeline myTimeline;
	private GridParser myGridParser;
	// myComboBox is protected because it is instantiated in the SimControls
	// class when it is created. myComboBox must be available to Animation.java
	// because its value must be checked each time the user chooses a new
	// simulation to run.
	// QUESTION is this explanation okay?
	protected ComboBox<String> myComboBox;

	/**
	 * Get the window title for the scene
	 * 
	 * @return the title as a String
	 */
	public String getTitle() {
		return TITLE;
	}

	/**
	 * Stop the step function, clear the grid, and prepare for a new simulation
	 * 
	 * @param animation
	 *            the current Timeline
	 */
	protected void resetSimulation(Timeline animation) {
		animation.stop();
		myGridParser.clearGrid();
		initStep(myComboBox.getValue());
	}

	/**
	 * Set the simulation to a specified choice
	 * 
	 * @param sim
	 *            a String corresponding to the desired simulation
	 */
	private void setSimulation(String simulation) {
		if (simulation.equals(myResources.getString("GameOfLifeSim"))) {
			mySimulation = new GameOfLife();
		}
		if (simulation.equals(myResources.getString("SegregationSim"))) {
			mySimulation = new Segregation();
		}
		if (simulation.equals(myResources.getString("PredatorPreySim"))) {
			mySimulation = new Wator();
		}
		if (simulation.equals(myResources.getString("FireSim")))
			mySimulation = new Fire();
		myGrid = mySimulation.getGrid();
	}

	/**
	 * Set up variables for the step function
	 * 
	 * @param simulation
	 *            a String specifying which simulation should be loaded
	 */
	protected void initStep(String simulation) {
		setSimulation(simulation);
		myGridParser = new GridParser(mySimulation, myGrid, myResources, myRoot);
		myGridParser.drawGrid(true); // pass true because this is a new grid
		myTimeline = new Timeline();
		int framesPerSecond = Integer.parseInt(myResources.getString("DefaultFPS"));
		KeyFrame frame = new KeyFrame(Duration.millis(1000 / framesPerSecond), e -> step(1.0 / framesPerSecond));
		myTimeline.setCycleCount(Timeline.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
	}

	/**
	 * Step through the simulation
	 * 
	 * @param elapsedTime
	 */
	protected void step(double elapsedTime) {
		mySimulation.updateGrid(); // calculate new grid
		// pass false because we are updating an old grid
		myGridParser.drawGrid(false);
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
		myRoot = new Pane();
		myRoot.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
		initStep(myResources.getString("DefaultSimulation"));
		SimControls controllers = new SimControls(this, myTimeline, myResources);
		controllers.addControls(myRoot);
		Scene simulation = new Scene(myRoot, Integer.parseInt(myResources.getString("WindowWidth")),
				Integer.parseInt(myResources.getString("WindowHeight")));
		return simulation;
	}
}