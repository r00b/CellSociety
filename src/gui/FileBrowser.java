package gui;

import java.io.File;
import java.util.ResourceBundle;

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

	private String buildPath(String XMLFileName, String simulationType) {
		if (simulationType.equals(myResources.getString("GameOfLifeSim"))) {
			XMLFileName = "GameOfLifeXMLs/" + XMLFileName;
		}
		else if (simulationType.equals(myResources.getString("SegregationSim"))) {
			XMLFileName = "SegregationXMLs/" + XMLFileName;
		}
		else if (simulationType.equals(myResources.getString("PredatorPreySim"))) {
			XMLFileName = "WatorXMLs/" + XMLFileName;
		}
		else if (simulationType.equals(myResources.getString("FireSim"))) {
			XMLFileName = "FireXMLs/" + XMLFileName;
		} else {
			XMLFileName = simulationType;
		}
		return myResources.getString("DefaultDataFolderPath") + XMLFileName;
	}
	
	protected String getXMLFileName(String simulationType) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose XML file");
		FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extentionFilter);
		String path = buildPath("",simulationType);
		File XMLPath = new File(path);
		fileChooser.setInitialDirectory(XMLPath);
		File chosenXMLFile = fileChooser.showOpenDialog(myStage);
		return buildPath(chosenXMLFile.getName(),simulationType);
	}
}
