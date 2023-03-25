package io.ylab.intensive.lesson02.snilsvalidator;

public class SnilsValidatorImpl implements SnilsValidator {
    @Override
    public boolean validate(String snils) {
        snils = snils.replaceAll("[- ]", "");
        if (snils.length() != 11) {
            return false;
        }
        try {
            Long.parseLong(snils);
        } catch (NumberFormatException e) {
            return false;
        }

        char[] numbers = snils.toCharArray();
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.digit(numbers[i], 10) * (9 - i);
        }
        while (sum > 100) {
            sum %= 101;
        }
        if (sum == 100) {
            sum = 0;
        }
        return sum == Integer.parseInt(snils.substring(9));
    }
}
