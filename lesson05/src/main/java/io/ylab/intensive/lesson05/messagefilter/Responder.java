package io.ylab.intensive.lesson05.messagefilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Responder {

    private final Censor censor;

    @Autowired
    public Responder(Censor censor) {
        this.censor = censor;
    }

    public String getOutput(String input) {
        StringBuilder mask = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String letter = input.substring(i, i + 1);
            mask.append(letter.matches("[a-zA-Zа-яА-Я0-9]") ? letter : " ");
        }

        String[] words = mask.toString().split("\\s+");
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 2 && this.censor.isBad(words[i])) {
                words[i] = words[i].charAt(0)
                        + "*".repeat(words[i].length() - 2)
                        + words[i].charAt(words[i].length() - 1);
            }
        }

        StringBuilder output = new StringBuilder();
        int wordsCounter = 0;
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == ' ') {
                output.append(input.charAt(i));
            } else {
                output.append(words[wordsCounter]);
                i += words[wordsCounter].length() - 1;
                wordsCounter++;
            }
        }

        return output.toString();
    }
}
