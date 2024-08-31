package com.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: Sanapala Muralidharan
 * @date: 30 Aug 2024 10:53:53 pm
 * @version:3.x
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8485542514662451814L;

    public ResourceNotFoundException(String message) {
	super(message);
    }
}
