package com.pg.aes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class InvalidParameterException extends RuntimeException {
	/**
	 * To be used when the parameter from the server is wrong.
	 */
	private static final long serialVersionUID = 1L;

	public InvalidParameterException(String msg){
		super(msg);
	}
	
	public InvalidParameterException(String msg, Throwable th) {
		super(msg, th);
	}
}
