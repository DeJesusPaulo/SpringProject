package com.centroedu.student.utils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
    //private List<String> errors;

}
