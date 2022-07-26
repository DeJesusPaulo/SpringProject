package com.centroedu.student.utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.centroedu.student.utils.exceptions.DataValidationException;
import com.centroedu.student.utils.exceptions.InvalidIdException;
import com.centroedu.student.utils.exceptions.StudentNotFoundException;
import com.centroedu.student.utils.exceptions.ListStudentsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity<Object> handleException(DataValidationException e, WebRequest request){
        Map<String,String> errors = new HashMap<>();
        e.getResult().getFieldErrors().forEach( fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> handleException(StudentNotFoundException e, WebRequest request){
        return new  ResponseEntity<>(new ErrorResponse(e.getMessage(),HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<Object> handleException(InvalidIdException e, WebRequest request){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(),HttpStatus.BAD_REQUEST,LocalDateTime.now()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ListStudentsNotFoundException.class)
    public ResponseEntity<Object> handleException(ListStudentsNotFoundException e, WebRequest request){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(),HttpStatus.NO_CONTENT, LocalDateTime.now()), HttpStatus.NO_CONTENT);
    }
}
