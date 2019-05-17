package com.italoalmeida.ptg.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Italo Almeida
 *
 */
public class EmailAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -7324811351491882884L;
	
	@Override
	public String getMessage() {
		return "E-mail already exists";
	}
	
	public int getCode() {
		return HttpStatus.BAD_REQUEST.value();
	}
	
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
