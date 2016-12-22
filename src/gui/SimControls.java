package gui;

import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Robert H. Steilberg II | rhs16
 *         <p>
 *         The SimControls class creates each GUI element used to control the
 *         simulation. The elements are drawn to a Pane which is then returned
 *         to the superclass for adding to the overall Scene. In this class, a
 *         combo box, play button, step button, pause button, stop & reset
 *         button, and speed slider are created and added to the GUI. The
 *         SimControls class also adds event handlers to each of these objects
 *         that give them their functionality. The actions associated with these
 *         event handlers are defined in the SimEvents class.
 *         <p>
 *         Dependencies: Animation.java, SimEvents.java
 */
public class SimControls {
    private Timeline myTimeline;
    private Animation myAnimation;
    private ResourceBundle myResources;

    SimControls(Animation currAnimation, Timeline timeline, ResourceBundle resources) {
        myAnimation = currAnimation;
        myTimeline = timeline;
        myResources = resources;
    }

    /**
     * Create a slider with a label to specify the speed of the simulation step
     *
     * @return a VBox containing the slider along with its label
     */
    private VBox createSlider(SimEvents events) {
        Slider speedSlider = new Slider();
        speedSlider.setId("slider");
        speedSlider.setShowTickMarks(true);
        speedSlider.setValue(Integer.parseInt(myResources.getString("SliderDefaultValue")));
        speedSlider.setOnMouseDragged(e -> events.handleSlider(speedSlider));
        speedSlider.setOnKeyPressed(e -> events.handleSlider(speedSlider));
        Text sliderText = new Text(myResources.getString("SpeedSliderText"));
        VBox sliderBox = new VBox(Integer.parseInt(myResources.getString("SpeedSliderSpacing")));
        sliderBox.setLayoutX(Integer.parseInt(myResources.getString("SliderXPos")));
        sliderBox.setLayoutY(Integer.parseInt(myResources.getString("SliderYPos")));
        sliderBox.getChildren().add(sliderText);
        sliderBox.getChildren().add(speedSlider);
        sliderBox.setAlignment(Pos.CENTER);
        return sliderBox;
    }

    /**
     * Create the ComboBox used to display and change simulations
     *
     * @return the ComboBox with preset simulation choices
     */
    private ComboBox<String> createComboBox(SimEvents events) {
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.getStyleClass().add("simControl");
        comboBox.getItems().addAll(myResources.getString("GameOfLifeSim"), myResources.getString("SegregationSim"),
                myResources.getString("WatorSim"), myResources.getString("FireSim"),
                myResources.getString("ForagingAntsSim"));
        // set default simulation as defined in properties file
        comboBox.setValue(myResources.getString("DefaultSimulation"));
        comboBox.setMinWidth(Integer.parseInt(myResources.getString("ComboBoxMinWidth")));
        comboBox.setLayoutX(Integer.parseInt(myResources.getString("ComboBoxXPos")));
        comboBox.setLayoutY(Integer.parseInt(myResources.getString("ComboBoxYPos")));
        // pass true because we are changing simulation and need an input file
        comboBox.valueProperty().addListener(e -> myAnimation.resetSimulation(myTimeline, true));
        // instantiated ComboBox in Animation.java so it can be referenced later
        myAnimation.myComboBox = comboBox;
        return comboBox;
    }

    /**
     * Set a specified event handler to a button
     *
     * @param newButton the button to which an event handler should be set
     * @param buttonID  a String corresponding to the button's type
     */
    private void setButtonEventHandler(Button newButton, String buttonID, SimEvents events) {
        if (buttonID.equals("PlayButton")) {
            newButton.setOnMouseClicked(e -> events.handlePlay());
        }
        if (buttonID.equals("StepButton")) {
            newButton.setOnMouseClicked(e -> events.handleStep());
        }
        if (buttonID.equals("PauseButton")) {
            newButton.setOnMouseClicked(e -> events.handlePause());
        }
        if (buttonID.equals("StopButton")) {
            newButton.setOnMouseClicked(e -> events.handleStop());
        }
        if (buttonID.equals("XMLResetButton")) {
            newButton.setOnMouseClicked(e -> events.handleXMLFileChange());
        }
    }

    /**
     * Create a specified button to signal some simulation change
     *
     * @param text The String to be displayed on the button
     * @return the button
     */
    private Button createButton(String text, SimEvents events) {
        Button newButton = new Button(myResources.getString(text));
        newButton.getStyleClass().add("simControl");
        newButton.setMinWidth(Integer.parseInt(myResources.getString("ButtonMinWidth")));
        newButton.setLayoutX(Integer.parseInt(myResources.getString("ButtonXPos")));
        newButton.setLayoutY(Integer.parseInt(myResources.getString(text + "YPos")));
        setButtonEventHandler(newButton, text, events);
        return newButton;
    }

    /**
     * Create the pane containing all of the simulation controls
     *
     * @param controls the pane onto which controls will be placed
     * @return the control pane
     */
    protected void addControls(Pane controls) {
        SimEvents events = new SimEvents(myAnimation, myTimeline, myResources);
        controls.setId("pane"); // CSS connection
        controls.getChildren().addAll(createComboBox(events), createButton("PlayButton", events),
                createButton("StepButton", events), createButton("PauseButton", events),
                createButton("StopButton", events), createButton("XMLResetButton", events), createSlider(events));
    }
}
