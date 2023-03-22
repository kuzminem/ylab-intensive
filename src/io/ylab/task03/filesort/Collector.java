package io.ylab.task03.filesort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Collector {
    Collector(Separator separator) throws IOException {
        final int filesNumber = separator.getFilesNumber();
        final String folder = separator.getFolder();

        TempFile[] tempFiles = new TempFile[filesNumber];
        for (int i = 0; i < filesNumber; i++) {
            tempFiles[i] = new TempFile(folder + i + ".txt");
        }

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(folder + "sorted.txt"))) {
            for (int i = 0; i < separator.getSourceLines(); i++) {
                int index = getIndex(tempFiles);
                writer.write(tempFiles[index].getValue() + "\r\n");
                tempFiles[index].read();
            }
        }

        for (TempFile tempFile : tempFiles) {
            tempFile.delete();
        }
    }

    private int getIndex(TempFile[] tempFiles) {
        int indexMin = 0;
        for (int i = 0; i < tempFiles.length; i++) {
            if (tempFiles[i].isReady()) {
                indexMin = i;
                break;
            }
        }
        for (int i = 0; i < tempFiles.length; i++) {
            if (tempFiles[i].isReady()
                    && tempFiles[i].getValue() < tempFiles[indexMin].getValue()) {
                indexMin = i;
            }
        }
        return indexMin;
    }
}
