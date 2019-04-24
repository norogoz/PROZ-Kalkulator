package kalkulator.git;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

/**
 * Main class being 'view' component of MVC pattern.
 * 
 * @author Grzegorz Norbert Rogozinski
 *
 */

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("application.fxml"));
		loader.setController(new Controller());

		Parent root = loader.load();

		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Kalkulator");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
