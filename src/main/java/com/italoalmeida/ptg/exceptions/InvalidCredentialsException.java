package com.italoalmeida.ptg.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Italo Almeida
 *
 */
public class InvalidCredentialsException extends Exception {

	private static final long serialVersionUID = -2850603748351432613L;
	
	@Override
	public String getMessage() {
		return "Invalid e-mail or password";
	}
	
	public int getCode() {
		return HttpStatus.BAD_REQUEST.value();
	}
	
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
