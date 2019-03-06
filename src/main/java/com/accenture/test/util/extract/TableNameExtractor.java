package com.accenture.test.util.extract;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.accenture.test.util.domain.TestData;

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
     * @param testData
     *            {@link TestData} domain object containing the SQL query
     * @return name of the table
     */
    public String extractTableName(TestData testData) {
        Matcher matcher = Pattern.compile(".+FROM (.+) WHERE.+")
                                 .matcher(testData.getSqlQuery());
        return matcher.matches() ? matcher.group(1) : UNKNOWN;
    }
}
