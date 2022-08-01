package com.centroedu.student.utils.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidIdException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidIdException(String message) {
		super(message);
		
	}
	
	

}
