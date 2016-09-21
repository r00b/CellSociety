package gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class Animation {
	private static final String TITLE = "CellSociety";
	
	public String getTitle() {
		return TITLE;
	}
	
	private void drawVisuals(Visual visuals) {
		visuals.createComboBox();
	}
	
	/**
	 * Initialize simulation stage
	 * 
	 * @param width the width of the window
	 * @param height the height of the window
	 * @return the scene
	 */
	public Scene init(int width, int height) {
		Scene simulation = new Scene(new Group(), width, height, Color.GRAY);
		Visual visuals = new Visual();
		drawVisuals(visuals);
		return simulation;
	}
}
