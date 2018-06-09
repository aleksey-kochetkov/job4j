package ru.job4j.list;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class UserConvert {
    public Map<Integer, User> process(List<User> list) {
        Map<Integer, User> map = new HashMap<>();
        for (User u : list) {
            map.put(u.getId(), u);
        }
        return map;
    }
}
