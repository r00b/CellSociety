package gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Animation {
	private static final String TITLE = "CellSociety";
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	/**
	 * Get the window title for the scene
	 * 
	 * @return the title as a String
	 */
	public String getTitle() {
		return TITLE;
	}

	/**
	 * Set up variables for the step function
	 */
	private void initStep() {
		Timeline t = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE);
		t.getKeyFrames().add(frame);
		t.play();
	}

	/**
	 * Step through the gameplay
	 * 
	 * @param elapsedTime
	 */
	private void step(double elapsedTime) {
		System.out.println("Step works!");
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
	public Scene init(int width, int height) {
		Controls controllers = new Controls();
		Pane root = controllers.getControlPane();
		Scene simulation = new Scene(root, width, height, Color.GRAY);
		initStep();
		return simulation;
	}
}
