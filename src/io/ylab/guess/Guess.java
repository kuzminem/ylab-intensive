package io.ylab.guess;

import java.util.Random;
import java.util.Scanner;

public class Guess {
    public static void main(String[] args) {
        int number = new Random().nextInt(99) + 1;
        int maxAttempts = 10;
        System.out.println("Я загадал число. У тебя " + maxAttempts + " попыток угадать.");

        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= maxAttempts; i++) {
            int n = scanner.nextInt();
            if (number == n) {
                System.out.println("Ты угадал с " + i + " попытки");
                break;
            } else if (i == maxAttempts) {
                System.out.println("Ты не угадал");
            } else if (number < n) {
                System.out.println("Мое число меньше! У тебя осталось " + (maxAttempts - i) + " попыток");
            } else {
                System.out.println("Мое число больше! У тебя осталось " + (maxAttempts - i) + " попыток");
            }
        }
    }
}
