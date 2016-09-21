package gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class Animation {
	
	
	/**
	 * Initialize simulation stage
	 * 
	 * @param width the width of the window
	 * @param height the height of the window
	 * @return the scene
	 */
	public Scene init(int width, int height) {
		Scene simulation = new Scene(new Group(), width, height, Color.WHITE);
		return simulation;
	}
}
