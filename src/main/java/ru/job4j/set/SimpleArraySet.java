package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean isAdded = !contains(value);
        if (isAdded) {
            set.add(value);
        }
        return isAdded;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (T element : this) {
            if (Objects.equals(value, element)) {
                result = Objects.equals(value, element);
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}