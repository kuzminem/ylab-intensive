package io.ylab.task03.filesort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class WriteTempFile {
    private final String fileName;

    WriteTempFile(String fileName, List<Long> list) throws IOException {
        this.fileName = fileName;
        Collections.sort(list);
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(this.fileName))) {
            for (Long l : list) {
                writer.write(l + "\r\n");
            }
        }
    }

    public String getFileName() {
        return this.fileName;
    }
}
