package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Balancer {

    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int index = 0;
        while (source.hasNext()) {
            nodes.get(index).add(source.next());
            if (!source.equals(Collections.emptyIterator()) && nodes.size() > 1) {
                index++;
                index = index >= nodes.size() ? 0 : index;
            }
        }
    }
}
