package gui;

import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SimControls extends Animation {
	private Timeline myTimeline;
	private Animation myAnimation;
	
	SimControls(Animation currAnimation, Timeline timeline) {
		myAnimation = currAnimation;
		myTimeline = timeline;
	}
	
	private void handleSlider(Slider speedSlider) {
		double DEFAULT_FPS = 5;
		double DEFAULT_MILLISECOND_DELAY = 1000 / DEFAULT_FPS;
		double DEFAULT_SECOND_DELAY = 1.0 / DEFAULT_FPS;
		
		myTimeline.pause(); // stop the current run
		myTimeline.getKeyFrames().clear(); // clear out the old Timeline with old speed
		KeyFrame frame = new KeyFrame(Duration.millis(DEFAULT_MILLISECOND_DELAY), e -> step(DEFAULT_SECOND_DELAY));
		myTimeline.getKeyFrames().add(frame);

		
//		
//		double framesPerSecond = speedSlider.getValue() / 5;
//		double millisecondDelay = 1000 / framesPerSecond;
//		double secondDelay = 1.0 / framesPerSecond;
//		KeyFrame k = new KeyFrame(Duration.millis(millisecondDelay), e -> step(secondDelay));
//		myTimeline.getKeyFrames().add(k);
//		myTimeline.play(); // resume simulation
		System.out.println("SLIDER WORKS");
	}
	
	/**
	 * Handle click to stop button by stopping the simulation
	 * 
	 * @param e
	 *            the MouseEvent registering the click
	 * @param t
	 *            the Timeline that is running through the step function
	 */
	private void handleStop(Timeline animation) {
		animation.stop();
		myAnimation.resetSimulation();
	}
	
	/**
	 * Handle click to pause button by pausing the simulation
	 * 
	 * @param e
	 *            the MouseEvent registering the click
	 * @param t
	 *            the Timeline that is running through the step function
	 */
	private void handlePause(Timeline animation) {
		animation.pause();
		System.out.println("PAUSE");

	}
	
	/**
	 * Handle click to step button by stepping through one cycle of the
	 * simulation
	 * 
	 * @param e
	 *            the MouseEvent registering the click
	 * @param t
	 *            the Timeline that is running through the step function
	 */
	private void handleStep(Timeline animation) {
		animation.pause(); // in case we are already playing
		myAnimation.step(DEFAULT_SECOND_DELAY);
		System.out.println("STEP");

	}
	
	/**
	 * Handle click to play button by starting the simulation
	 * 
	 * @param e
	 *            the MouseEvent registering the click
	 * @param t
	 *            the Timeline that is running through the step function
	 */
	private void handlePlay(Timeline animation) {
		animation.play();
		System.out.println("PLAY");
	}
	
	/**
	 * Create a slider to specify the speed of the simulation step
	 * 
	 * @return the slider
	 */
	private Slider createSlider() {
		Slider speedSlider = new Slider();
		speedSlider.setShowTickMarks(true);
		speedSlider.setLayoutX(Integer.parseInt(myResources.getString("SliderXPos")));
		speedSlider.setLayoutY(Integer.parseInt(myResources.getString("SliderYPos")));
		speedSlider.setValue(Integer.parseInt(myResources.getString("SliderDefaultValue")));
		
		speedSlider.setOnMouseDragged(e -> handleSlider(speedSlider));
		speedSlider.setOnKeyPressed(e -> handleSlider(speedSlider));
		
		return speedSlider;
	}

	/**
	 * Create the ComboBox to display and change simulations
	 * 
	 * @param resources
	 *            the ResourceBundle containing the properties file
	 * @return the ComboBox with preset simulation choices
	 */
	private ComboBox<String> createComboBox() {
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.getItems().addAll(
				myResources.getString("GameOfLifeSim"),
				myResources.getString("SegregationSim"),
				myResources.getString("PredatorPreySim"),
				myResources.getString("FireSim"));
		comboBox.setValue(myResources.getString("GameOfLifeSim")); // set default
		comboBox.setMinWidth(Integer.parseInt(myResources.getString("ComboBoxMinWidth")));
		comboBox.setLayoutX(Integer.parseInt(myResources.getString("ComboBoxXPos")));
		comboBox.setLayoutY(Integer.parseInt(myResources.getString("ComboBoxYPos")));
		comboBox.valueProperty().addListener(e -> myAnimation.resetSimulation());
		myAnimation.myComboBox = comboBox;
		return comboBox;
	}
	
	/**
	 * Set a specified event handler to a button
	 * 
	 * @param newButton the button to which an event handler should be set
	 */
	private void setButtonEventHandler(Button newButton, String buttonID) {
		if (buttonID.equals("PlayButton")) {
			newButton.setOnMouseClicked(e -> handlePlay(myTimeline));
		}
		if (buttonID.equals("StepButton")) {
			newButton.setOnMouseClicked(e -> handleStep(myTimeline));
		}
		if (buttonID.equals("PauseButton")) {
			newButton.setOnMouseClicked(e -> handlePause(myTimeline));
		}
		if (buttonID.equals("StopButton")) {
			newButton.setOnMouseClicked(e -> handleStop(myTimeline));
		}
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
	private Button createButton(String text) {
		Button newButton = new Button(myResources.getString(text));
		newButton.setId(myResources.getString(text));
		newButton.setMinWidth(Integer.parseInt(myResources.getString("ButtonMinWidth")));
		newButton.setLayoutX(Integer.parseInt(myResources.getString("ButtonXPos")));
		newButton.setLayoutY(Integer.parseInt(myResources.getString(text + "YPos")));
		setButtonEventHandler(newButton,text);
		return newButton;
	}

	/**
	 * Create the pane containing all of the simulation controls
	 * 
	 * @return the control pane
	 */
	public void addControls(Pane controls) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
		controls.setStyle("-fx-background-color: #98a2c5");
		controls.getChildren().addAll(createComboBox(),
				createButton("PlayButton"),
				createButton("StepButton"),
				createButton("PauseButton"),
				createButton("StopButton"),
				createSlider());
	}
}
