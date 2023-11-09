package edu.hw3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class Task2 {

    private Task2() {
    }

    private static final String ERROR_MSG = "There are unbalanced clusters in the string";

    public static List<String> clusterize(String string) throws IllegalArgumentException {
        if (string == null) {
            throw new IllegalArgumentException("String can't be null");
        }

        Deque<Character> stack = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        char[] chrArr = string.toCharArray();
        StringBuilder tmpRes = new StringBuilder();
        boolean stackWasChanged = false;

        for (char i : chrArr) {
            if (i == '(') {
                if (stack.isEmpty() && stackWasChanged) {
                    result.add(String.valueOf(tmpRes));
                    tmpRes = new StringBuilder();
                }
                stack.push(i);
                stackWasChanged = true;
            } else if (i == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    throw new IllegalArgumentException(ERROR_MSG);
                }
            }
            tmpRes.append(i);
        }
        if (stack.isEmpty() && !tmpRes.isEmpty()) {
            result.add(tmpRes.toString());
        } else if (!stack.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MSG);
        }

        return result;
    }
}
