package gui;

import java.util.ResourceBundle;

import javafx.scene.layout.Pane;

public class SimControls {

	
	
	
	
	
	
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
