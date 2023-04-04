package io.ylab.intensive.lesson05.messagefilter;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class MessageFilterApp {
    public static void main(String[] args) throws IOException, TimeoutException {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();

        ConnectionFactory connectionFactory =
                applicationContext.getBean(ConnectionFactory.class);
        Connection connection = connectionFactory.newConnection();

        final String exchangeName = "exc";

        final String inputQueueName = "input";
        Channel inputChannel = connection.createChannel();
        inputChannel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);
        inputChannel.queueDeclare(inputQueueName, true, false, false, null);
        inputChannel.queueBind(inputQueueName, exchangeName, "*");

        final String outputQueueName = "output";
        Channel outputChannel = connection.createChannel();
        outputChannel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);
        outputChannel.queueDeclare(outputQueueName, true, false, false, null);
        outputChannel.queueBind(outputQueueName, exchangeName, "*");

        Censor censor = applicationContext.getBean(Censor.class);

        while (!Thread.currentThread().isInterrupted()) {
            GetResponse response = inputChannel.basicGet(inputQueueName, true);
            if (response != null) {
                byte[] body = response.getBody();
                String input = new String(body, StandardCharsets.UTF_8);
                String output = censor.correct(input);
                outputChannel.basicPublish(exchangeName, "*", null, output.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
