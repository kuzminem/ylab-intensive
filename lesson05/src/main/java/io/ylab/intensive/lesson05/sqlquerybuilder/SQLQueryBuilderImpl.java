package io.ylab.intensive.lesson05.sqlquerybuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SQLQueryBuilderImpl implements SQLQueryBuilder {
    private final DatabaseMetaData metaData;

    @Autowired
    public SQLQueryBuilderImpl(DataSource dataSource) throws SQLException {
        this.metaData = dataSource
                .getConnection()
                .getMetaData();
    }

    @Override
    public String queryForTable(String tableName) throws SQLException {
        ResultSet resultSet = this.metaData
                .getTables(null, "%", tableName, null);
        if (!resultSet.next()) {
            return null;
        }
        resultSet = this.metaData
                .getColumns(null, "%", tableName, "%");
        List<String> columns = new ArrayList<>();
        while (resultSet.next()) {
            columns.add(resultSet.getString("column_name"));
        }
        return "SELECT"
                + (columns.isEmpty() ? " " : " " + String.join(", ", columns) + " ")
                + "FROM " + tableName;
    }

    @Override
    public List<String> getTables() throws SQLException {
        ResultSet resultSet = this.metaData
                .getTables(null, "%", "%", null);
        List<String> tables = new ArrayList<>();
        while (resultSet.next()) {
            tables.add(resultSet.getString("table_name"));
        }
        return tables;
    }
}
