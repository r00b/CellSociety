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

	private String buildPath(String directory, String simulationType) {
		String simXMLFolder = "XMLs";
		if (simulationType.equals(myResources.getString("GameOfLifeSim"))) {
			simXMLFolder = "GameOfLife" + simXMLFolder;
		}
		if (simulationType.equals(myResources.getString("SegregationSim"))) {
			simXMLFolder = "Segregation" + simXMLFolder;
		}
		if (simulationType.equals(myResources.getString("PredatorPreySim"))) {
			simXMLFolder = "Wator" + simXMLFolder;
		}
		if (simulationType.equals(myResources.getString("FireSim")))
			simXMLFolder = "Fire" + simXMLFolder;
		return directory + simXMLFolder;
	}
	
	protected String getXMLFileName(String simulationType) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose XML file");
		FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extentionFilter);
		String path = buildPath(myResources.getString("DefaultDataFolderPath"),simulationType);
		File userDirectory = new File(path);
	fileChooser.setInitialDirectory(userDirectory);

		
		
		fileChooser.showOpenDialog(myStage);
		
		
		
		
		
		return "S";
	}
}
