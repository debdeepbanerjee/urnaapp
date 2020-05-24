package com.urna.urnapatients.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UrnaExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ UrnaException.class })
    public ResponseEntity<Object> handleUrnaException(UrnaException ex) {
        return new ResponseEntity<Object>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<Object>(
                "Access Denied or Session Expired", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}
