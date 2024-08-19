package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<String> duplicates = new ArrayList<>();
    private Map<String, List<Path>> fileMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        String key = attributes.size() + file.getFileName().toString();
        fileMap.putIfAbsent(key, new ArrayList<>());
        fileMap.get(key).add(file);
        if (fileMap.get(key).size() > 1) {
            for (Path duplicate : fileMap.get(key)) {
                if (!duplicates.contains(duplicate.toAbsolutePath().toString())) {
                    duplicates.add(duplicate.toAbsolutePath().toString());
                }
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public List<String> getDuplicates() {
        return duplicates;
    }
}