package io.ylab.task03.filesort;

import java.io.File;
import java.io.IOException;

public class Sorter {
    public File sortFile(File dataFile) throws IOException {
        final int NUMBER_OF_FILES = 2;

        Separator separator = new Separator(dataFile, NUMBER_OF_FILES);

        Collector collector = new Collector(separator);

        return new File(separator.getFolder() + "sorted.txt");
    }
}
