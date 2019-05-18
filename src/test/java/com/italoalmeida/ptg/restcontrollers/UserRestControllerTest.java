package com.italoalmeida.ptg.restcontrollers;

import static java.lang.Thread.sleep;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

/**
 * @author Italo Almeida
 *
 */
public class UserRestControllerTest extends RestControllerTest {
	
	final String ME = "/me";
	
	final String SIGNUP = "/signup";

	private HttpHeaders headers;
	
	@Value("${jwt.expiration}")
	private Long tokenExpiration;
		
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Before
	public void setup() {
		headers = new HttpHeaders();
	}
	
	@Test
	public void me_authenticatedSucessfully_shouldReturnUserDetails() throws Exception {
		// given
		JSONObject phone = new JSONObject()
		.put("number", 775555555)
		.put("area_code", 83)
		.put("country_code", "+55");
		
		JSONObject user = new JSONObject()
		.put("firstName", "Java")
		.put("lastName", "Test")
		.put("email", "java@email.com")
		.put("password", "123456")
		.put("phones", new JSONArray().put(phone));

		// Get token and assign it in request header
		JSONObject token = doPostAndReturnJson(SIGNUP, user.toString());
		headers.add("Authorization", token.getString("token"));
				
		// when
		JSONObject result = doGetAndReturnJson(ME, headers);
		
		// then
		error.checkThat(result.has("firstName"), equalTo(true));
		error.checkThat(result.has("firstName"), equalTo(true));
		error.checkThat(result.has("lastName"), equalTo(true));
		error.checkThat(result.has("email"), equalTo(true));
		error.checkThat(result.has("phones"), equalTo(true));
		error.checkThat(result.has("created_at"), equalTo(true));
		error.checkThat(result.has("last_login"), equalTo(true));
	}
	
	@Test
	public void me_notSentToken_shouldReturnErrorMessage() throws Exception {
		// given empty headers
		
		// when
		JSONObject result = doGetAndReturnJson(ME, headers);
		
		// then
		assertThat(result.toString(), containsString("Unauthorized"));
	}
	
	@Test
	public void me_expiredToken_shouldReturnErrorMessage() throws Exception {
		// given
		JSONObject phone = new JSONObject()
		.put("number", 775555555)
		.put("area_code", 83)
		.put("country_code", "+55");
		
		JSONObject user = new JSONObject()
		.put("firstName", "Java")
		.put("lastName", "Test")
		.put("email", "java@email.com")
		.put("password", "123456")
		.put("phones", new JSONArray().put(phone));

		// Get token and assign it in request header
		JSONObject token = doPostAndReturnJson(SIGNUP, user.toString());
		headers.add("Authorization", token.getString("token"));
		
		sleep(tokenExpiration);
				
		// when
		JSONObject result = doGetAndReturnJson(ME, headers);
		
		// then
		assertThat(result.toString(), containsString("Unauthorized - invalid session"));
	}

}
