import gui.Animation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	
	
	/**
	 * Open the window and initiate simulation screen
	 */
	@Override
	public void start(Stage s) {
		s.setResizable(false);
		
		
		s.setResizable(false);
		s.setTitle("TEST");
		
		Animation a = new Animation();
		Scene simulation = a.init(WIDTH,HEIGHT);
	//	intro.setOnKeyPressed(e -> handleInput(e, s));
		s.setScene(simulation);
		s.show();
		
	}
	
	/**
	 * Launch the game
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
