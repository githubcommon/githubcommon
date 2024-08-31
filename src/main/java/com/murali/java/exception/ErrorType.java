package com.murali.java.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Sanapala Muralidharan
 * @date: 31 Aug 2024 12:24:58 pm
 * @version:3.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorType {

    private String time;
    private String status;
    private String message;
}
