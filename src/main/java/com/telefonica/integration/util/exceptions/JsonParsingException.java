package com.telefonica.integration.util.exceptions;

/**
 * JsonParseException occurring during mapping the JSONs to java objects.
 * 
 * @author Peter Izso
 *
 */
public class JsonParsingException extends RuntimeException {

    private static final String MESSAGE = "Error during parsing the JSON: '%s'";

    public JsonParsingException(String path, Throwable cause) {
        super(String.format(MESSAGE, path), cause);
    }
}
