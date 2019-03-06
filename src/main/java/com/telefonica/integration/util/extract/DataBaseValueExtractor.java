package com.telefonica.integration.util.extract;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Extracts the values from the database.
 * 
 * @author Peter Izso
 *
 */
@Component
public class DataBaseValueExtractor {

    private final JdbcTemplate jdbcTemplate;
    private final ResultSetMapper resultSetMapper;
    private final Logger logger;

    public DataBaseValueExtractor(JdbcTemplate jdbcTemplate, ResultSetMapper resultSetMapper, Logger logger) {
        this.jdbcTemplate = jdbcTemplate;
        this.resultSetMapper = resultSetMapper;
        this.logger = logger;
    }

    public Map<String, String> extractDBValues(String sqlQuery) {
        logger.info(String.format("Sending SQL query: %s", sqlQuery));
        List<Map<String, String>> result = jdbcTemplate.query(sqlQuery, resultSetMapper);
        return result.stream()
                     .map(Map::entrySet)
                     .flatMap(Set::stream)
                     .map(this::logKeyValuePairs)
                     .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    private Entry<String, String> logKeyValuePairs(Entry<String, String> entry) {
        logger.debug(String.format("Actual key: %s, Actual value: %s", entry.getKey(), entry.getValue()));
        return entry;
    }
}
