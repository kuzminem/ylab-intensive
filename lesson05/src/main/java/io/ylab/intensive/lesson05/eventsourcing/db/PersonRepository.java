package io.ylab.intensive.lesson05.eventsourcing.db;

import io.ylab.intensive.lesson05.eventsourcing.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PersonRepository {
    Connection connection;

    @Autowired
    public PersonRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean delete(Person person) {
        if (!isPresent(person.getId())) {
            return false;
        }
        try {
            String insertQuery = "delete "
                    + "from person "
                    + "where person_id = ?";
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(insertQuery);
            preparedStatement.setLong(1, person.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Person person) {
        if (isPresent(person.getId())) {
            update(person);
        } else {
            create(person);
        }
    }

    private boolean isPresent(Long personId) {
        try {
            String selectQuery = "select count(*) "
                    + "from person "
                    + "where person_id = ?";
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(selectQuery);
            preparedStatement.setLong(1, personId);
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

    private void update(Person person) {
        try {
            String insertQuery = "update person "
                    + "set "
                    + "first_name = ?, "
                    + "last_name = ?, "
                    + "middle_name = ? "
                    + "where person_id = ?";
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getMiddleName());
            preparedStatement.setLong(4, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void create(Person person) {
        try {
            String insertQuery = "insert into person "
                    + "(person_id, first_name, last_name, middle_name) "
                    + "values (?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(insertQuery);
            preparedStatement.setLong(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setString(4, person.getMiddleName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
