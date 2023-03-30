package io.ylab.intensive.lesson04.eventsourcing.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import io.ylab.intensive.lesson04.DbUtil;
import io.ylab.intensive.lesson04.RabbitMQUtil;
import io.ylab.intensive.lesson04.eventsourcing.Person;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DbApp {
    public static void main(String[] args) throws Exception {
        final ConnectionFactory connectionFactory = initMQ();
        final String queueName = "queue";
        final DataSource dataSource = initDb();
        final Executor executor = new Executor(dataSource);
        final ObjectMapper mapper = new ObjectMapper();

        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            while (!Thread.currentThread().isInterrupted()) {
                GetResponse message = channel.basicGet(queueName, true);
                if (message != null) {
                    Order order = mapper.readValue(message.getBody(), Order.class);
                    Person person = order.getPerson();
                    if (order.getCommand().equals("save")) {
                        executor.save(person);
                    } else if (order.getCommand().equals("delete")) {
                        if (executor.delete(person)) {
                            System.err.println("Person with id " + person.getId() + " not found");
                        }
                    }
                }
            }
        }
    }

    private static ConnectionFactory initMQ() throws Exception {
        return RabbitMQUtil.buildConnectionFactory();
    }

    private static DataSource initDb() throws SQLException {
        String ddl = ""
                + "drop table if exists person;"
                + "create table if not exists person (\n"
                + "person_id bigint primary key,\n"
                + "first_name varchar,\n"
                + "last_name varchar,\n"
                + "middle_name varchar\n"
                + ")";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(ddl, dataSource);
        return dataSource;
    }
}
