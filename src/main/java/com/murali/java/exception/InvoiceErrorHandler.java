package com.murali.java.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author: Sanapala Muralidharan
 * @date: 31 Aug 2024 12:26:59 pm 
 * @version:3.x
 */
@ControllerAdvice
public class InvoiceErrorHandler {

    /**
     * In case of InvoiceNotFoundException is thrown from any controller method,
     * this logic gets executed which behaves like re-usable and clear code (Code
     * Modularity)
     * 
     * @param nfe
     * @return ResponseEntity
     */
    // @ResponseBody
    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<ErrorType> handleNotFound(InvoiceNotFoundException nfe) {

	return new ResponseEntity<ErrorType>(
		new ErrorType(new Date(System.currentTimeMillis()).toString(), "404- NOT FOUND", nfe.getMessage()),
		HttpStatus.NOT_FOUND);
    }
}
