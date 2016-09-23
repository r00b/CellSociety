package gui;

import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class ControlElements extends Group {
	//TODO don't have these here?
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String LANGUAGE = "English";

	/**
	 * Create a slider to specify the speed of the simulation step
	 * 
	 * @return the slider
	 */
	private Slider createSlider() {
		Slider speedSlider = new Slider();
		speedSlider.setId("slider");
		speedSlider.setMin(0);
		speedSlider.setMax(100);
		speedSlider.setValue(50);
		speedSlider.setShowTickLabels(true);
		speedSlider.setShowTickMarks(true);
		speedSlider.setMajorTickUnit(50);
		speedSlider.setMinorTickCount(5);
		speedSlider.setBlockIncrement(10);
		speedSlider.setLayoutX(600);
		speedSlider.setLayoutY(450);
		return speedSlider;
	}

	/**
	 * Create a button to signal some simulation change
	 * 
	 * @param text
	 *            The String to be displayed on the button
	 * @param y
	 *            The y-coordinate of the button
	 * @return the button
	 */
	private Button createButton(String text, double y) {
		Button b = new Button(text);
		b.setId(text);
		b.setMinWidth(110);
		b.setLayoutX(612);
		b.setLayoutY(y);
		return b;
	}

	/**
	 * Create the ComboBox to display and change simulations
	 * 
	 * @param resources
	 *            the ResourceBundle containing the properties file
	 * @return the ComboBox with preset simulation choices
	 */
	private ComboBox<String> createComboBox(ResourceBundle resources) {
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.getItems().addAll(resources.getString("GameOfLifeSim"), resources.getString("SegregationSim"),
				resources.getString("PredatorPreySim"), resources.getString("FireSim"));
		comboBox.setId("simChoice");
		comboBox.setValue(resources.getString("GameOfLifeSim")); // set default
		comboBox.setMinWidth(100);
		comboBox.setLayoutX(600);
		comboBox.setLayoutY(100);
		return comboBox;
	}

	/**
	 * Put all child nodes in a map so that we can access them later
	 * 
	 * @param p
	 *            is the pane containing the child nodes
	 * @return the map containing the nodes' IDs mapped to the nodes
	 */
	public HashMap<String, Node> getControls(Pane p) {
		HashMap<String, Node> nodes = new HashMap<String, Node>();
		for (Node n : p.getChildren()) {
			nodes.put(n.getId(), n);
		}
		return nodes;
	}

	/**
	 * Create the pane containing all of the simulation controls
	 * 
	 * @return the control pane
	 */
	public Pane getControlPane() {
		Pane controls = new Pane();
		controls.setStyle("-fx-background-color: #98a2c5");
		ResourceBundle resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
		controls.getChildren().add(createComboBox(resources));
		controls.getChildren().add(createButton(resources.getString("PlayButton"), 180));
		controls.getChildren().add(createButton(resources.getString("StepButton"), 220));
		controls.getChildren().add(createButton(resources.getString("PauseButton"), 260));
		controls.getChildren().add(createButton(resources.getString("StopButton"), 300));
		controls.getChildren().add(createSlider());
		return controls;
	}
}
