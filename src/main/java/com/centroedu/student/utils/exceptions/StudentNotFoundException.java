package com.centroedu.student.utils.exceptions;

import lombok.NoArgsConstructor;


public class StudentNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public StudentNotFoundException(String message) {
		super(message);

	}
	
	

}
