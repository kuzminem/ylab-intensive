package io.ylab.intensive.lesson03.filesort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TempFile {
    private final String fileName;
    private final BufferedReader reader;
    private long value;
    private boolean isReady = true;

    TempFile(String fileName, List<Long> list) throws IOException {
        this.fileName = fileName;
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(this.fileName))) {
            Collections.sort(list);
            for (Long l : list) {
                writer.write(l + "\r\n");
            }
        }

        this.reader = new BufferedReader(new FileReader(this.fileName));
        this.value = Long.parseLong(this.reader.readLine());
    }

    public long getValue() {
        return this.value;
    }

    public boolean isReady() {
        return this.isReady;
    }

    public void read() throws IOException {
        String line = this.reader.readLine();
        if (line == null) {
            this.isReady = false;
        } else {
            this.value = Long.parseLong(line);
        }
    }

    public void delete() throws IOException {
        this.reader.close();
        File file = new File(this.fileName);
        if (!file.delete()) {
            System.out.println("Ошибка при удалении файла " + this.fileName);
        }
    }
}
