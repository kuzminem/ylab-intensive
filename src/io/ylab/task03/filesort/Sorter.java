package io.ylab.task03.filesort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Sorter {
    public File sortFile(File dataFile) throws IOException {

        final int NUMBER_OF_FILES = 10;
        final String RESOURCES = "src/io/ylab/task03/filesort/resources/";
        
        int sourceLines;
        try (Stream<String> fileStream = Files.lines(dataFile.toPath())) {
            sourceLines = (int) fileStream.count();
        }
        int targetLines = (int) Math.ceil((double) sourceLines / NUMBER_OF_FILES);

        try (BufferedReader sourceReader = new BufferedReader(new FileReader(dataFile))) {
            for (int i = 0; i < NUMBER_OF_FILES; i++) {
                List<Long> lines = new ArrayList<>(targetLines);
                for (int j = 0; j < targetLines; j++) {
                    String line = sourceReader.readLine();
                    if (line == null) {
                        break;
                    }
                    lines.add(Long.parseLong(line));
                }
                Collections.sort(lines);

                try (BufferedWriter targetWriter = new BufferedWriter(new FileWriter(RESOURCES + i + ".txt"))) {
                    for (Long l : lines) {
                        targetWriter.write(l + "\n");
                    }
                }
            }
        }

        try (BufferedWriter sortedWriter = new BufferedWriter(new FileWriter(RESOURCES + "sorted.txt"))) {

            BufferedReader[] readers = new BufferedReader[NUMBER_OF_FILES];
            for (int i = 0; i < NUMBER_OF_FILES; i++) {
                readers[i] = new BufferedReader(new FileReader(RESOURCES + i + ".txt"));
            }

            long[] tempLine = new long[NUMBER_OF_FILES];
            boolean[] isOpen = new boolean[NUMBER_OF_FILES];
            for (int i = 0; i < NUMBER_OF_FILES; i++) {
                tempLine[i] = Long.parseLong(readers[i].readLine());
                isOpen[i] = true;
            }

            for (int i = 0; i < sourceLines; i++) {
                int fileNumber = indexMin(tempLine, isOpen);
                sortedWriter.write(tempLine[fileNumber] + "\r\n");
                String line = readers[fileNumber].readLine();
                if (line == null) {
                    isOpen[fileNumber] = false;
                } else {
                    tempLine[fileNumber] = Long.parseLong(line);
                }
            }

            for (int i = 0; i < NUMBER_OF_FILES; i++) {
                readers[i].close();
                File file = new File(RESOURCES + i + ".txt");
                if (!file.delete()) {
                    System.out.println("Ошибка при удалении файла " + i + ".txt");
                }
            }
        }

        return new File(RESOURCES + "sorted.txt");
    }

    static int indexMin(long[] array, boolean[] isOpen) {
        int min = 0;
        for (int i = 0; i < array.length; i++) {
            if (isOpen[i]) {
                min = i;
                break;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (isOpen[i] && array[i] < array[min]) {
                min = i;
            }
        }
        return min;
    }
}
