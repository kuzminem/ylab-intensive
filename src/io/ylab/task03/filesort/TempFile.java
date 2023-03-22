package io.ylab.task03.filesort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TempFile {
    private final String filePath;
    private final BufferedReader reader;
    private long value;
    private boolean isReady = true;

    TempFile(String filePath) throws IOException {
        this.filePath = filePath;
        this.reader = new BufferedReader(new FileReader(this.filePath));
        this.value = Long.parseLong(reader.readLine());
    }

    public long getValue() {
        return this.value;
    }

    public boolean isReady() {
        return this.isReady;
    }

    public void read() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            this.isReady = false;
        } else {
            this.value = Long.parseLong(line);
        }
    }

    public void delete() throws IOException {
        this.reader.close();
        File file = new File(this.filePath);
        if (!file.delete()) {
            System.out.println("Ошибка при удалении файла " + this.filePath);
        }
    }
}
