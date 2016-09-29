package gui;

import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import simulations.*;

/**
 * 
 * @author Robert H. Steilberg II | rhs16
 * 
 *         The Animation class handles the GUI for the program. After creating
 *         the general Scene, the SimControls class is used to populate the
 *         scene with functional control elements. Then, according to the
 *         default simulation set in the properties file, the grid is
 *         initialized via the simulation's initGrid() method to that
 *         simulation's parameters. The step function is prepared for stepping.
 *         The simulation begins when the play button is clicked. The step
 *         function updates the simulation grid on each frame via the
 *         updateGrid() method, clears the old grid, and then prints the new
 *         one. Each grid iteration is drawn via the CellNode class that creates
 *         each cell according to a specified shape and then returns the cell
 *         with the correct state according to the simulation. The simulation
 *         continues infinitely, or until it is stopped by the user.
 * 
 *         Dependencies: SimControls.java, CellNode.java
 */
public class Animation {
	private static final String TITLE = "CellSociety";
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String LANGUAGE = "English";

	private int GRID_SIZE = 500;
	private ResourceBundle myResources;
	private Pane myRoot;
	private Timeline myTimeline;
	private Grid myGrid;
	protected Simulation mySimulation;
	public ComboBox<String> myComboBox;

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
		clearGrid();
		initStep(myComboBox.getValue());
	}

	/**
	 * Clear the grid and re-initialize the simulation
	 */
	private void clearGrid() {
		for (int i = 0; i < mySimulation.getGridHeight(); i++) {
			for (int j = 0; j < mySimulation.getGridWidth(); j++) {
				String id = Integer.toString(i) + Integer.toString(j);
				// get each node via CSS id
				Node toDelete = myRoot.lookup("#" + id);
				myRoot.getChildren().remove(toDelete);
			}
		}
	}

	/**
	 * Draw the grid to the scene
	 * 
	 * @param newGrid
	 *            true if the grid is being drawn for the first time, false
	 *            otherwise
	 */
	private void drawGrid(boolean newGrid) {
		if (!newGrid) {
			clearGrid();
		}
		double cellSize = GRID_SIZE / mySimulation.getGridHeight();
		for (int i = 0; i < mySimulation.getGridHeight(); i++) {
			for (int j = 0; j < mySimulation.getGridWidth(); j++) {
				int numVertices = 4;
				CellNode node = new CellNode();
				Polygon cell = node.getCellNode(myGrid, cellSize, Integer.parseInt(myResources.getString("GridOffset")),
						i, j, numVertices);
				if (newGrid) {
					String id = Integer.toString(i) + Integer.toString(j);
					// set a CSS id so we can get this cell later to remove it
					cell.setId(id);
				}
				myRoot.getChildren().add(cell);
			}
		}

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
		drawGrid(true); // pass true because this is a new grid
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
		drawGrid(false); // pass false because we are updating an old grid
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
		initStep(myResources.getString("DefaultSimulation"));
		SimControls controllers = new SimControls(this, myTimeline, myResources);
		controllers.addControls(myRoot);
		Scene simulation = new Scene(myRoot, Integer.parseInt(myResources.getString("WindowWidth")),
				Integer.parseInt(myResources.getString("WindowHeight")));
		return simulation;
	}
}