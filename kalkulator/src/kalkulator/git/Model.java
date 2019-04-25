package kalkulator.git;

import java.util.List;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

/**
 * Manages given data to return calculation result.
 * 
 * @author Grzegorz Norbert Rogozinski
 */

public class Model {

	private JShell jshell;
	private String value = "";

	public Model() {
		jshell = JShell.create();
	}

	/**
	 * Calculates value of an expression using JShell.
	 * 
	 * @param expression input string.
	 * @return value of the expression.
	 * @throws Exception if expression is constructed erroneously or result is out
	 *                   of bounds.
	 */
	public String calculate(String expression) throws ArithmeticException {

		List<SnippetEvent> events = jshell.eval(expression);
		SnippetEvent e = events.get(events.size() - 1);

		value = e.value();
		
		System.out.println(value);

		if (value == null || value.equals("NaN"))
			throw new ArithmeticException("Operation not allowed or invalid expression: " + expression);
		if (value.equals("Infinity"))
			throw new ArithmeticException("Out of bounds");
		return value;
	}

}
