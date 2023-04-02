package io.ylab.intensive.lesson05.eventsourcing.api;

import io.ylab.intensive.lesson05.eventsourcing.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApiApp {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();
        PersonApi personApi = applicationContext.getBean(PersonApi.class);

        Person person;
        List<Person> personList;

        personApi.savePerson(1L, "Александр", "Александров", "Александрович");
        personApi.savePerson(2L, "Борис", "Борисов", "Борисович");
        personApi.savePerson(3L, "Василий", "Васильев", "Васильевич");
        TimeUnit.SECONDS.sleep(1);
        personList = personApi.findAll();
        for (Person p : personList) {
            System.out.println(show(p));
        }
        System.out.println();

        personApi.savePerson(3L, "Виктор", "Викторов", "Викторович");
        TimeUnit.SECONDS.sleep(1);
        personList = personApi.findAll();
        for (Person p : personList) {
            System.out.println(show(p));
        }
        System.out.println();

        person = personApi.findPerson(2L);
        if (person == null) {
            System.out.println("null");
        } else {
            System.out.println(show(person));
        }
        System.out.println();

        personApi.deletePerson(2L);
        TimeUnit.SECONDS.sleep(1);
        personList = personApi.findAll();
        for (Person p : personList) {
            System.out.println(show(p));
        }
        System.out.println();

        person = personApi.findPerson(2L);
        if (person == null) {
            System.out.println("null");
        } else {
            System.out.println(show(person));
        }
        System.out.println();
    }

    private static String show(Person person) {
        return (person.getId() + " "
                + person.getLastName() + " "
                + person.getName() + " "
                + person.getMiddleName());
    }
}
