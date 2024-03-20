package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        ForwardLinked.Node<T> node = new ForwardLinked.Node<>(value, null);
        if (head == null) {
            head = node;
        } else {
            ForwardLinked.Node<T> node1 = head;
            while (node1.next != null) {
                node1 = node1.next;
            }
            node1.next = node;
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<T> node1 = head;
            head = new Node<>(value, null);
            head.next = node1;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        ForwardLinked.Node<T> node1 = head;
        for (int i = 0; i < index; i++) {
            node1 = node1.next;
        }
        return node1.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T headItem = head.item;
        Node<T> next = head.next;
        head.item = null;
        head.next = null;
        head = next;
        size--;
        modCount++;
        return headItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
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
                return get(index++);
            }
        };
    }

    private static class Node<T> {
        private T item;
        private ForwardLinked.Node<T> next;

        Node(T element, ForwardLinked.Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}