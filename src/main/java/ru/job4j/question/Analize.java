package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.StrictMath.toIntExact;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> previousMap = previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        Map<Integer, String> currentMap = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));

        int added = toIntExact(current.stream()
                .filter(user -> !previousMap.containsKey(user.getId()))
                .count());
        int changed = toIntExact(previous.stream()
                .filter(user -> !user.getName()
                        .equals(currentMap.getOrDefault(user.getId(), user.getName())))
                .count());
        int deleted = toIntExact(previous.stream()
                .filter(user -> !currentMap.containsKey(user.getId()))
                .count());

        return new Info(added, changed, deleted);
    }
}
