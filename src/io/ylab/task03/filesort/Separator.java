package io.ylab.task03.filesort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Separator {
    private final String folder;
    private final int sourceLines;
    private final List<WriteTempFile> writeTempFiles = new ArrayList<>();

    Separator(File sourceFile, int filesNumber) throws IOException {
        this.folder = sourceFile.getPath()
                .substring(0, sourceFile.getPath().lastIndexOf('\\') + 1);

        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            while (reader.readLine() != null) {
                counter++;
            }
        }
        this.sourceLines = counter;

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            int targetLines = this.sourceLines / filesNumber;
            int capacity = (int) (targetLines * 1.4);

            for (int i = 0; i < filesNumber - 1; i++) {
                List<Long> longList = new ArrayList<>(capacity);
                for (int j = 0; j < targetLines; j++) {
                    String line = reader.readLine();
                    longList.add(Long.parseLong(line));
                }
                this.writeTempFiles.add(new WriteTempFile(this.folder + i + ".txt", longList));
            }

            List<Long> longList = new ArrayList<>(capacity);
            int lastTargetLines = this.sourceLines - targetLines * (filesNumber - 1);
            for (int j = 0; j < lastTargetLines; j++) {
                String line = reader.readLine();
                longList.add(Long.parseLong(line));
            }
            this.writeTempFiles.add(
                    new WriteTempFile(this.folder + (filesNumber - 1) + ".txt", longList));
        }
    }

    public String getFolder() {
        return this.folder;
    }

    public int getSourceLines() {
        return this.sourceLines;
    }

    public List<WriteTempFile> getWriteTempFiles() {
        return this.writeTempFiles;
    }
}
