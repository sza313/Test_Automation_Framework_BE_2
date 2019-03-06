package com.telefonica.integration.util.compare;

import java.util.Map;

import org.assertj.core.api.SoftAssertions;
import org.springframework.stereotype.Component;

import com.telefonica.integration.util.domain.TestData;
import com.telefonica.integration.util.extract.DataBaseValueExtractor;
import com.telefonica.integration.util.extract.TableNameExtractor;

/**
 * Compares the expected and the actual values. Expected values are coming from the JSON provided for tests, actual values are coming from the result
 * of the SQL query.
 * 
 * @author Peter Izso
 *
 */
@Component
public class ValueComparator {

    private static final String FAILURE_MESSAGE_DUPLICATE_KEY = "Failure: \nThere were more than one result lines.\n The SQL query was: %s";
    private static final String FAILURE_MESSAGE = "Failure: \nComparing maps of test case '%s' failed at table '%s' \n The SQL query was: %s";
    private final DataBaseValueExtractor dataBaseValueExtractor;
    private final TableNameExtractor tableNameExtractor;

    public ValueComparator(DataBaseValueExtractor dataBaseValueExtractor, TableNameExtractor tableNameExtractor) {
        this.dataBaseValueExtractor = dataBaseValueExtractor;
        this.tableNameExtractor = tableNameExtractor;
    }

    /**
     * Compares the expected and the actual values. Expected values are coming from the JSON provided for tests, actual values are coming from the result
     * of the SQL query.
     * 
     * @param testData
     *            {@link TestData} object containing the SQL query and the expected results
     * @param testName
     *            Name of the test
     * @param softly
     *            The {@link SoftAssertions} object
     */
    public void compareValues(TestData testData, String testName, SoftAssertions softly) {
        String sqlQuery = testData.getSqlQuery();
        Map<String, String> expectedValues = testData.getExpectedValues();
        String tableName = tableNameExtractor.extractTableName(testData);
        try {
            Map<String, String> actualValues = dataBaseValueExtractor.extractDBValues(sqlQuery);
            if (expectedValues.isEmpty()) {
                softly.assertThat(actualValues)
                      .describedAs(FAILURE_MESSAGE, testName, tableName, sqlQuery)
                      .hasSameSizeAs(expectedValues);
            } else {
                softly.assertThat(actualValues)
                      .describedAs(FAILURE_MESSAGE, testName, tableName, sqlQuery)
                      .containsAllEntriesOf(expectedValues)
                      .hasSameSizeAs(expectedValues);
            }
        } catch (IllegalStateException e) {
            if (e.getMessage()
                 .contains("Duplicate key")) {
                softly.fail(String.format(FAILURE_MESSAGE_DUPLICATE_KEY, sqlQuery));
            } else {
                throw e;
            }
        }
    }
}