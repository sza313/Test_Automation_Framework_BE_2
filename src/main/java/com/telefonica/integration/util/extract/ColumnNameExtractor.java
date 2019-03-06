package com.telefonica.integration.util.extract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.telefonica.integration.util.exceptions.SQLDataProcessException;

/**
 * Extracts the column name from the {@link ResultSet}.
 * 
 * @author Peter Izso
 *
 */
@Service
public class ColumnNameExtractor {

    private static final String APOSTROPH_PATTERN = "'([A-Za-z_1-9]+)'";
    private static final String TO_CHAR_PATTERN = "TO_CHAR\\(([A-Za-z_1-9]+),.+";

    /**
     * Extracts the column name from the {@link ResultSet}.
     * 
     * @param resultSet
     *            The {@link ResultSet} of the SQL query
     * @param index
     *            index of the column
     * @return the column name
     */
    public String getColumnName(ResultSet resultSet, int index) {
        try {
            String columnName = resultSet.getMetaData()
                                         .getColumnName(index);
            Matcher toCharMatcher = Pattern.compile(TO_CHAR_PATTERN)
                                           .matcher(columnName);
            Matcher apostrophMatcher = Pattern.compile(APOSTROPH_PATTERN)
                                              .matcher(columnName);
            if (toCharMatcher.matches()) {
                columnName = toCharMatcher.group(1);
            } else if (apostrophMatcher.matches()) {
                columnName = apostrophMatcher.group(1);
            }
            return columnName;
        } catch (SQLException e) {
            throw new SQLDataProcessException("Getting column name", e);
        }
    }
}
