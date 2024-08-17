package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get("D:/Photo");
        findDups(start);
    }

    public static void findDups(Path root) throws IOException {
        DuplicatesVisitor dupFinder = new DuplicatesVisitor();
        Files.walkFileTree(root, dupFinder);
        Map<String, FileProperty> duplicatesFiles = dupFinder.getAllFiles();
        List<String> filesToRemove = getStrings(duplicatesFiles);

        for (String keyToRemove : filesToRemove) {
            duplicatesFiles.remove(keyToRemove);
        }
        for (Map.Entry<String, FileProperty> entry : duplicatesFiles.entrySet()) {
            System.out.println("File: " + entry.getKey() + ", Size: " + entry.getValue().getSize());
        }

    }

    private static List<String> getStrings(Map<String, FileProperty> duplicatesFiles) {
        Map<String, Integer> valueCounts = new HashMap<>();
        List<String> filesToRemove = new ArrayList<>();

        for (Map.Entry<String, FileProperty> entry : duplicatesFiles.entrySet()) {
            String val = entry.getValue().getName() + entry.getValue().getSize();
            valueCounts.put(val, valueCounts.getOrDefault(val, 0) + 1);
        }

        for (Map.Entry<String, FileProperty> entry : duplicatesFiles.entrySet()) {
            String val = entry.getValue().getName() + entry.getValue().getSize();
            if (valueCounts.get(val) == 1) {
                filesToRemove.add(entry.getKey());
            }
        }
        return filesToRemove;
    }
}