package io.ylab.task03.passwordvalidator;

public class WrongLoginException extends Exception {
    WrongLoginException() {
    }

    WrongLoginException(String message) {
        super(message);
    }
}
