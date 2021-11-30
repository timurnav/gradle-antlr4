package org.example;

import java.util.ArrayList;
import java.util.List;

public class SQLParserListener extends SQLBaseListener {

    private final List<String> columns = new ArrayList<>();

    private String fromTable;
    private boolean allColumns;

    public SQLQuery getQuery() {
        return new SQLQuery(fromTable, new ArrayList<>(columns), allColumns);
    }

    @Override
    public void enterSqlQuery(SQLParser.SqlQueryContext ctx) {
        fromTable = null;
        columns.clear();
        allColumns = false;
    }

    @Override
    public void exitColumn(SQLParser.ColumnContext ctx) {
        columns.add(ctx.VALID_NAME().getText());
    }

    @Override
    public void exitAllColumns(SQLParser.AllColumnsContext ctx) {
        allColumns = true;
    }

    @Override
    public void exitTable(SQLParser.TableContext ctx) {
        fromTable = ctx.VALID_NAME().getText();
    }
}
