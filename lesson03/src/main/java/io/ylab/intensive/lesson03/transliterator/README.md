# Transliterator.

Правила транслитерации приведены в таблице ниже:

| Буква | Транслит | Буква | Транслит | Буква | Транслит |
|:-----:|:--------:|:-----:|:--------:|:-----:|:--------:|
|   А   |    A     |   К   |    K     |   Х   |    KH    |
|   Б   |    B     |   Л   |    L     |   Ц   |    TS    |
|   В   |    V     |   М   |    M     |   Ч   |    CH    |
|   Г   |    G     |   Н   |    N     |   Ш   |    SH    |
|   Д   |    D     |   О   |    O     |   Щ   |   SHCH   |
|   Е   |    E     |   П   |    P     |   Ъ   |    IE    |
|   Ё   |    E     |   Р   |    R     |   Ы   |    Y     |
|   Ж   |    ZH    |   С   |    S     |   Ь   |    -     |
|   З   |    Z     |   Т   |    T     |   Э   |    E     |
|   И   |    I     |   У   |    U     |   Ю   |    IU    |
|   Й   |    I     |   Ф   |    F     |   Я   |    IA    |

Реализовать интерфейс Transliterator:

```java
public interface Transliterator {
    String transliterate(String source);
}
```

Метод transliterate должен выполнять транслитерацию входной строки в выходную, то есть заменять каждый символ кириллицы на соответствующую группу символов латиницы. Каждый символ кириллицы, имеющийся во входной строке входит в нее в верхнем регистре.

```java
public class TransliteratorTest {
    public static void main(String[] args) {
        Transliterator transliterator = new TransliteratorImpl();
        String res = transliterator
                .transliterate("HELLO! ПРИВЕТ! Go, boy!");
        System.out.println(res);
    }
}
```

```
> HELLO! PRIVET! Go, boy!
```
