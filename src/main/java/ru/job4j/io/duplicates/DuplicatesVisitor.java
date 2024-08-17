package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<String, FileProperty> allFiles = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        BasicFileAttributes info = Files.readAttributes(file, BasicFileAttributes.class);
        allFiles.put(file.toString(), new FileProperty(info.size(), file.getFileName().toString()));
        return FileVisitResult.CONTINUE;
    }

    public Map<String, FileProperty> getAllFiles() {
        return allFiles;
    }
}