package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    public T[] dynamicArray() {
        return Arrays.copyOf(container,
                container.length > 0 ? container.length * 2 : 1);
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = dynamicArray();
        }
        container[size++] = value;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T result = container[index];
        container[index] = newValue;
        return result;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        T result = container[index];
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                size() - index - 1
        );
        container[size - 1] = null;
        return result;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            private int index;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}