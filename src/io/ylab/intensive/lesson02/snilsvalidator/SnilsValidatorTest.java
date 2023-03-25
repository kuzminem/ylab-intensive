package io.ylab.intensive.lesson02.snilsvalidator;

public class SnilsValidatorTest {
    public static void main(String[] args) {
        SnilsValidator snilsValidator = new SnilsValidatorImpl();

        // Невалидный
        String snils = "01468870570";
        System.out.println(snils + ((snilsValidator.validate(snils) ? " " : " не") + "валидный"));

        // Валидный
        snils = "90114404441";
        System.out.println(snils + ((snilsValidator.validate(snils) ? " " : " не") + "валидный"));

        // Валидный
        snils = "210 548 691 44";
        System.out.println(snils + ((snilsValidator.validate(snils) ? " " : " не") + "валидный"));

        // Валидный
        snils = "084-446-514 80";
        System.out.println(snils + ((snilsValidator.validate(snils) ? " " : " не") + "валидный"));

        // Невалидный
        snils = "4966034131";
        System.out.println(snils + ((snilsValidator.validate(snils) ? " " : " не") + "валидный"));

        // Невалидный
        snils = "4966O341313";
        System.out.println(snils + ((snilsValidator.validate(snils) ? " " : " не") + "валидный"));

        // Валидный
        snils = "49660341313";
        System.out.println(snils + ((snilsValidator.validate(snils) ? " " : " не") + "валидный"));
    }
}
