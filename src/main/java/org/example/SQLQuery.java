package org.example;

import java.util.List;

public class SQLQuery {

    private final String tableName;
    private final List<String> columns;
    private final boolean allColumns;

    public SQLQuery(String tableName, List<String> columns, boolean allColumns) {
        this.tableName = tableName;
        this.columns = columns;
        this.allColumns = allColumns;
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getColumns() {
        return columns;
    }

    public boolean isAllColumns() {
        return allColumns;
    }
}
