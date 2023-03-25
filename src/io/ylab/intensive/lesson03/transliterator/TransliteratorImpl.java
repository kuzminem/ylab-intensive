package io.ylab.intensive.lesson03.transliterator;

import java.util.Map;

import static java.util.Map.entry;

public class TransliteratorImpl implements io.ylab.task03.transliterator.Transliterator {
    private static final Map<Character, String> LETTER_MAP = Map.ofEntries(
            entry('А', "A"),
            entry('Б', "B"),
            entry('В', "V"),
            entry('Г', "G"),
            entry('Д', "D"),
            entry('Е', "E"),
            entry('Ё', "E"),
            entry('Ж', "ZH"),
            entry('З', "Z"),
            entry('И', "I"),
            entry('Й', "I"),
            entry('К', "K"),
            entry('Л', "L"),
            entry('М', "M"),
            entry('Н', "N"),
            entry('О', "O"),
            entry('П', "P"),
            entry('Р', "R"),
            entry('С', "S"),
            entry('Т', "T"),
            entry('У', "U"),
            entry('Ф', "F"),
            entry('Х', "KH"),
            entry('Ц', "TS"),
            entry('Ч', "CH"),
            entry('Ш', "SH"),
            entry('Щ', "SHCH"),
            entry('Ъ', "IE"),
            entry('Ы', "Y"),
            entry('Ь', ""),
            entry('Э', "E"),
            entry('Ю', "IU"),
            entry('Я', "IA")
    );

    @Override
    public String transliterate(String source) {
        StringBuilder result = new StringBuilder();
        for (char ch : source.toCharArray()) {
            String symbol = String.valueOf(ch);
            result.append(LETTER_MAP.getOrDefault(ch, symbol));
        }
        return result.toString();
    }
}
