package com.accenture.test.util.extract;

import java.sql.ResultSet;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.accenture.test.util.exceptions.MoreThanOneResultsException;

/**
 * Custom {@link ResultSet} mapper.
 * 
 * @author Peter Izso
 *
 */
@Component
public class ResultSetMapper implements RowMapper<Map<String, String>> {

    private final ColumnCountExtractor columnCountExtractor;
    private final ColumnNameExtractor columnNameExtractor;
    private final ColumnValueExtractor columnValueExtractor;

    public ResultSetMapper(ColumnCountExtractor columnCountExtractor, ColumnNameExtractor columnNameExtractor, ColumnValueExtractor columnValueExtractor) {
        this.columnCountExtractor = columnCountExtractor;
        this.columnNameExtractor = columnNameExtractor;
        this.columnValueExtractor = columnValueExtractor;
    }

    /**
     * The custom mapRow method iterates over the columns, and puts them into a map (column-value pairs).
     */
    @Override
    public Map<String, String> mapRow(ResultSet resultSet, int rowNum) {
        try {
            return IntStream.range(1, columnCountExtractor.getColumnCount(resultSet) + 1)
                            .mapToObj(index -> getEntry(resultSet, index))
                            .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        } catch (IllegalStateException e) {
            if (e.getMessage()
                 .contains("Duplicate key")) {
                throw new MoreThanOneResultsException(e);
            } else {
                throw e;
            }
        }
    }

    private Entry<String, String> getEntry(ResultSet resultSet, int index) {
        return new AbstractMap.SimpleEntry<String, String>(columnNameExtractor.getColumnName(resultSet, index), columnValueExtractor.getColumnValue(resultSet, index));
    }

}
