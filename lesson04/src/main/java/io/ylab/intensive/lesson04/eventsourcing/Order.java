package io.ylab.intensive.lesson04.eventsourcing;

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
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
