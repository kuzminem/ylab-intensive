package io.ylab.intensive.lesson03.filesort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sorter {
    public File sortFile(File dataFile) throws IOException {
        final int NUMBER_OF_FILES = 2;
        final int dataFileLines = countLines(dataFile);
        final String folder = dataFile.getParent();

        List<TempFile> tempFiles = separate(dataFile, dataFileLines, folder, NUMBER_OF_FILES);

        return collect(tempFiles, folder + "/sorted.txt", dataFileLines);
    }

    private int countLines(File dataFile) throws IOException {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            while (reader.readLine() != null) {
                counter++;
            }
        }
        return counter;
    }

    private List<TempFile> separate(File dataFile, int dataFileLines, String folder, int filesNumber) throws IOException {
        List<TempFile> tempFiles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            int tempFileLines = dataFileLines / filesNumber;
            for (int i = 0; i < filesNumber - 1; i++) {
                tempFiles.add(new TempFile(folder + "/" + i + ".txt",
                        readList(reader, tempFileLines)));
            }
            tempFileLines = dataFileLines - tempFileLines * (filesNumber - 1);
            tempFiles.add(new TempFile(folder + "/" + (filesNumber - 1) + ".txt",
                    readList(reader, tempFileLines)));
        }
        return tempFiles;
    }

    private List<Long> readList(BufferedReader reader, int tempFileLines) throws IOException {
        List<Long> list = new ArrayList<>(tempFileLines * 2);
        for (int i = 0; i < tempFileLines; i++) {
            String line = reader.readLine();
            list.add(Long.parseLong(line));
        }
        return list;
    }

    private File collect(List<TempFile> tempFiles, String sortedFileName, int sortedFileLines) throws IOException {
        File sortedFile = new File(sortedFileName);
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(sortedFile))) {
            for (int i = 0; i < sortedFileLines; i++) {
                TempFile file = tempFiles.stream()
                        .filter(TempFile::isReady)
                        .min(Comparator.comparingLong(TempFile::getValue))
                        .get();
                writer.write(file.getValue() + "\r\n");
                file.read();
            }
        }
        for (TempFile f : tempFiles) {
            f.delete();
        }
        return sortedFile;
    }
}
