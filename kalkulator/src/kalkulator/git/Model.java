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
	public String calculate(String expression) throws Exception {

		List<SnippetEvent> events = jshell.eval(expression);
		SnippetEvent e = events.get(events.size() - 1);

		System.out.println(e.value());

		if (e.value() == null || e.value().equals("NaN"))
			throw new Exception("Wrong expression: " + expression);
		if (e.value().equals("Infinity"))
			throw new Exception("Out of bounds");
		return e.value();
	}

}
