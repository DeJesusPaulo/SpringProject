package com.centroedu.student.utils.exceptions;

import org.springframework.validation.BindingResult;

public class DataValidationException extends RuntimeException{

    private transient BindingResult result;



    public DataValidationException(String message, BindingResult result){
        super(message);
        this.result = result;
    }

    public BindingResult getResult(){
        return result;
    }
}
