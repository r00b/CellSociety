package gui;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class Controls extends Group {

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
	 * @return the ComboBox with preset simulation choices
	 */
	private ComboBox<String> createComboBox() {
		ObservableList<String> options = FXCollections.observableArrayList("Segregation", "Predator-prey", "Fire",
				"Game ofLife");
		ComboBox<String> comboBox = new ComboBox<String>(options);
		comboBox.setId("simChoice");
		comboBox.setValue("Segregation"); // set default value
		comboBox.setMinWidth(100);
		comboBox.setLayoutX(600); // x-coordinate
		comboBox.setLayoutY(100); // y-coordinate
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
		controls.getChildren().addAll(createComboBox(), createButton("PLAY", 180), createButton("STEP", 220),
				createButton("PAUSE", 260), createButton("STOP & RESET", 300), createSlider());
		return controls;
	}
}
