package com.accenture.test.util.extract;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.accenture.test.util.domain.TestData;

@Service
public class TableNameExtractor {

    public String extractTableName(TestData testData) {
        String tableName = "UNKNOWN";
        Matcher matcher = Pattern.compile(".+FROM (.+) WHERE.+")
                                 .matcher(testData.getSqlQuery());
        if (matcher.matches()) {
            tableName = matcher.group(1);
        }
        return tableName;
    }
}
