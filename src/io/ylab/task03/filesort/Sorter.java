package io.ylab.task03.filesort;

import java.io.File;
import java.io.IOException;

public class Sorter {
    public File sortFile(File dataFile) throws IOException {
        final int NUMBER_OF_PARTS = 2;

        FileSort fileSort = new FileSort(dataFile, NUMBER_OF_PARTS);

        return fileSort.getSortedFile();
    }
}
