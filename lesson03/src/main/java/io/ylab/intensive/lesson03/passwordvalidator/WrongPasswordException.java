package io.ylab.intensive.lesson03.passwordvalidator;

public class WrongPasswordException extends Exception {
    WrongPasswordException() {
    }

    WrongPasswordException(String message) {
        super(message);
    }
}
