package kalkulator.git;

public class Model {

	public double calculate(double number1, double number2, String operator) {
		Double result = 0.0;

		switch (operator) {
		case "+":
			result = number1 + number2;
			break;
		case "-":
			result = number1 - number2;
			break;
		case "x":
			result = number1 * number2;
			break;
		case "/":
			result = number1 / number2;
			break;
		case "%":
			result = number2 / 100;
			break;
		case "square":
			result = number2 * number2;
			break;
		case "sqrt":
			result = Math.sqrt(number2);
			break;
		case "!":
			result = fac(number2);
			break;
		default:
			System.out.println("Unknown operator " + operator);
		}
		return result;
	}
	
	private double fac(double n) {
	    if (n <= 2) {
	        return n;
	    }
	    return n * fac(n - 1);
	}
}
