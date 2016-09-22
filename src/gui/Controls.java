package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

public class Controls extends Group {

	/**
	 * Create a button to signal some simulation change
	 * 
	 * @param text
	 *            is the String to be displayed on the button
	 * @return the button
	 */
	private Button createButton(String text, double y) {
		Button playButton = new Button(text);
		playButton.setMinWidth(90);
		playButton.setLayoutX(621); // x-coordinate
		playButton.setLayoutY(y); // y-coordinate
		return playButton;
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
		comboBox.setValue("Segregation"); // set default value
		comboBox.setMinWidth(100);
		comboBox.setLayoutX(600); // x-coordinate
		comboBox.setLayoutY(100); // y-coordinate
		return comboBox;
	}

	public Pane getControlPane() {
		Pane controls = new Pane();
		controls.getChildren().addAll(createComboBox(), createButton("PLAY", 180), createButton("STOP", 220),
				createButton("STEP", 260));
		return controls;
	}

}
