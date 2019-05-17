package com.italoalmeida.ptg.restcontrollers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

/**
 * @author Italo Almeida
 *
 */
public class AuthenticationRestControllerTest extends RestControllerTest {
	
	final String SIGNUP = "/signup";

	final String SIGNIN = "/signin";
	
	private JSONObject user;
	
	private JSONObject phone;
	
	private JSONObject credentials;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Before
	public void setup() {
		user = new JSONObject();
		phone = new JSONObject();
		credentials = new JSONObject();
	}
	
	@Test
	public void signup_signedUpSuccessfully_shouldReturnToken() throws Exception {
		// given
		phone
		.put("number", 775555555)
		.put("areaCode", 83)
		.put("countryCode", "+55");
		
		user
		.put("firstName", "Java")
		.put("lastName", "Test")
		.put("email", "java@email.com")
		.put("password", "123456")
		.put("phones", new JSONArray().put(phone));
				
		// when
		JSONObject token = doPostAndReturnJson(SIGNUP, user.toString());
		
		// then
		error.checkThat(token, notNullValue());
		error.checkThat(token.has("token"), equalTo(true));
	}
	
	@Test
	public void signup_emailAlreadyExists_shouldReturnErrorMessage() throws Exception {
		// given
		phone
		.put("number", 775555555)
		.put("areaCode", 83)
		.put("countryCode", "+55");
		
		user
		.put("firstName", "Java")
		.put("lastName", "Test")
		.put("email", "java@email.com")
		.put("password", "123456")
		.put("phones", new JSONArray().put(phone));
		
		doPostAndReturnString(SIGNUP, user.toString());
		
		JSONObject otherUser = new JSONObject()
		.put("firstName", "Java")
		.put("lastName", "Test")
		// same email as first user's
		.put("email", "java@email.com")
		.put("password", "123456")
		.put("phones", new JSONArray().put(phone));
		
		// when
		String result = doPostAndReturnString(SIGNUP, otherUser.toString());
		
		// then
		assertThat(result, containsString("E-mail already exists"));
	}
	
	@Test
	public void signup_invalidFields_shouldReturnErrorMessage() throws Exception {
		// given
		phone
		.put("number", 775555555)
		.put("areaCode", 83)
		.put("countryCode", "+55");
		
		user
		.put("firstName", "Java")
		.put("lastName", "Test")
		// an invalid email
		.put("email", "invalid")
		.put("password", "123456")
		.put("phones", new JSONArray().put(phone));
		
		// when
		String result = doPostAndReturnString(SIGNUP, user.toString());
		
		// then
		assertThat(result, containsString("Invalid fields"));
	}
	
	@Test
	public void signup_missingFields_shouldReturnErrorMessage() throws Exception {
		// given
		phone
		.put("number", 775555555)
		.put("areaCode", 83)
		.put("countryCode", "+55");
		
		user
		// missing first name field
		.put("lastName", "Test")
		.put("email", "email1@email.com")
		.put("password", "123456")
		.put("phones", new JSONArray().put(phone));
		
		// when
		String result = doPostAndReturnString(SIGNUP, user.toString());
		
		// then
		assertThat(result, containsString("Missing fields"));
	}
	
	@Test
	public void signin_signedInSuccessfully_shouldReturnToken() throws Exception {
		// given
		phone
		.put("number", 775555555)
		.put("areaCode", 83)
		.put("countryCode", "+55");
		
		user
		.put("firstName", "Java")
		.put("lastName", "Test")
		.put("email", "java@email.com")
		.put("password", "123456")
		.put("phones", new JSONArray().put(phone));
		
		doPostAndReturnString(SIGNUP, user.toString());
		
		credentials
		.put("email", "java@email.com")
		.put("password", "123456");
		
		// when
		JSONObject token = doPostAndReturnJson(SIGNIN, credentials.toString());
		
		// then
		error.checkThat(token, notNullValue());
		error.checkThat(token.has("token"), equalTo(true));
	}
	
	@Test
	public void signin_nonexistentEmailOrWrongPassword_shouldReturnErrorMessage() throws Exception {
		// given
		credentials
		// nonexistent email
		.put("email", "jose@email.com")
		.put("password", "123456");
		
		// when
		String result = doPostAndReturnString(SIGNIN, credentials.toString());
		
		// then
		assertThat(result, containsString("Invalid e-mail or password"));
	}
	
	@Test
	public void signin_missingFields_shouldReturnErrorMessage() throws Exception {
		// given
		credentials
		.put("email", "joao@email.com");
		// missing password field
		
		// when
		String result = doPostAndReturnString(SIGNIN, credentials.toString());
		
		// then
		assertThat(result, containsString("Missing fields"));
	}

}
