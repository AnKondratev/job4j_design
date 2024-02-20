package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public boolean checkOutOfBounds() {
        return index < data.length;
    }

    public boolean oddNumber() {
        if (checkOutOfBounds()) {
            return data[index] % 2 != 0;
        }
        return true;
    }

    @Override
    public boolean hasNext() {
        while (oddNumber()) {
            index++;
            if (!checkOutOfBounds()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (oddNumber()) {
            index++;
        }
        return data[index++];
    }
}