package com.italoalmeida.ptg.services;

import com.italoalmeida.ptg.models.User;
import com.italoalmeida.ptg.security.Credentials;
import com.italoalmeida.ptg.security.Token;

/**
 * @author Italo Almeida
 *
 */
public interface AuthenticationService {
	
	public Token generateToken(User user);
	
	public Token authenticate(Credentials credentials) throws Exception;

}
