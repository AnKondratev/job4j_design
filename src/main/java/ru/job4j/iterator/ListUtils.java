package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index + 1) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        for (T t : list) {
            if (filter.test(t)) {
                list.remove(t);
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        for (int i = 0; i < list.size(); i++) {
            if (filter.test(list.get(i))) {
                list.set(i, value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        for (int i = 0; i < list.size(); i++) {
            for (T element : elements) {
                if (list.get(i).equals(element)) {
                    list.remove(i);
                }
            }
        }
    }
}