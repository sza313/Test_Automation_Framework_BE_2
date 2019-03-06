package com.accenture.test.util;

import java.util.Map.Entry;

import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.accenture.test.util.compare.ValueComparator;
import com.accenture.test.util.domain.TestCases;
import com.accenture.test.util.domain.TestData;

@Component
public class DataBaseChecker {

    private final ValueComparator valueComparator;
    private final Logger logger;
    private final SoftAssertions softly;

    public DataBaseChecker(ValueComparator valueComparator, Logger logger, SoftAssertions softly) {
        this.valueComparator = valueComparator;
        this.logger = logger;
        this.softly = softly;
    }

    public void runTestCase(TestCases testCases) {
        logger.info(String.format("Running the test case '%s'", testCases.getTestName()));
        testCases.getTestData()
                 .stream()
                 .map(this::logExpectedKeyValuePairs)
                 .forEach(testData -> valueComparator.compareValues(testData, testCases.getTestName(), softly));
        softly.assertAll();
    }

    private TestData logExpectedKeyValuePairs(TestData testData) {
        testData.getExpectedValues()
                .entrySet()
                .stream()
                .forEach(this::logValues);
        return testData;
    }

    private void logValues(Entry<String, String> entry) {
        logger.debug(String.format("Expected key: %s, Expected value: %s", entry.getKey(), entry.getValue()));
    }
}
