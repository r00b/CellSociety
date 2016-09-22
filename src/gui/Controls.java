package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

public class Controls extends Group {

	/**
	 * Return the pane containing all of the controls for the simulation
	 * @return the pane containing all of the controls for the simulation
	 */
	public Controls() {
	}
	
	public ComboBox<String> createComboBox() {
		ObservableList<String> options = FXCollections.observableArrayList("Option 1", "Option 2", "Option 3");
		ComboBox<String> comboBox = new ComboBox<String>(options);
		return comboBox;
	}
	
	public Pane getControlPane() {
		Pane p = new Pane();
		p.getChildren().add(createComboBox());
		return p;
	}
	
}
