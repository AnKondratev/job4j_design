package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        list.listIterator(index).add(value);
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        list.listIterator(index + 1).add(value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        while (list.listIterator().hasNext()) {
            if (filter.test(list.listIterator().next())) {
                list.listIterator().remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        while (list.listIterator().hasNext()) {
            if (filter.test(list.listIterator().next())) {
                list.listIterator().set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        removeIf(list, elements::contains);
    }
}