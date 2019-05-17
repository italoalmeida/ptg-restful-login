package com.italoalmeida.ptg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.italoalmeida.ptg.validators.TokenValidator;

/**
 * @author Italo Almeida
 *
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	@Autowired
	TokenValidator tokenValidator;
	
	@Override
	public void authorize(String token) throws Exception {
		tokenValidator.validate(token);
	}

}
