package io.ylab.intensive.lesson04.persistentmap;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, методы которого надо реализовать
 */
public class PersistentMapImpl implements PersistentMap {
    private final DataSource dataSource;
    private String mapName;

    public PersistentMapImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void init(String name) {
        this.mapName = name;
    }

    @Override
    public boolean containsKey(String key) {
        try (Connection connection = this.dataSource.getConnection()) {
            String selectQuery = "select count(*) "
                    + "from persistent_map "
                    + "where map_name = ? "
                    + "and key = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, this.mapName);
            preparedStatement.setString(2, key);
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

    @Override
    public List<String> getKeys() {
        try (Connection connection = this.dataSource.getConnection()) {
            String selectQuery = "select key "
                    + "from persistent_map "
                    + "where map_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, this.mapName);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> keys = new ArrayList<>();
            while (resultSet.next()) {
                keys.add(resultSet.getString("key"));
            }
            return keys;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get(String key) {
        try (Connection connection = this.dataSource.getConnection()) {
            String selectQuery = "select value "
                    + "from persistent_map "
                    + "where map_name = ? "
                    + "and key = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, this.mapName);
            preparedStatement.setString(2, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            String value = null;
            if (resultSet.next()) {
                value = resultSet.getString("value");
            }
            return value;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(String key) {
        try (Connection connection = this.dataSource.getConnection()) {
            String insertQuery = "delete "
                    + "from persistent_map "
                    + "where map_name = ? "
                    + "and key = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, this.mapName);
            preparedStatement.setString(2, key);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void put(String key, String value) {
        if (containsKey(key)) {
            remove(key);
        }
        create(key, value);
    }

    private void create(String key, String value) {
        try (Connection connection = this.dataSource.getConnection()) {
            String insertQuery = "insert into persistent_map "
                    + "(map_name, key, value) "
                    + "values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, this.mapName);
            preparedStatement.setString(2, key);
            preparedStatement.setString(3, value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clear() {
        try (Connection connection = this.dataSource.getConnection()) {
            String insertQuery = "delete "
                    + "from persistent_map "
                    + "where map_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, this.mapName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
