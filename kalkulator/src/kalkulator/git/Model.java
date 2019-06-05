package kalkulator.git;

//import java.util.List;
//import jdk.jshell.JShell;
//import jdk.jshell.SnippetEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

/**
 * Manages given data to return calculation result.
 * 
 * @author Grzegorz Norbert Rogozinski
 */

public class Model {

	// private JShell jshell;
	//private String value;
	private Str value;
	private Client client;
	private WebTarget webTarget;
	private Gson g;

	public Model() {
		// jshell = JShell.create();
		client = ClientBuilder.newClient();
		g = new Gson();
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

		// List<SnippetEvent> events = jshell.eval(expression);
		// SnippetEvent e = events.get(events.size() - 1);
		// value = e.value();

		// webTarget = client.target("http://localhost:8080/docker/calculate/" +
		// expression.replaceAll("[+]","%2B").replaceAll("[/]","%2F"));
		// value = webTarget.request(MediaType.TEXT_PLAIN).get(String.class);

		webTarget = client.target("http://localhost:8080/docker/calculate/post");
		value = g.fromJson(
				webTarget.request(MediaType.APPLICATION_JSON).post(
						Entity.entity("{\"str\":\"" + expression + "\"}", MediaType.APPLICATION_JSON), String.class),
				Str.class);

		System.out.println(value);

		if (value == null || value.getString().equals("NaN"))
			throw new ArithmeticException("Operation not allowed or invalid expression: " + expression);
		if (value.getString().equals("Infinity"))
			throw new ArithmeticException("Out of bounds");

		return value.getString();
	}

}
