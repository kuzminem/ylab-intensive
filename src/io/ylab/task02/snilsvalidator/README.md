# Snils Validator.

[Валидация и проверка контрольного числа СНИЛС](http://www.kholenkov.ru/data-validation/snils/). 

Реализовать интерфейс SnilsValidator

```java
public interface SnilsValidator {
    /**
    * Проверяет, что в строке содержится валидный номер СНИЛС
    * @param snils снилс
    * @return результат проверки
    */
    boolean validate(String snils);
}
```

Который возвращает true если номер СНИЛС валидный, false - в противном случае. Можно считать, что номер передается в виде строки, содержащей исключительно цифры от 0 до 9. 