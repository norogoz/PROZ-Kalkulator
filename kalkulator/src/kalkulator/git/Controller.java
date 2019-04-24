package kalkulator.git;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

/**
 * Allows for user input, parses it and shows alerts
 * 
 * @author Grzegorz Norbert Rogozinski
 */

public class Controller implements Initializable {

	@FXML
	private TextField output;

	@FXML
	private Button zero;
	@FXML
	private Button one;
	@FXML
	private Button two;
	@FXML
	private Button three;
	@FXML
	private Button four;
	@FXML
	private Button five;
	@FXML
	private Button six;
	@FXML
	private Button seven;
	@FXML
	private Button eight;
	@FXML
	private Button nine;
	@FXML
	private Button point;
	@FXML
	private Button plus;
	@FXML
	private Button minus;
	@FXML
	private Button mul;
	@FXML
	private Button div;
	@FXML
	private Button fac;
	@FXML
	private Button sqrt;
	@FXML
	private Button square;
	@FXML
	private Button percent;
	@FXML
	private Button equals;
	@FXML
	private Button sign;
	@FXML
	private Button erase;
	@FXML
	private Button ac;

	private String num1 = "";
	private String num2 = "";
	private String operator = "";
	private String expression = "";
	private boolean clean = true;

	private Model model = new Model();
	private Alert alert = new Alert(AlertType.ERROR);

	/**
	 * Called to initialize a controller after its root element has been completely
	 * processed.
	 * 
	 * @param location  The location used to resolve relative paths for the root
	 *                  object, or null if the location is not known.
	 * @param resources The resources used to localize the root object, or null if
	 *                  the root object was not localized.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		output.setText("");
		alert.setHeaderText(null);

		zero.setOnAction(e -> writeNumber("0"));
		one.setOnAction(e -> writeNumber("1"));
		two.setOnAction(e -> writeNumber("2"));
		three.setOnAction(e -> writeNumber("3"));
		four.setOnAction(e -> writeNumber("4"));
		five.setOnAction(e -> writeNumber("5"));
		six.setOnAction(e -> writeNumber("6"));
		seven.setOnAction(e -> writeNumber("7"));
		eight.setOnAction(e -> writeNumber("8"));
		nine.setOnAction(e -> writeNumber("9"));
		point.setOnAction(e -> writeNumber("."));

		plus.setOnAction(e -> setOperator("+"));
		minus.setOnAction(e -> setOperator("-"));
		mul.setOnAction(e -> setOperator("*"));
		div.setOnAction(e -> setOperator("/"));

		equals.setOnAction(e -> getResult(operator));
		percent.setOnAction(e -> getResult("%"));
		square.setOnAction(e -> getResult("square"));
		sqrt.setOnAction(e -> getResult("sqrt"));
		fac.setOnAction(e -> getResult("!"));

		sign.setOnAction(e -> {
			if (clean) {
				output.setText("");
				clean = false;
			}
			if (!output.getText().isEmpty() && "-".equals(output.getText().substring(0, 1))) {
				output.setText(output.getText().substring(1, output.getLength()));
			} else
				output.setText("-" + output.getText());
		});

		erase.setOnAction(e -> {
			if (!output.getText().isEmpty())
				output.setText(output.getText().substring(0, output.getLength() - 1));
			operator = "";
			clean = false;
		});

		ac.setOnAction(e -> {
			num1 = "";
			num2 = "";
			operator = "";
			output.setText("");
		});
	}

	/**
	 * Outputs given character to TextField output.
	 * 
	 * @param num character to write
	 */
	private void writeNumber(String num) {
		if (num.equals(".") && output.getText().contains("."))
			return;
		if (clean) {
			output.setText("");
			clean = false;
		}
		output.setText(output.getText() + num);
	}

	/**
	 * Stores given String in String operator for later use.
	 * 
	 * @param op String to store.
	 */
	private void setOperator(String op) {
		if (output.getText().isEmpty())
			return;
		operator = op;
		num1 = output.getText();
		clean = true;
	}

	/**
	 * Parses expression and uses Model to calculate its value, shows alert if
	 * exception is caught.
	 * 
	 * @param op operator used in calculations.
	 */
	private void getResult(String op) {
		if (output.getText().isEmpty())
			return;
		num2 = output.getText();

		try {
			switch (op) {
			case "+":
			case "-":
			case "*":
			case "/":
				output.setText(model.calculate(num1 + "d" + op + num2 + "d"));
				break;
			case "%":
				output.setText(model.calculate(num2 + "d" + "/" + 100d));
				break;
			case "square":
				output.setText(model.calculate(num2 + "d*" + num2 + "d"));
				break;
			case "sqrt":
				output.setText(model.calculate("Math.sqrt(" + num2 + "d)"));
				break;
			case "!":
				expression = "1d";
				for (int i = Integer.parseUnsignedInt(num2); i > 1; --i) {
					expression += "*" + i + "d";
				}
				output.setText(model.calculate(expression));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			output.setText("Error");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

		clean = true;
		num1 = num2;
		operator = "";
	}

}
