# Guess.

Игра угадайка. При запуске программа загадывает число от 1 до 99 (включительно) и дает пользователю 10 попыток отгадать. Далее пользователь начинает вводить число. И тут возможен один из следующих вариантов:
- Пользователь отгадал число. В таком случае выводится строка
  "Ты угадал с N попытки", где N - номер текущей попытки пользователя
- Пользователь ввел число, меньше загаданного. В таком случае выводится сообщение "Мое число меньше! У тебя осталось M попыток" где M - количество оставшихся попыток
- Пользователь ввел число, больше загаданного. В таком случае выводится сообщение "Мое число больше! У тебя осталось M попыток" где M - количество оставшихся попыток
- У пользователя закончились попытки и число не было угадано. В таком случае выводится сообщение "Ты не угадал"

```java
public class Guess {
    public static void main(String[] args) throws Exception {
        int number = new Random().nextInt(99) + 1; // здесь загадывается число от 1 до 99
        int maxAttempts = 10; // здесь задается количество попыток
        System.out.println("Я загадал число. У тебя " + maxAttempts + " попыток угадать.");
        // ваш код здесь
    }
}
```
