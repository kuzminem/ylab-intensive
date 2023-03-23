package io.ylab.task03.filesort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileSort {
    private final String sortedFileName;

    FileSort(File sourceFile, int filesNumber) throws IOException {
        List<TempFile> tempFiles = new ArrayList<>();

        String folder = sourceFile.getPath()
                .substring(0, sourceFile.getPath().lastIndexOf('\\') + 1);

        int sourceLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            while (reader.readLine() != null) {
                sourceLines++;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            int targetLines = sourceLines / filesNumber;
            int capacity = (int) (targetLines * 1.4);

            for (int i = 0; i < filesNumber - 1; i++) {
                List<Long> longList = new ArrayList<>(capacity);
                for (int j = 0; j < targetLines; j++) {
                    String line = reader.readLine();
                    longList.add(Long.parseLong(line));
                }
                tempFiles.add(new TempFile(folder + i + ".txt", longList));
            }

            List<Long> longList = new ArrayList<>(capacity);
            int lastTargetLines = sourceLines - targetLines * (filesNumber - 1);
            for (int i = 0; i < lastTargetLines; i++) {
                String line = reader.readLine();
                longList.add(Long.parseLong(line));
            }
            tempFiles.add(
                    new TempFile(folder + (filesNumber - 1) + ".txt", longList));
        }

        this.sortedFileName = folder + "sorted.txt";

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(this.sortedFileName))) {
            for (int i = 0; i < sourceLines; i++) {
                TempFile file = tempFiles.stream()
                        .filter(TempFile::isReady)
                        .min(Comparator.comparingLong(TempFile::getValue))
                        .get();
                writer.write(file.getValue() + "\r\n");
                file.read();
            }
        }

        for (TempFile f : tempFiles) {
            f.delete();
        }
    }

    public File getSortedFile() {
        return new File(this.sortedFileName);
    }
}
