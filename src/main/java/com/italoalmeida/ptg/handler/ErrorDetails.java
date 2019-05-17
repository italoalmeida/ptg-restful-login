package com.italoalmeida.ptg.handler;

import java.io.Serializable;

/**
 * @author Italo Almeida
 *
 */
public class ErrorDetails implements Serializable {

	private static final long serialVersionUID = 4575566687349763250L;
	
	private String message;
	
	private int errorCode;
	
	public ErrorDetails() {
		super();
	}

	public ErrorDetails(String message, int errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
