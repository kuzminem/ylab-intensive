package io.ylab.intensive.lesson04.movie;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class MovieLoaderImpl implements MovieLoader {
    private final DataSource dataSource;

    public MovieLoaderImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadData(File file) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, Charset.forName("CP1252")))) {
            String line = reader.readLine();
            if (!line.equals("Year;Length;Title;Subject;Actor;Actress;Director;Popularity;Awards;*Image")) {
                throw new IllegalArgumentException("The first line should have a header");
            }
            line = reader.readLine();
            if (!line.equals("INT;INT;STRING;CAT;CAT;CAT;CAT;INT;BOOL;STRING")) {
                throw new IllegalArgumentException("The second line should have a header");
            }
            Packer packer = new Packer();
            for (int linesCounter = 3; ; linesCounter++) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] fields = line.split(";");
                try {
                    if (fields[2].equals("")) {
                        throw new IllegalArgumentException("Line " + linesCounter
                                + " - title not found");
                    }
                    Movie movie = packer.getMovie(fields, linesCounter);
                    saveData(movie, this.dataSource);
                } catch (IllegalArgumentException e) {
                    System.out.println(e);
                }
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveData(Movie movie, DataSource dataSource) throws SQLException {
        String insertQuery = "insert into movie "
                + "(year, length, title, subject, actors, actress, director, popularity, awards)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            if (movie.getYear() == null) {
                preparedStatement.setNull(1, Types.INTEGER);
            } else {
                preparedStatement.setInt(1, movie.getYear());
            }

            if (movie.getLength() == null) {
                preparedStatement.setNull(2, Types.INTEGER);
            } else {
                preparedStatement.setInt(2, movie.getLength());
            }

            preparedStatement.setString(3, movie.getTitle());

            if (movie.getSubject() == null) {
                preparedStatement.setNull(4, Types.VARCHAR);
            } else {
                preparedStatement.setString(4, movie.getSubject());
            }

            if (movie.getActors() == null) {
                preparedStatement.setNull(5, Types.VARCHAR);
            } else {
                preparedStatement.setString(5, movie.getActors());
            }

            if (movie.getActress() == null) {
                preparedStatement.setNull(6, Types.VARCHAR);
            } else {
                preparedStatement.setString(6, movie.getActress());
            }

            if (movie.getDirector() == null) {
                preparedStatement.setNull(7, Types.VARCHAR);
            } else {
                preparedStatement.setString(7, movie.getDirector());
            }

            if (movie.getPopularity() == null) {
                preparedStatement.setNull(8, Types.INTEGER);
            } else {
                preparedStatement.setInt(8, movie.getPopularity());
            }

            if (movie.getAwards() == null) {
                preparedStatement.setNull(9, Types.BOOLEAN);
            } else {
                preparedStatement.setBoolean(9, movie.getAwards());
            }

            preparedStatement.executeUpdate();
        }
    }
}
