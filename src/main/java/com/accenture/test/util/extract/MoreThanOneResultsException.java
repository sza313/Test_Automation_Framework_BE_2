package com.accenture.test.util.extract;

public class MoreThanOneResultsException extends RuntimeException {

    public MoreThanOneResultsException(Throwable throwable) {
        super(throwable);
    }
}
