package io.ylab.intensive.lesson04.eventsourcing.api;

import com.rabbitmq.client.ConnectionFactory;
import io.ylab.intensive.lesson04.RabbitMQUtil;
import io.ylab.intensive.lesson04.eventsourcing.Person;

import java.util.List;

public class ApiApp {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = initMQ();
        PersonApi personApi = new PersonApiImpl(connectionFactory);

//        personApi.savePerson(1L, "Александр", "Алексеев", "Александрович");
//        personApi.savePerson(2L, "Борис", "Борисов", "Борисович");
//        personApi.savePerson(3L, "Василий", "Васильев", "Викторович");
        showAll(personApi.findAll());

//        personApi.deletePerson(1L);
//        personApi.deletePerson(2L);
//        personApi.deletePerson(3L);
//        Person person = personApi.findPerson(1L);
    }

    private static ConnectionFactory initMQ() throws Exception {
        return RabbitMQUtil.buildConnectionFactory();
    }

    private static void show(Person person) {
        System.out.println(person.getId() + " "
                + person.getLastName() + " "
                + person.getName() + " "
                + person.getMiddleName());
    }

    private static void showAll(List<Person> personList) {
        for (Person p : personList) {
            show(p);
        }
    }
}
