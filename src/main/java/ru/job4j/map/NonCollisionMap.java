package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (((double) count / capacity) >= LOAD_FACTOR) {
            expand();
        }
        boolean result = false;
        if (table[indexFor(hash(Objects.hashCode(key)))] == null) {
            table[indexFor(hash(Objects.hashCode(key)))] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity = capacity == 0 ? 1 : capacity * 2;
        var newTable = new MapEntry[capacity];
        Arrays.stream(table).
                filter(Objects::nonNull).
                forEach(e -> newTable[indexFor(hash(Objects.
                        hashCode(e.key)))] = e);
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        V result = null;
        if (table[index] != null) {
            if ((key == null && table[index].key == null)
                    || (key != null && key.equals(table[index].key))) {
                result = table[index].value;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        boolean result = false;
        if (table[index] != null) {
            if ((key == null && table[index].key == null)
                    || (key != null && key.equals(table[index].key))) {
                table[index] = null;
                count--;
                modCount++;
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}