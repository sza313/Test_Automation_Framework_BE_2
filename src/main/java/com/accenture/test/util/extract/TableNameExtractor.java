package com.accenture.test.util.extract;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

/**
 * Extracts table name.
 * 
 * @author Peter Izso
 *
 */
@Service
public class TableNameExtractor {

    private static final String UNKNOWN = "UNKNOWN";

    /**
     * Extract the table name from the SQL query.
     * 
     * @param sqlQuery
     *            SQL query
     * @return name of the table
     */
    public String extractTableName(String sqlQuery) {
        Matcher matcher = Pattern.compile(".+FROM (.+) WHERE.+")
                                 .matcher(sqlQuery);
        return matcher.matches() ? matcher.group(1) : UNKNOWN;
    }
}
