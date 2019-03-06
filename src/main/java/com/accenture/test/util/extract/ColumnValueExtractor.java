package com.accenture.test.util.extract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.accenture.test.util.exceptions.SQLDataProcessException;

/**
 * Extracts the value of the specific column from the {@link ResultSet}.
 * 
 * @author Peter Izso
 *
 */
@Service
public class ColumnValueExtractor {

    /**
     * Extracts the value of the specific column from the {@link ResultSet}.
     * 
     * @param resultSet
     *            The {@link ResultSet} of the SQL query
     * @param index
     *            index of the column
     * @return the value of the column
     */
    public String getColumnValue(ResultSet resultSet, int index) {
        try {
            String columnValue = resultSet.getString(index);
            return columnValue == null ? "NULL" : columnValue;
        } catch (SQLException e) {
            throw new SQLDataProcessException("Getting value", e);
        }
    }
}
