package io.ylab.intensive.lesson04.eventsourcing.db;

import io.ylab.intensive.lesson04.eventsourcing.Person;

public class Order {
    private String command;
    private Person person;

    public Order() {
    }

    public Order(String command, Person person) {
        this.command = command;
        this.person = person;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
