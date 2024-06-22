package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void printDirectoryContents(File directory) {
        if (!directory.exists()) {
            throw new IllegalArgumentException(String.format(
                    "Not exist %s", directory.getAbsoluteFile()
            ));
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "Not directory %s", directory.getAbsoluteFile()
            ));
        }
        System.out.println("Directory: " + directory.getName());
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            System.out.printf("File/Folder: %s, Size: %s bytes."
                    + System.lineSeparator(), file.getName(), file.length());
        }
    }

    public static void main(String[] args) {
        printDirectoryContents(new File("c:\\projects"));
    }
}
