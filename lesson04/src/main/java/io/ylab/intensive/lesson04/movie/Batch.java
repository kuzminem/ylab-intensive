package io.ylab.intensive.lesson04.movie;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class Batch {
    private final Connection connection;
    private final PreparedStatement preparedStatement;

    Batch(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
        this.connection.setAutoCommit(false);

        String insertQuery = "insert into movie "
                + "(year, length, title, subject, actors, actress, director, popularity, awards)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        this.preparedStatement = this.connection.prepareStatement(insertQuery);
    }

    public void add(Movie movie) throws SQLException {
        if (movie.getYear() == null) {
            this.preparedStatement.setNull(1, Types.INTEGER);
        } else {
            this.preparedStatement.setInt(1, movie.getYear());
        }

        if (movie.getLength() == null) {
            this.preparedStatement.setNull(2, Types.INTEGER);
        } else {
            this.preparedStatement.setInt(2, movie.getLength());
        }

        this.preparedStatement.setString(3, movie.getTitle());

        if (movie.getSubject() == null) {
            this.preparedStatement.setNull(4, Types.VARCHAR);
        } else {
            this.preparedStatement.setString(4, movie.getSubject());
        }

        if (movie.getActors() == null) {
            this.preparedStatement.setNull(5, Types.VARCHAR);
        } else {
            this.preparedStatement.setString(5, movie.getActors());
        }

        if (movie.getActress() == null) {
            this.preparedStatement.setNull(6, Types.VARCHAR);
        } else {
            this.preparedStatement.setString(6, movie.getActress());
        }

        if (movie.getDirector() == null) {
            this.preparedStatement.setNull(7, Types.VARCHAR);
        } else {
            this.preparedStatement.setString(7, movie.getDirector());
        }

        if (movie.getPopularity() == null) {
            this.preparedStatement.setNull(8, Types.INTEGER);
        } else {
            this.preparedStatement.setInt(8, movie.getPopularity());
        }

        if (movie.getAwards() == null) {
            this.preparedStatement.setNull(9, Types.BOOLEAN);
        } else {
            this.preparedStatement.setBoolean(9, movie.getAwards());
        }

        this.preparedStatement.addBatch();
    }

    public void send() throws SQLException {
        this.preparedStatement.executeBatch();
        this.connection.commit();
        this.connection.close();
    }
}
