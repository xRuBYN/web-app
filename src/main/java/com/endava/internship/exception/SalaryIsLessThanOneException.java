package com.endava.internship.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SalaryIsLessThanOneException extends Exception {
    public SalaryIsLessThanOneException(String message) {
        super(message);
    }
}
