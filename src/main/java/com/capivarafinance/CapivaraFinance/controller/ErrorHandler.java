package com.capivarafinance.CapivaraFinance.controller;

import com.capivarafinance.CapivaraFinance.exception.EntryNotFoundException;
import com.capivarafinance.CapivaraFinance.exception.Error;
import com.capivarafinance.CapivaraFinance.exception.UserAlreadyExistsException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Error> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<Error>(new Error(ex.getMessage(), new Date(System.currentTimeMillis()), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<Error> handleEntryNotFoundException(EntryNotFoundException ex) {
        return new ResponseEntity<Error>(new Error(ex.getMessage(), new Date(System.currentTimeMillis()), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<Error>(new Error(ex.getMessage(), new Date(System.currentTimeMillis()), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Error> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<Error>(new Error(ex.getMessage(), new Date(System.currentTimeMillis()), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> handleConstraintViolationException(ConstraintViolationException ex) {
        String msg = "Erros: ";

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            msg += violation.getMessage() + ", ";
        }

        return new ResponseEntity<Error>(new Error(msg, new Date(System.currentTimeMillis()), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<Error>(new Error(ex.getMessage(), new Date(System.currentTimeMillis()), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
