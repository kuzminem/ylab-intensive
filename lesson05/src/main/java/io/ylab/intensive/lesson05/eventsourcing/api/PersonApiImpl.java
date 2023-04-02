package io.ylab.intensive.lesson05.eventsourcing.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import io.ylab.intensive.lesson05.eventsourcing.Person;
import io.ylab.intensive.lesson05.eventsourcing.db.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonApiImpl implements PersonApi {
    final String exchangeName = "exc";
    final Channel channel;
    final Connection connection;
    final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public PersonApiImpl(Channel channel, Connection connection) {
        this.channel = channel;
        this.connection = connection;
    }

    private void give(Order order) throws IOException {
        String message = this.mapper.writeValueAsString(order);
        this.channel.basicPublish(this.exchangeName, "*", null, message.getBytes());
    }

    @Override
    public void deletePerson(Long personId) {
        try {
            Person person = new Person();
            person.setId(personId);
            Order order = new Order("delete", person);
            give(order);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void savePerson(Long personId, String firstName, String lastName, String middleName) {
        try {
            Person person = new Person(personId, firstName, lastName, middleName);
            Order order = new Order("save", person);
            give(order);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public Person findPerson(Long personId) {
        if (!isPresent(personId)) {
            return null;
        }
        try {
            String selectQuery = "select first_name, last_name, middle_name "
                    + "from person "
                    + "where person_id = ?";
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(selectQuery);
            preparedStatement.setLong(1, personId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Person person = new Person();
            person.setId(personId);
            if (resultSet.next()) {
                person.setName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getString("last_name"));
                person.setMiddleName(resultSet.getString("middle_name"));
            }
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Person> findAll() {
        try {
            String selectQuery = "select * from person";
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Person> personList = new ArrayList<>();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong("person_id"));
                person.setName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getString("last_name"));
                person.setMiddleName(resultSet.getString("middle_name"));
                personList.add(person);
            }
            return personList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
}
