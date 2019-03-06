package com.telefonica.integration.util.exceptions;

/**
 * IOException during mapping the JSON to java objects.
 * 
 * @author Peter Izso
 *
 */
public class JsonIOException extends RuntimeException {

    private static final String MESSAGE = "Some IOException occured during parsing '%s'";

    public JsonIOException(String path, Throwable cause) {
        super(String.format(MESSAGE, path), cause);
    }

}
