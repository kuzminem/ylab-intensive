package io.ylab.intensive.lesson04.filesort;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileSorterImpl implements FileSorter {
    private final DataSource dataSource;

    public FileSorterImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public File sort(File data) {
        try {
            load(data);
            File res = new File(data.getParent() + "/sorted.txt");
            save(res);
            return res;
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void load(File data) {
        try (BufferedReader reader = new BufferedReader(new FileReader(data));
             Batch batch = new Batch(this.dataSource, 1000)) {
            for (String line; (line = reader.readLine()) != null; ) {
                batch.add(Long.parseLong(line));
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void save(File res) throws SQLException, IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(res));
             Connection connection = this.dataSource.getConnection()) {
            String selectQuery = "select val "
                    + "from numbers "
                    + "order by val desc";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                writer.write(resultSet.getLong("val") + "\r\n");
            }
        }
    }
}
