package io.ylab.intensive.lesson03.transliterator;

import io.ylab.intensive.lesson03.transliterator.TransliteratorImpl;

public class TransliteratorTest {
    public static void main(String[] args) {
        io.ylab.intensive.lesson03.transliterator.Transliterator transliterator = new TransliteratorImpl();

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
