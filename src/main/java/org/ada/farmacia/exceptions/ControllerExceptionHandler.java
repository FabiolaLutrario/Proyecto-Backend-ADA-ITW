package org.ada.farmacia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ExistingResourceException.class)
    public ResponseEntity handleException(ExistingResourceException e) {

        return new ResponseEntity(ExistingResourceException.MESSAGE,
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleException(ResourceNotFoundException e) {

        return new ResponseEntity(ResourceNotFoundException.MESSAGE,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotDeletedException.class)
    public ResponseEntity handleException(ResourceNotDeletedException e){

        return new ResponseEntity(ResourceNotDeletedException.MESSAGE,
                HttpStatus.CONFLICT);
    }
}
