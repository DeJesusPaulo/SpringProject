package com.centroedu.student.utils.exceptions;

public class CoursesNotFoundException extends RuntimeException{

    public CoursesNotFoundException(String message){
        super(message);
    }
}
