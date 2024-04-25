package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        var result = new ArrayList<String>();
        try (BufferedReader input = new BufferedReader(
                new FileReader("data/log.txt")
        )) {
            for (String line : input.lines().toList()) {
                String[] words = line.split(" ");
                if ("404".equals(words[words.length - 2])) {
                    result.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("data/out.txt")
                ))) {
            data.forEach(line -> output.printf("%s%n", line));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/out.txt");

    }
}