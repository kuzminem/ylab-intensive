# File Sort.

Реализовать интерфейс:

```java
public interface FileSorter {
    File sort(File data);
}
```

Реализация интерфейса получает на вход файл, состоящий из чисел (long), разделенных переносом строки и возвращает файл, в котором эти числа отсортированы в порядке убывания.

1. Можно считать, что максимальный размер файла &mdash; 1000000 чисел
2. Сортировку необходимо реализовать средствами БД
3. Работа с БД &mdash; средствами JDBC
4. При вставке данных обязательно использовать batch-processing. Разобраться что это такое, для чего используется и как реализовать
5. Необязательно. Реализовать версию без batch-processing, сравнить производительность