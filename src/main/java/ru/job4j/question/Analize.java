package ru.job4j.question;

import java.util.HashMap;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        HashMap<Integer, String> previousMap = new HashMap<>();
        previous.forEach(user -> previousMap.put(user.getId(), user.getName()));
        HashMap<Integer, String> currentMap = new HashMap<>();
        current.forEach(user -> currentMap.put(user.getId(), user.getName()));

        for (User user : current) {
            if (!previousMap.containsKey(user.getId())) {
                added++;
            }
        }

        for (User user : previous) {
            if (!user.getName().equals(currentMap.getOrDefault(user.getId(), user.getName()))) {
                changed++;
            }
            if (!currentMap.containsKey(user.getId())) {
                deleted++;
            }
        }

        return new Info(added, changed, deleted);
    }
}
