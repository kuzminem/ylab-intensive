package io.ylab.intensive.lesson05.messagefilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class Censor {

    private final Connection connection;
    private final File censorFile =
            new File("lesson05/src/main/resources/messagefilter/words.txt");

    @Autowired
    public Censor(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
        DatabaseMetaData metaData = this.connection.getMetaData();
        ResultSet resultSet = metaData
                .getTables(null, "%", "censor", new String[]{"TABLE"});
        if (!resultSet.next()) {
            createTable();
        }
    }

    public boolean isBad(String word) {
        try {
            String selectQuery = "select count(*) "
                    + "from censor "
                    + "where word ilike ?";
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, word);
            ResultSet resultSet = preparedStatement.executeQuery();
            int result = 0;
            if (resultSet.next()) {
                result = resultSet.getInt("count");
            }
            return result == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTable() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(this.censorFile))) {
            Statement statement = this.connection.createStatement();
            String query = "create table censor (word varchar)";
            statement.executeUpdate(query);

            this.connection.setAutoCommit(false);
            query = "insert into censor (word) values (?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            for (String line; (line = reader.readLine()) != null; ) {
                preparedStatement.setString(1, line);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            this.connection.commit();
            this.connection.setAutoCommit(true);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
