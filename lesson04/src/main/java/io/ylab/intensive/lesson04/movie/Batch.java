package io.ylab.intensive.lesson04.movie;

import javax.sql.DataSource;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class Batch implements Closeable {
    private final Connection connection;
    private final PreparedStatement preparedStatement;

    public Batch(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
        this.connection.setAutoCommit(false);
        String insertQuery = "insert into movie "
                + "(year, length, title, subject, actors, actress, director, popularity, awards) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.preparedStatement = this.connection.prepareStatement(insertQuery);
    }

    public void add(Movie movie) throws SQLException {
        setInt(1, movie.getYear());
        setInt(2, movie.getLength());
        setVarchar(3, movie.getTitle());
        setVarchar(4, movie.getSubject());
        setVarchar(5, movie.getActors());
        setVarchar(6, movie.getActress());
        setVarchar(7, movie.getDirector());
        setInt(8, movie.getPopularity());
        setBoolean(9, movie.getAwards());

        this.preparedStatement.addBatch();
    }

    private void setInt(int index, Integer parameter) throws SQLException {
        if (parameter == null) {
            this.preparedStatement.setNull(index, Types.INTEGER);
        } else {
            this.preparedStatement.setInt(parameter, parameter);
        }
    }

    private void setVarchar(int index, String parameter) throws SQLException {
        if (parameter == null) {
            this.preparedStatement.setNull(index, Types.VARCHAR);
        } else {
            this.preparedStatement.setString(index, parameter);
        }
    }

    private void setBoolean(int index, Boolean parameter) throws SQLException {
        if (parameter == null) {
            this.preparedStatement.setNull(index, Types.BOOLEAN);
        } else {
            this.preparedStatement.setBoolean(index, parameter);
        }
    }

    public void close() {
        try {
            this.preparedStatement.executeBatch();
            this.connection.commit();
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
