package com.gabriel.berlinclock.domain.api;

import com.gabriel.berlinclock.domain.InvalidBerlinTimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidBerlinTimeException.class)
    public ProblemDetail handleInvalidBerlinTime(InvalidBerlinTimeException ex){
        return processBadRequest("Invalid Berlin Time", ex.getMessage());
    }

    private static ProblemDetail processBadRequest(String title, String message){
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
        pd.setTitle(title);
        return pd;
    }
}
