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
            int linesCounter = 3;
            for (; ; linesCounter++) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] fields = line.split(";");
                Movie movie;
                if (fields[2].equals("")) {
                    throw new FieldNotFoundException("Line " + linesCounter
                            + " - title not found");
                } else {
                    movie = packer.getMovie(fields, linesCounter);
                }
                System.out.println(linesCounter);
                saveData(movie, this.dataSource);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } catch (FieldNotFoundException e) {
            System.out.println(e);
        }
    }

    private static void saveData(Movie movie, DataSource dataSource) throws SQLException {
        String insertQuery = "insert into movie (year, length, title, subject, actors, actress, director, popularity, awards) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, movie.getYear());
            preparedStatement.setInt(2, movie.getLength());
            preparedStatement.setString(3, movie.getTitle());
            preparedStatement.setString(4, movie.getSubject());
            preparedStatement.setString(5, movie.getActors());
            preparedStatement.setString(6, movie.getActress());
            preparedStatement.setString(7, movie.getDirector());
            preparedStatement.setInt(8, movie.getPopularity());
            preparedStatement.setBoolean(9, movie.getAwards());
            preparedStatement.executeUpdate();
        }
    }
}
