package com.accenture.test.util.domain;

import java.util.Map;

/**
 * Domain object representing the SQL query and the expected values.
 * 
 * @author Peter Izso
 *
 */
public class TestData {

    private String sqlQuery;
    private Map<String, String> expectedValues;

    public String getSqlQuery() {
        return sqlQuery;
    }

    public Map<String, String> getExpectedValues() {
        return expectedValues;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public void setExpectedValues(Map<String, String> expectedValues) {
        this.expectedValues = expectedValues;
    }

}
