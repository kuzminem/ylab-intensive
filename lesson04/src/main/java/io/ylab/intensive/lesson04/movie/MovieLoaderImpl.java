package io.ylab.intensive.lesson04.movie;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
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
            Batch batch = new Batch(this.dataSource);

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
                    batch.add(movie);
                } catch (IllegalArgumentException e) {
                    System.out.println(e);
                }
            }
            batch.send();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
