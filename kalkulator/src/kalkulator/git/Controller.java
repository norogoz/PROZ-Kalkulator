package kalkulator.git;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class Controller {
	@FXML
	private TextField output;
	
	private String operator = "";
	private long number1 = 0;
	private boolean start = true;

	private Model model = new Model();

	@FXML
	private void processNumpad(ActionEvent event) {
		if (start) {
			output.setText("");
			start = false;
		}
		String value = ((Button) event.getSource()).getText();
		output.setText(output.getText() + value);
	}

	@FXML
	private void processOperator(ActionEvent event) {
		String value = ((Button) event.getSource()).getText();
		
		if (!"=".equals(value)) {
			if (!operator.isEmpty())
				return;
			
			operator = value;
			number1 = Long.parseLong(output.getText());
			output.setText("");
		} else {
			if (operator.isEmpty())
				return;
			
			output.setText(String.valueOf(model.calculate(number1,  Long.parseLong(output.getText()), operator)));
			operator = "";
			start = true;
		}
		
	}
}
