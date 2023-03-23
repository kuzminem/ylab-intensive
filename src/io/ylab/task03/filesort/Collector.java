package io.ylab.task03.filesort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Collector {
    private final List<ReadTempFile> readTempFiles = new ArrayList<>();
    private final String sortedFileName;

    Collector(Separator separator) throws IOException {
        for (WriteTempFile f : separator.getWriteTempFiles()) {
            this.readTempFiles.add(new ReadTempFile(f));
        }

        this.sortedFileName = separator.getFolder() + "sorted.txt";

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(this.sortedFileName))) {
            for (int i = 0; i < separator.getSourceLines(); i++) {
                writer.write(collectValue() + "\r\n");
            }
        }

        for (ReadTempFile f : readTempFiles) {
            f.delete();
        }
    }

    private Long collectValue() throws IOException {
        ReadTempFile file = this.readTempFiles.stream()
                .filter(ReadTempFile::isReady)
                .min(Comparator.comparingLong(ReadTempFile::getValue))
                .get();
        long min = file.getValue();
        file.read();
        return min;
    }

    public File getSortedFile() {
        return new File(this.sortedFileName);
    }
}
