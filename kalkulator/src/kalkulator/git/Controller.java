package kalkulator.git;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.*;

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

	private double number1 = 0;
	private double numbertmp = 0;
	private boolean clean = true;
	private DecimalFormat df = new DecimalFormat("#.######");

	private String operator = "";

	private Model model = new Model();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		output.setText("");

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
		mul.setOnAction(e -> setOperator("x"));
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
			number1 = 0;
			numbertmp = 0;
			operator = "";
			output.setText("");
		});
	}

	private void writeNumber(String num) {
		if (clean) {
			output.setText("");
			clean = false;
		}
		output.setText(output.getText() + num);
	}

	private void setOperator(String op) {
		if (output.getText().isEmpty())
			return;
		operator = op;
		number1 = Double.parseDouble(output.getText());
		clean = true;
	}

	private void getResult(String op) {
		if (output.getText().isEmpty())
			return;
		numbertmp = Double.parseDouble(output.getText());

		switch (op) {
		case "+":
		case "-":
		case "x":
		case "/":
		case "%":
		case "square":
		case "sqrt":
		case "!":
			output.setText(df.format(model.calculate(number1, numbertmp, op)));
			break;
		}

		clean = true;
		number1 = numbertmp;
		operator = "";
	}

}
