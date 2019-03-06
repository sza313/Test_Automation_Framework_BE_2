package com.accenture.test.util.exceptions;

public class MoreThanOneResultsException extends RuntimeException {

    public MoreThanOneResultsException(Throwable throwable) {
        super(throwable);
    }
}
