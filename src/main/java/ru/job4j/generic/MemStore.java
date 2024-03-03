package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;


public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.put(model.getId(), storage.getOrDefault(model.getId(), model));
    }

    @Override
    public boolean replace(String id, T model) {
        storage.replace(id, model);
        return storage.containsKey(id);
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public T findById(String id) {
        return storage.getOrDefault(id, null);
    }
}