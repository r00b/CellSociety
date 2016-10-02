package gui;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import xml.XMLParser;
import xml.XMLParserException;

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
	 * Display an error message to the user when there is a problem with XML
	 * file input
	 */
	private void displayXMLAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();

	}

	/**
	 * Test a specified XML file for validity
	 * 
	 * @param pathToFile
	 *            a String representing the path to the XML file to be tested
	 * @return true if the XML file is valid, false otherwise
	 */
	@SuppressWarnings("unused")
	private boolean validXMLFile(String pathToFile) {
		try {
			// generically load in an XML file to see if it throws any errors
			XMLParser testParser = new XMLParser(pathToFile);
		} catch (XMLParserException e) {
			displayXMLAlert(myResources.getString("XMLInvalidErrorTitle"),
					myResources.getString("XMLInvalidErrorHeader"), myResources.getString("XMLInvalidErrorContent"));
			return false;
		}
		return true;
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
		if (simulationType.equals(myResources.getString("AntSim"))) {
			XMLFileName = "ForagingAntsXMLs/" + XMLFileName;
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
		// "" because we are specifying a directory, not a file
		String path = buildPath("", simulationType);
		File XMLPath = new File(path);
		fileChooser.setInitialDirectory(XMLPath);
		File chosenXMLFile = fileChooser.showOpenDialog(myStage);
		if (chosenXMLFile == null) {
			displayXMLAlert(myResources.getString("XMLMissingErrorTitle"),
					myResources.getString("XMLMissingErrorHeader"), myResources.getString("XMLMissingErrorContent"));
			return getXMLFileName(simulationType); // try again
		}
		String pathToChosenXML = buildPath(chosenXMLFile.getName(), simulationType);
		if (validXMLFile(pathToChosenXML)) {
			return pathToChosenXML;
		} else { // try again
			return getXMLFileName(simulationType); // try again
		}
	}

}
