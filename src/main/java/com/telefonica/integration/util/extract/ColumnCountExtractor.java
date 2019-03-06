package com.telefonica.integration.util.extract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.telefonica.integration.util.exceptions.SQLDataProcessException;

/**
 * Extracts the column count from the {@link ResultSet}.
 * 
 * @author Peter Izso
 *
 */
@Service
public class ColumnCountExtractor {

    /**
     * Extracts the column count from the {@link ResultSet}.
     * 
     * @param resultSet
     *            The {@link ResultSet} of the SQL query
     * @return the column count
     */
    public int getColumnCount(ResultSet resultSet) {
        try {
            return resultSet.getMetaData()
                            .getColumnCount();
        } catch (SQLException e) {
            throw new SQLDataProcessException("Getting column count", e);
        }
    }
}
