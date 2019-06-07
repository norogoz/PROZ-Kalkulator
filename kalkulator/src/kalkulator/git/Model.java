package kalkulator.git;

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

	// private String value; //used in GET request
	private Str value; // used in POST request JSON binding
	private Client client;
	private WebTarget webTarget;
	private Gson g;

	public Model() {
		client = ClientBuilder.newClient();
		g = new Gson();
	}

	/**
	 * Calculates value of an expression with HTML POST request.
	 * 
	 * @param expression to calculate.
	 * @return value of the expression as a String.
	 * @throws Exception if expression is constructed erroneously or result is out
	 *                   of bounds.
	 */
	public String calculate(String expression) throws ArithmeticException {
		// GET request version
		/*
		webTarget = client.target("http://localhost:8080/docker/calculate/"
				+ expression.replaceAll("[+]", "%2B").replaceAll("[/]", "%2F"));
		value = webTarget.request(MediaType.TEXT_PLAIN).get(String.class);
		*/

		webTarget = client.target("http://localhost:8080/docker/calculate/post");
		value = g.fromJson(
				webTarget.request(MediaType.APPLICATION_JSON).post(
						Entity.entity("{\"str\":\"" + expression + "\"}", MediaType.APPLICATION_JSON), String.class),
				Str.class);

		if (value == null || value.getString().equals("NaN"))
			throw new ArithmeticException("Operation not allowed or invalid expression: " + expression);
		if (value.getString().equals("Infinity"))
			throw new ArithmeticException("Out of bounds");

		System.out.println(expression + "=" + value.getString());
		return value.getString();
	}

}
