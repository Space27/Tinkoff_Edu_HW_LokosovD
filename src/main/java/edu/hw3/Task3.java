package edu.hw3;

import java.util.ArrayList;
import java.util.HashMap;

public final class Task3 {

    private Task3() {
    }

    public static HashMap<Object, Integer> freqDict(ArrayList<Object> list) throws IllegalArgumentException {
        if (list == null) {
            throw new IllegalArgumentException("List can't be null");
        }

        HashMap<Object, Integer> map = new HashMap<>();

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
