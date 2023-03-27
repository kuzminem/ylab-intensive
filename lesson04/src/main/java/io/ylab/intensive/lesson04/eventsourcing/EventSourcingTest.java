package io.ylab.intensive.lesson04.eventsourcing;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.ylab.intensive.lesson04.RabbitMQUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EventSourcingTest {
    public static void main(String[] args) throws IOException, TimeoutException {
        String exchangeName = "exc";
        String queueName = "queue";

        ConnectionFactory connectionFactory = RabbitMQUtil.buildConnectionFactory();
//        try (Connection connection = connectionFactory.newConnection();
//             Channel channel = connection.createChannel()) {
//            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);
//            channel.queueDeclare(exchangeName, true, false, false, null);
//            channel.queueBind(queueName, exchangeName, "*");
//
//            channel.basicPublish(exchangeName, "*", null, "Hello World".getBytes());
//        }

        Connection connection = connectionFactory.newConnection();
    }
}