package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class Visual {

	public void createComboBox() {
		ObservableList<String> options = FXCollections.observableArrayList("Option 1", "Option 2", "Option 3");
		ComboBox comboBox = new ComboBox(options);
	}
	
}
