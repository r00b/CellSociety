package gui;

import java.io.File;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Robert H. Steilberg II | rhs16
 * 
 *         The FileBrowser class handles selecting an XML file for the
 *         simulation to set parameters with.
 *
 */
public class FileBrowser {
	private Stage myStage;
	private ResourceBundle myResources;

	FileBrowser(Stage stage, ResourceBundle resources) {
		myStage = stage;
		myResources = resources;
	}

	/**
	 * Display an error message to the user if an XML file was not specified
	 */
	private void diplayMissingXMLFileAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(myResources.getString("XMLMissingErrorTitle"));
		alert.setHeaderText(myResources.getString("XMLMissingErrorHeader"));
		alert.setContentText(myResources.getString("XMLMissingErrorText"));
		alert.showAndWait();

	}

	/**
	 * Build a path to the specified XML file that can be plugged into the
	 * simulation classes
	 * 
	 * @param XMLFileName
	 *            the name of the XML file
	 * @param simulationType
	 *            the type of simulation
	 * @return a String designating the path to the chosen XML file
	 */
	private String buildPath(String XMLFileName, String simulationType) {
		if (simulationType.equals(myResources.getString("GameOfLifeSim"))) {
			XMLFileName = "GameOfLifeXMLs/" + XMLFileName;
		}
		if (simulationType.equals(myResources.getString("SegregationSim"))) {
			XMLFileName = "SegregationXMLs/" + XMLFileName;
		}
		if (simulationType.equals(myResources.getString("PredatorPreySim"))) {
			XMLFileName = "WatorXMLs/" + XMLFileName;
		}
		if (simulationType.equals(myResources.getString("FireSim"))) {
			XMLFileName = "FireXMLs/" + XMLFileName;
		}
		return myResources.getString("DefaultDataFolderPath") + XMLFileName;
	}

	/**
	 * Prompt the user to select an XML file for simulation input via a JavaFX
	 * FileChooser
	 * 
	 * @param simulationType
	 *            the type of simulation
	 * @return a String designating the path to the chosen XML file
	 */
	protected String getXMLFileName(String simulationType) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose XML file");
		FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extentionFilter);
		String path = buildPath("", simulationType);
		File XMLPath = new File(path);
		fileChooser.setInitialDirectory(XMLPath);
		File chosenXMLFile = fileChooser.showOpenDialog(myStage);
		if (chosenXMLFile == null) {
			diplayMissingXMLFileAlert();
			return getXMLFileName(simulationType);
		}
		return buildPath(chosenXMLFile.getName(), simulationType);
	}

}
