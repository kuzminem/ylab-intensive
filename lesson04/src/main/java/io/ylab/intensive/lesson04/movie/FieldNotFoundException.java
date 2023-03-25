package io.ylab.intensive.lesson04.movie;

public class FieldNotFoundException extends Exception {
    FieldNotFoundException() {

    }

    FieldNotFoundException(String message) {
        super(message);
    }
}
