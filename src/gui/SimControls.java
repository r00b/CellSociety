package gui;

import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * 
 * @author Robert H. Steilberg II | rhs16
 * 
 *         The SimControls class creates each GUI element used to control the
 *         simulation. The elements are drawn to a Pane which is then returned
 *         to the superclass for adding to the overall Scene. A ComboBox is
 *         added that changes the simulation to another specified simulation; a
 *         play button is added that plays the simulation; a step button is
 *         added that steps through individual frames of the simulation; a pause
 *         button is added that pauses the simulation; a stop & reset button is
 *         added that stops the simulation and resets it to its initial state; a
 *         slider is added that controls the stepping speed of the simulation.
 *         The SimControls class also adds event handlers to each of these
 *         objects that give them their functionality.
 * 
 *         Dependencies: Animation.java
 */
public class SimControls extends Animation {
	private Timeline myTimeline;
	private Animation mySimulation;
	private ResourceBundle myResources;

	SimControls(Animation currAnimation, Timeline timeline, ResourceBundle resources) {
		mySimulation = currAnimation;
		myTimeline = timeline;
		myResources = resources;
	}

	/**
	 * Changes the speed of a step function according to a value given by a
	 * slider
	 * 
	 * @param speedSlider
	 *            is the slider object containing the speed value
	 */
	private void handleSlider(Slider speedSlider) {
		double framesPerSecond = speedSlider.getValue() / 5;
		double millisecondDelay = 1000 / framesPerSecond;
		double secondDelay = 1.0 / framesPerSecond;
		myTimeline.stop(); // stop the current run
		myTimeline.getKeyFrames().clear(); // clear out the old frame
		KeyFrame frame = new KeyFrame(Duration.millis(millisecondDelay), e -> mySimulation.step(secondDelay));
		myTimeline.getKeyFrames().add(frame);
		myTimeline.play();
	}

	/**
	 * Handle click to stop button by stopping the simulation
	 */
	private void handleStop() {
		mySimulation.resetSimulation(myTimeline);
	}

	/**
	 * Handle click to pause button by pausing the simulation
	 */
	private void handlePause() {
		myTimeline.pause();
	}

	/**
	 * Handle click to step button by stepping through one cycle of the
	 * simulation
	 */
	private void handleStep() {
		myTimeline.pause(); // in case we are already playing
		mySimulation.step(1000 / Integer.parseInt(myResources.getString("DefaultFPS")));
	}

	/**
	 * Handle click to play button by starting the simulation
	 */
	private void handlePlay() {
		myTimeline.play();
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
	 * Create the ComboBox used to display and change simulations
	 * 
	 * @return the ComboBox with preset simulation choices
	 */
	private ComboBox<String> createComboBox() {
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.getItems().addAll(myResources.getString("GameOfLifeSim"), myResources.getString("SegregationSim"),
				myResources.getString("PredatorPreySim"), myResources.getString("FireSim"));
		// set Game of Life as the default simulation
		comboBox.setValue(myResources.getString("GameOfLifeSim"));
		comboBox.setMinWidth(Integer.parseInt(myResources.getString("ComboBoxMinWidth")));
		comboBox.setLayoutX(Integer.parseInt(myResources.getString("ComboBoxXPos")));
		comboBox.setLayoutY(Integer.parseInt(myResources.getString("ComboBoxYPos")));
		comboBox.valueProperty().addListener(e -> mySimulation.resetSimulation(myTimeline));
		mySimulation.myComboBox = comboBox;
		return comboBox;
	}

	/**
	 * Set a specified event handler to a button
	 * 
	 * @param newButton
	 *            the button to which an event handler should be set
	 * 
	 * @param buttonID
	 *            a String corresponding to the button's type
	 */
	private void setButtonEventHandler(Button newButton, String buttonID) {
		if (buttonID.equals("PlayButton")) {
			newButton.setOnMouseClicked(e -> handlePlay());
		}
		if (buttonID.equals("StepButton")) {
			newButton.setOnMouseClicked(e -> handleStep());
		}
		if (buttonID.equals("PauseButton")) {
			newButton.setOnMouseClicked(e -> handlePause());
		}
		if (buttonID.equals("StopButton")) {
			newButton.setOnMouseClicked(e -> handleStop());
		}
	}

	/**
	 * Create a specified button to signal some simulation change
	 * 
	 * @param text
	 *            The String to be displayed on the button
	 * @return the button
	 */
	private Button createButton(String text) {
		Button newButton = new Button(myResources.getString(text));
		newButton.setId(myResources.getString(text));
		newButton.setMinWidth(Integer.parseInt(myResources.getString("ButtonMinWidth")));
		newButton.setLayoutX(Integer.parseInt(myResources.getString("ButtonXPos")));
		newButton.setLayoutY(Integer.parseInt(myResources.getString(text + "YPos")));
		setButtonEventHandler(newButton, text);
		return newButton;
	}

	/**
	 * Create the pane containing all of the simulation controls
	 * 
	 * @param controls
	 *            the pane onto which controls will be placed
	 * 
	 * @return the control pane
	 */
	protected void addControls(Pane controls) {
		// access properties file
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
		// set background of GUI
		controls.setStyle("-fx-background-color: #98a2c5");
		controls.getChildren().addAll(createComboBox(), createButton("PlayButton"), createButton("StepButton"),
				createButton("PauseButton"), createButton("StopButton"), createSlider());
	}
}
