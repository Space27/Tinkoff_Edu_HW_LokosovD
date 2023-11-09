package edu.hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Task3 {

    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(ArrayList<T> list) throws IllegalArgumentException {
        if (list == null) {
            throw new IllegalArgumentException("List can't be null");
        }

        HashMap<T, Integer> map = new HashMap<>();

        for (var i : list) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        return map;
    }
}
