package kalkulator.git;

import java.net.URL;
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
	private Button sign;
	@FXML
	private Button equals;
	@FXML
	private Button erase;
	@FXML
	private Button ac;

	private long number1 = 0;
	private String operator = "";
	private boolean clean = true;
	private Model model = new Model();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		output.setEditable(false);
		output.setText("");

		zero.setOnAction(e -> {
			if (clean) {
				output.setText("");
				clean = false;
			}
			output.setText(output.getText() + 0);
		});

		one.setOnAction(e -> {
			if (clean) {
				output.setText("");
				clean = false;
			}
			output.setText(output.getText() + 1);

		});
		two.setOnAction(e -> {
			if (clean) {
				output.setText("");
				clean = false;
			}
			output.setText(output.getText() + 2);

		});
		three.setOnAction(e -> {
			if (clean) {
				output.setText("");
				clean = false;
			}
			output.setText(output.getText() + 3);

		});
		four.setOnAction(e -> {
			if (clean) {
				output.setText("");
				clean = false;
			}
			output.setText(output.getText() + 4);
		});
		five.setOnAction(e -> {
			if (clean) {
				output.setText("");
				clean = false;
			}
			output.setText(output.getText() + 5);
		});
		six.setOnAction(e -> {
			if (clean) {
				output.setText("");
				clean = false;
			}
			output.setText(output.getText() + 6);

		});
		seven.setOnAction(e -> {
			if (clean) {
				output.setText("");
				clean = false;
			}
			output.setText(output.getText() + 7);

		});
		eight.setOnAction(e -> {
			if (clean) {
				output.setText("");
				clean = false;
			}
			output.setText(output.getText() + 8);

		});
		nine.setOnAction(e -> {
			if (clean) {
				output.setText("");
				clean = false;
			}
			output.setText(output.getText() + 9);

		});
		point.setOnAction(e -> {
			if (clean)
				return;
			output.setText(output.getText() + ".");
		});
		plus.setOnAction(e -> {
			operator = "+";
			number1 = Long.parseLong(output.getText());
			clean = true;
		});
		minus.setOnAction(e -> {
			operator = "-";
			number1 = Long.parseLong(output.getText());
			clean = true;
		});
		mul.setOnAction(e -> {
			operator = "x";
			number1 = Long.parseLong(output.getText());
			clean = true;
		});
		div.setOnAction(e -> {
			operator = "/";
			number1 = Long.parseLong(output.getText());
			clean = true;
		});
		equals.setOnAction(e -> {
			if (!operator.isEmpty() && !output.getText().isEmpty()) {
				output.setText(String.valueOf(model.calculate(number1, Long.parseLong(output.getText()), operator)));
				clean = true;
				operator = "";
			}
		});

		erase.setOnAction(e -> {
			if (!output.getText().isEmpty())
				output.setText(output.getText().substring(0, output.getLength() - 1));
		});
	}

}
