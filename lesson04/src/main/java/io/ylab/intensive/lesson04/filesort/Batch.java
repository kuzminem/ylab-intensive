package io.ylab.intensive.lesson04.filesort;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batch {
    private final Connection connection;
    private final PreparedStatement preparedStatement;
    private final int capacity;
    private int counter;

    Batch(DataSource dataSource, int capacity) throws SQLException {
        this.connection = dataSource.getConnection();
        this.connection.setAutoCommit(false);
        String insertQuery = "insert into numbers (val) values (?)";
        this.preparedStatement = this.connection.prepareStatement(insertQuery);
        this.capacity = capacity;
    }

    public void add(long value) throws SQLException {
        this.preparedStatement.setLong(1, value);
        this.preparedStatement.addBatch();
        this.counter++;
        if (this.counter == this.capacity) {
            send();
            this.counter = 0;
        }
    }

    private void send() throws SQLException {
        this.preparedStatement.executeBatch();
        this.connection.commit();
    }

    public void close() throws SQLException {
        if (this.counter > 0) {
            send();
        }
        this.connection.close();
    }
}
