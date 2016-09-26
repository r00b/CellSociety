package gui;

import java.util.HashMap;

import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import simulations.*;

public class Animation {
	private static final String TITLE = "CellSociety";
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String LANGUAGE = "English";
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private double DEFAULT_FPS = 5;
	private double DEFAULT_MILLISECOND_DELAY = 1000 / DEFAULT_FPS;
	public double DEFAULT_SECOND_DELAY = 1.0 / DEFAULT_FPS;
	private int GRID_SIZE = 500;
	private int GRID_OFFSET = 60;

	private Pane myRoot;
	private Timeline myTimeline;
	private Grid myGrid;
	public ComboBox<String> myComboBox;
	protected Simulation mySimulation;
	ResourceBundle myResources;

	/**
	 * Get the window title for the scene
	 * 
	 * @return the title as a String
	 */
	public String getTitle() {
		return TITLE;
	}
	
	protected void resetSimulation() {
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
				Node toDelete = myRoot.lookup("#" + id);
				myRoot.getChildren().remove(toDelete);
			}
		}
	}

	/**
	 * Redraw the grid each for each step through the simulation
	 */
	private void redrawGrid() {
		clearGrid();
		for (int i = 0; i < mySimulation.getGridHeight(); i++) {
			for (int j = 0; j < mySimulation.getGridWidth(); j++) {
				double cellSize = GRID_SIZE / mySimulation.getGridWidth();
				int numVertices = 4;
				CellNode node = new CellNode();
				Polygon cell = node.getCellNode(myGrid, cellSize, GRID_OFFSET, i, j, numVertices);
				myRoot.getChildren().add(cell);
			}
		}
	}

	/**
	 * Draw myGrid to the canvas for the first time
	 */
	private void drawNewGrid() {
		double cellWidth = GRID_SIZE / mySimulation.getGridWidth();
		double cellHeight = GRID_SIZE / mySimulation.getGridHeight();
		double cellSize = GRID_SIZE / mySimulation.getGridHeight();
		for (int i = 0; i < mySimulation.getGridHeight(); i++) {
			for (int j = 0; j < mySimulation.getGridWidth(); j++) {
				int numVertices = 4;
				CellNode node = new CellNode();
				Polygon cell = node.getCellNode(myGrid, cellSize, GRID_OFFSET, i, j, numVertices);
				String id = Integer.toString(i) + Integer.toString(j);
				cell.setId(id);
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
		// if (simulation.equals(myResources.getString("PredatorPreySim")))
		// mySimulation = new PredatorPrey();
		if (simulation.equals(myResources.getString("FireSim")))
			mySimulation = new Fire();
		myGrid = mySimulation.getGrid();
	}

	/**
	 * Set up variables for the step function
	 */
	protected void initStep(String simulation) {
		setSimulation(simulation);
		drawNewGrid();
		myTimeline = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(DEFAULT_MILLISECOND_DELAY), e -> step(DEFAULT_SECOND_DELAY));
		myTimeline.setCycleCount(Timeline.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
	}

	/**
	 * Step through the gameplay
	 * 
	 * @param elapsedTime
	 */
	protected void step(double elapsedTime) {
		System.out.println("STEPPING");
		mySimulation.updateGrid();
		redrawGrid();
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
		SimControls controllers = new SimControls(this, myTimeline);
		controllers.addControls(myRoot);

		Scene simulation = new Scene(myRoot, WIDTH, HEIGHT);
		return simulation;
	}
}