package com.italoalmeida.ptg.services;

/**
 * @author Italo Almeida
 *
 */
public interface AuthorizationService {
	
	public void authorize(String token) throws Exception;

}
