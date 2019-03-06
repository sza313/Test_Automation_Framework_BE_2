package com.accenture.test.util.exceptions;

/**
 * JsonMappingException occurring during the mapping of JSONs to java objects.
 * 
 * @author Peter Izso
 *
 */
public class MappingException extends RuntimeException {

    private static final String MESSAGE = "Json cannot be mapped: '%s'";

    public MappingException(String path, Throwable cause) {
        super(String.format(MESSAGE, path), cause);
    }
}
