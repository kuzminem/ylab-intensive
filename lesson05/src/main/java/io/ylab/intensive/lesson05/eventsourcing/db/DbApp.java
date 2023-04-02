package io.ylab.intensive.lesson05.eventsourcing.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import io.ylab.intensive.lesson05.eventsourcing.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DbApp {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();

        final ConnectionFactory connectionFactory =
                applicationContext.getBean(ConnectionFactory.class);
        PersonRepository personRepository =
                applicationContext.getBean(PersonRepository.class);
        final ObjectMapper mapper = new ObjectMapper();
        final String queueName = "queue";

        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            while (!Thread.currentThread().isInterrupted()) {
                GetResponse message = channel.basicGet(queueName, true);
                if (message != null) {
                    Order order = mapper.readValue(message.getBody(), Order.class);
                    Person person = order.getPerson();
                    if (order.getCommand().equals("save")) {
                        personRepository.save(person);
                    } else if (order.getCommand().equals("delete")) {
                        if (personRepository.delete(person)) {
                            System.err.println("Person with id " + person.getId() + " not found");
                        }
                    }
                }
            }
        }
    }
}
