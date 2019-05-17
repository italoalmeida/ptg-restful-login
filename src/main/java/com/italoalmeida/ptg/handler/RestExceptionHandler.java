package com.italoalmeida.ptg.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.italoalmeida.ptg.exceptions.EmailAlreadyExistsException;
import com.italoalmeida.ptg.exceptions.InvalidCredentialsException;
import com.italoalmeida.ptg.exceptions.InvalidFieldsException;
import com.italoalmeida.ptg.exceptions.MissingFieldsException;
import com.italoalmeida.ptg.exceptions.UnauthorizedException;

/**
 * @author Italo Almeida
 *
 */
@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleException(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorDetails("Sorry! An unexpected error occurs. Please, refresh your page or contact us if the problem persists.", 
						HttpStatus.INTERNAL_SERVER_ERROR.value()));
	}
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails("Invalid JSON", HttpStatus.BAD_REQUEST.value()));
	}

	@ExceptionHandler(value = EmailAlreadyExistsException.class)
	public ResponseEntity<Object> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
		return ResponseEntity.status(e.getHttpStatus()).body(new ErrorDetails(e.getMessage(), e.getCode()));
	}

	@ExceptionHandler(value = InvalidFieldsException.class)
	public ResponseEntity<Object> handleInvalidFieldsException(InvalidFieldsException e) {
		return ResponseEntity.status(e.getHttpStatus()).body(new ErrorDetails(e.getMessage(), e.getCode()));
	}

	@ExceptionHandler(value = MissingFieldsException.class)
	public ResponseEntity<Object> handleMissingFieldsException(MissingFieldsException e) {
		return ResponseEntity.status(e.getHttpStatus()).body(new ErrorDetails(e.getMessage(), e.getCode()));
	}
	
	@ExceptionHandler(value = InvalidCredentialsException.class)
	public ResponseEntity<Object> handleInvalidCredentialsException(InvalidCredentialsException e) {
		return ResponseEntity.status(e.getHttpStatus()).body(new ErrorDetails(e.getMessage(), e.getCode()));
	}

	@ExceptionHandler(value = UnauthorizedException.class)
	public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException e) {
		return ResponseEntity.status(e.getHttpStatus()).body(new ErrorDetails(e.getMessage(),e.getCode()));
	}

}
