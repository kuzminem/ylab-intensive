package io.ylab.intensive.lesson03.passwordvalidator;

public class WrongLoginException extends Exception {
    WrongLoginException() {
    }

    WrongLoginException(String message) {
        super(message);
    }
}
