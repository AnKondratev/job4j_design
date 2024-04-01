package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private int index(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    @Override
    public boolean put(K key, V value) {
        if ((count * 1.0 / capacity) >= LOAD_FACTOR) {
            expand();
        }
        int index = index(key);
        boolean result = false;
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private void expand() {
        capacity = capacity * 2;
        var newTable = new MapEntry[capacity];
        Arrays.stream(table).
                filter(Objects::nonNull).
                forEach(e -> newTable[index(e.key)] = e);
        table = newTable;
    }

    private boolean keyEqualityCheck(K key) {
        int index = index(key);
        return (key == null && table[index].key == null)
                || (key != null && key.equals(table[index].key));
    }

    @Override
    public V get(K key) {
        int index = index(key);
        V result = null;
        if (table[index] != null) {
            if (keyEqualityCheck(key)) {
                result = table[index].value;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        int index = index(key);
        boolean result = false;
        if (table[index] != null) {
            if (keyEqualityCheck(key)) {
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