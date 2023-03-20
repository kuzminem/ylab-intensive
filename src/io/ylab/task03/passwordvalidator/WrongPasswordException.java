package io.ylab.task03.passwordvalidator;

public class WrongPasswordException extends Exception {
    WrongPasswordException() {
    }

    WrongPasswordException(String message) {
        super(message);
    }
}
