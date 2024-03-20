package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int sizeInput;
    private int sizeOutput;

    public T poll() {
        try {
            sizeInput--;
            return input.pop();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Queue is empty");
        }
    }

    public void push(T value) {
        while (sizeInput > 0) {
            output.push(input.pop());
            sizeInput--;
            sizeOutput++;
        }
        input.push(value);
        sizeInput++;
        while (sizeOutput > 0) {
            input.push(output.pop());
            sizeOutput--;
            sizeInput++;
        }
    }
}