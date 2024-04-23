package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        var resultList = new ArrayList<String>();
        try (BufferedReader input = new BufferedReader(new FileReader("data/log.txt"))) {
            input.lines()
                    .filter(line -> Arrays.asList(line.split(" ")).contains("404"))
                    .forEach(resultList::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}