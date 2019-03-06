package com.accenture.test.util.read;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.accenture.test.util.domain.TestCases;
import com.accenture.test.util.exceptions.JsonIOException;
import com.accenture.test.util.exceptions.JsonParsingException;
import com.accenture.test.util.exceptions.MappingException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Reads the input JSON.
 * 
 * @author Peter Izso
 *
 */
@Service
public class JsonReader {

    private final ObjectMapper objectMapper;
    private final Logger logger;

    public JsonReader(ObjectMapper objectMapper, Logger logger) {
        this.objectMapper = objectMapper;
        this.logger = logger;
    }

    /**
     * Reads the input json, and maps it to {@link TestCases} domain object.
     * 
     * @param path
     *            Path of the JSON file
     * @return {@link TestCases} domain object
     */
    public TestCases readJson(String path) {
        try {
            logger.info(String.format("Reading the json: %s", path));
            return objectMapper.readValue(new File(path), TestCases.class);
        } catch (JsonParseException e) {
            throw new JsonParsingException(path, e);
        } catch (JsonMappingException e) {
            throw new MappingException(path, e);
        } catch (IOException e) {
            throw new JsonIOException(path, e);
        }
    }
}
