package com.telefonica.integration.util.domain;

import java.util.List;

/**
 * Domain object representing the name of the test, and the list of {@link TestData} domain object.
 * 
 * @author Peter Izso
 *
 */
public class TestCases {

    private String testName;
    private List<TestData> testData;

    public String getTestName() {
        return testName;
    }

    public List<TestData> getTestData() {
        return testData;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setTestData(List<TestData> testData) {
        this.testData = testData;
    }

}
