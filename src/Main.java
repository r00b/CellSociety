import gui.Animation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	/**
	 * Open the window and initiate simulation screen
	 */
	@Override
	public void start(Stage stage) {
		stage.setResizable(false);
		Animation simulationGUI = new Animation();
		stage.setTitle(simulationGUI.getTitle());
		Scene simulation = simulationGUI.init();
		stage.setScene(simulation);
		stage.show();
	}
	
	/**
	 * Launch the game
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
