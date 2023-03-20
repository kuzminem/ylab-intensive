package io.ylab.task03.transliterator;

public class TransliteratorTest {
    public static void main(String[] args) {
        Transliterator transliterator = new TransliteratorImpl();

        String res = transliterator
                .transliterate("HELLO! ПРИВЕТ! Go, boy!");
        System.out.println(res);

        res = transliterator
                .transliterate("ЩЕРБАТЫЙ, Я СКАЗАЛ щербатый!");
        System.out.println(res);

        res = transliterator
                .transliterate("МЯГКИЙ ЗНАК ьЬь");
        System.out.println(res);
    }
}
