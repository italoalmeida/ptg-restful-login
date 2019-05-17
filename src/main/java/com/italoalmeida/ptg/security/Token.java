package com.italoalmeida.ptg.security;

import java.io.Serializable;

/**
 * @author Italo Almeida
 *
 */
public class Token implements Serializable {

	private static final long serialVersionUID = -8690074873566162342L;
	
	private String token;
	
	public Token() {
		super();
	}

	public Token(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
