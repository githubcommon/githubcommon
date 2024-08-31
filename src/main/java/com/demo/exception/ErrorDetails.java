/**
 * 
 */
package com.demo.exception;

import java.util.Date;

/**
 * @author: Sanapala Muralidharan
 * @date: 30 Aug 2024 10:53:24 pm
 * @version:3.x
 */
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;

    /**
     * @param timestamp
     * @param message
     * @param details
     */
    public ErrorDetails(Date timestamp, String message, String details) {
	super();
	this.timestamp = timestamp;
	this.message = message;
	this.details = details;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
	return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
    }

    /**
     * @return the message
     */
    public String getMessage() {
	return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
	this.message = message;
    }

    /**
     * @return the details
     */
    public String getDetails() {
	return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
	this.details = details;
    }

}
