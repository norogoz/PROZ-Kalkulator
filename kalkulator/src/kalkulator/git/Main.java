package kalkulator.git;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

/**
 * Sets up stage and key listening.
 * 
 * @author Grzegorz Norbert Rogozinski
 *
 */

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("application.fxml"));

		Controller controller = new Controller();
		loader.setController(controller);

		Parent root = loader.load();
		Scene scene = new Scene(root);

		scene.setOnKeyPressed(e -> controller.keyPressed(e));

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Kalkulator");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
