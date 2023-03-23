package io.ylab.task03.filesort;

import java.io.File;
import java.io.IOException;

public class Sorter {
    public File sortFile(File dataFile) throws IOException {
        final int NUMBER_OF_PARTS = 4;

        Separator separator = new Separator(dataFile, NUMBER_OF_PARTS);

        Collector collector = new Collector(separator);

        return collector.getSortedFile();
    }
}
