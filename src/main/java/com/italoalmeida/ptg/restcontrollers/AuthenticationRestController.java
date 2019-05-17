package com.italoalmeida.ptg.restcontrollers;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.italoalmeida.ptg.models.User;
import com.italoalmeida.ptg.security.Credentials;
import com.italoalmeida.ptg.security.Token;
import com.italoalmeida.ptg.services.AuthenticationService;
import com.italoalmeida.ptg.services.UserService;

/**
 * @author Italo Almeida
 *
 */
@RestController
@RequestMapping(value = "/")
public class AuthenticationRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	Validator validator;
		
	@PostMapping("/signup")
	private ResponseEntity<Object> signup(@RequestBody User user) throws Exception {
		
		userService.save(user);
		
		Token token = authenticationService.generateToken(user);
		
		return ResponseEntity.ok().body(token);
	}
	
	@PostMapping("/signin")
	private ResponseEntity<Object> signin(@RequestBody Credentials credentials) throws Exception {
				
		Token token = authenticationService.authenticate(credentials);
		
		return ResponseEntity.ok().body(token);
	}

}
