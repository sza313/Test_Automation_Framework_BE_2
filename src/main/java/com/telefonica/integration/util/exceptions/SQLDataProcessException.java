package com.telefonica.integration.util.exceptions;

/**
 * SQLException occurring during processing the ResultSet.
 * 
 * @author Peter Izso
 *
 */
public class SQLDataProcessException extends RuntimeException {

    private static final String MESSAGE = "Reading the result set caused an exception at %s";

    public SQLDataProcessException(String methodCause, Throwable cause) {
        super(String.format(MESSAGE, methodCause), cause);
    }

}
