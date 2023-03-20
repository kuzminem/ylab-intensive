package io.ylab.task03.filesort;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) throws IOException {
        File dataFile = new Generator().generate("src/io/ylab/task03/filesort/resources/data.txt", 100);
        System.out.println(new Validator(dataFile).isSorted()); // false

        LocalDateTime start = LocalDateTime.now();

        File sortedFile = new Sorter().sortFile(dataFile);
        System.out.println(new Validator(sortedFile).isSorted()); // true

        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);

        System.out.println("Time taken: " + duration.toSeconds() + " sec");
    }
}
