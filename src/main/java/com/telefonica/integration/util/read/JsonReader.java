package com.telefonica.integration.util.read;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telefonica.integration.util.domain.TestCases;
import com.telefonica.integration.util.exceptions.JsonIOException;
import com.telefonica.integration.util.exceptions.JsonParsingException;
import com.telefonica.integration.util.exceptions.MappingException;

@Service
public class JsonReader {

    private final ObjectMapper objectMapper;
    private final Logger logger;

    public JsonReader(ObjectMapper objectMapper, Logger logger) {
        this.objectMapper = objectMapper;
        this.logger = logger;
    }

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
