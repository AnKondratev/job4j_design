package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validation(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validation(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Error: Expected 2 arguments: ROOT_FOLDER and FILE_EXTENSION.");
        }
        Path rootPath = Paths.get(args[0]);
        if (!Files.isDirectory(rootPath)) {
            throw new IllegalArgumentException("Error: the specified path '" + args[0] + "' is not a directory.");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Error: the file extension must start with a dot.");
        }
    }
}