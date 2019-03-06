package com.accenture.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.accenture.test.util.DataBaseChecker;
import com.accenture.test.util.domain.TestCases;
import com.accenture.test.util.read.JsonReader;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DummyTest {

    @Autowired
    private JsonReader jsonReader;
    @Autowired
    private DataBaseChecker dataBaseChecker;

    @Test
    @EnabledIf("${tests.dummy.enabled:false}")
    public void dummyTest() {
        // GIVEN
        TestCases testCases = jsonReader.readJson("src/test/resources/dummyTest.json");

        // WHEN/THEN
        dataBaseChecker.runTestCase(testCases);
    }
}