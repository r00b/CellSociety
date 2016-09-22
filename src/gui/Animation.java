package gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Animation {
	private static final String TITLE = "CellSociety";
	
	/**
	 * Get the window title for the scene
	 * @return the title as a String
	 */
	public String getTitle() {
		return TITLE;
	}
	
	
//	private void drawVisuals(Visual visuals, Group root) {
//		
//		
//		ComboBox<String> cb = visuals.createComboBox();
//		cb.setTranslateX(20);
//		cb.setTranslateY(20);
//		cb.setMinWidth(400);
//
//		root.getChildren().add(cb);
//	}
	
	/**
	 * Initialize simulation stage
	 * 
	 * @param width the width of the window
	 * @param height the height of the window
	 * @return the scene
	 */
	public Scene init(int width, int height) {
		Group root = new Group();
		Scene simulation = new Scene(root, width, height, Color.GRAY);
		Controls controllers = new Controls();
		simulation.setRoot(controllers.getControlPane());
		return simulation;
	}
}
