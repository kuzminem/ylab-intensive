package io.ylab.task03.filesort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Separator {
    private final String folder;
    private final int filesNumber;
    private final int sourceLines;

    Separator(File sourceFile, int filesNumber) throws IOException {
        String path = sourceFile.getPath();
        this.folder = path.substring(0, path.lastIndexOf('\\') + 1);
        this.filesNumber = filesNumber;
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            while (reader.readLine() != null) {
                counter++;
            }
        }
        this.sourceLines = counter;

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            int targetLines = this.sourceLines / this.filesNumber;
            int capacity = (int) (targetLines * 1.4);

            for (int i = 0; i < this.filesNumber - 1; i++) {
                List<Long> lines = new ArrayList<>(capacity);
                for (int j = 0; j < targetLines; j++) {
                    String line = reader.readLine();
                    lines.add(Long.parseLong(line));
                }
                Collections.sort(lines);
                try (BufferedWriter writer = new BufferedWriter(
                        new FileWriter(this.folder + i + ".txt"))) {
                    for (Long l : lines) {
                        writer.write(l + "\r\n");
                    }
                }
            }

            List<Long> lines = new ArrayList<>(capacity);
            int lastTargetLines = this.sourceLines - targetLines * (this.filesNumber - 1);
            for (int j = 0; j < lastTargetLines; j++) {
                String line = reader.readLine();
                lines.add(Long.parseLong(line));
            }
            Collections.sort(lines);
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(this.folder + (this.filesNumber - 1) + ".txt"))) {
                for (Long l : lines) {
                    writer.write(l + "\r\n");
                }
            }
        }
    }

    public String getFolder() {
        return this.folder;
    }

    public int getFilesNumber() {
        return filesNumber;
    }

    public int getSourceLines() {
        return this.sourceLines;
    }
}
