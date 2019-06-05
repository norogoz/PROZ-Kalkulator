module kalkulator {
	exports kalkulator.git;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires jdk.jshell;
	requires java.ws.rs;
	requires java.scripting;
	requires gson;

	opens kalkulator.git to javafx.fxml;
}