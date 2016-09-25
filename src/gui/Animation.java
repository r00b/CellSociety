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
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import simulations.GameOfLife;
import simulations.Grid;
import simulations.Simulation;
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
	/**
	 * Draw myGrid to the canvas
	 */
	private void drawGrid(Grid grid) {
		double cellWidth = GRID_SIZE / mySimulation.getGridWidth();
		double cellHeight = GRID_SIZE / mySimulation.getGridHeight();
		for (int i = 0; i < mySimulation.getGridWidth(); i++) {
			for (int j = 0; j < mySimulation.getGridHeight(); j++) {
				Rectangle a = new Rectangle(GRID_OFFSET + i * cellWidth, GRID_OFFSET + j * cellHeight, cellWidth, cellHeight);
				// TODO: get curr state from cell for color
				a.setFill(grid.getCell(i, j).getStateColor());
				a.setStroke(Color.BLACK);
				myRoot.getChildren().add(a);
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
//		if (simulation.equals(myResources.getString("SegregationSim")))
//			mySimulation = new Segregation();
//		if (simulation.equals(myResources.getString("PredatorPreySim")))
//			mySimulation = new PredatorPrey();
//		if (simulation.equals(myResources.getString("FireSim")))
//			mySimulation = new Fire();
//		Grid cellGrid = mySimulation.initGrid();
//		int gridSize = mySimulation.getGridSize();
//		drawGrid(cellGrid);
		drawGrid(mySimulation.getGrid()); 
	}
	/**
	 * Set up variables for the step function
	 */
	protected void initStep(String simulation) {
		setSimulation(simulation);
		myTimeline = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(DEFAULT_MILLISECOND_DELAY), e -> step(DEFAULT_SECOND_DELAY));
		myTimeline = new Timeline();
		myTimeline.setCycleCount(Timeline.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
	}
	/**
	 * Step through the gameplay
	 * 
	 * @param elapsedTime
	 */
	protected void step(double elapsedTime) {
		mySimulation.updateGrid();
		drawGrid(mySimulation.getGrid());
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
	@SuppressWarnings("unchecked") // QUESTION ask TA if this is okay
	public Scene init() {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
		ControlElements controlElements = new ControlElements();
		myRoot = controlElements.getControlPane();
		
		
		HashMap<String, Node> nodes = controlElements.getControls(myRoot);
		initStep(((ComboBox<String>) nodes.get("simChoice")).getValue());
		FlowControls f = new FlowControls(this);
		f.setEventHandlers(myResources, nodes, myTimeline);
		Scene simulation = new Scene(myRoot, WIDTH, HEIGHT);
		return simulation;
	}
}