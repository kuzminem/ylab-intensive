# Stars.

Программе передается 3 параметра: количество строк, количество столбцов, произвольный симов. Необходимо вывести вывести фигуру, состоящую из заданного списка строк и заданного количества столбцов, и каждый элемент в которой равен указанному символу.

Ввод: n m c

Вывод: фигура

### Пример:

Ввод:
```
2 3 $
```

Вывод:
```
$ $ $
$ $ $
```

```java
public class Stars {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            String template = scanner.next();
            // ваш код здесь
        }
    }
}
```
