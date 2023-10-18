package edu.hw3;

import java.util.ArrayList;
import java.util.Stack;

public final class Task2 {

    private Task2() {
    }

    public static ArrayList<String> clusterize(String string) throws IllegalArgumentException {
        if (string == null) {
            throw new IllegalArgumentException("String can't be null");
        }

        String errorMsg = "There are unbalanced clusters in the string";
        Stack<Character> stack = new Stack<>();
        ArrayList<String> result = new ArrayList<>();
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
                    throw new IllegalArgumentException(errorMsg);
                }
            }
            tmpRes.append(i);
        }
        if (stack.isEmpty()) {
            result.add(String.valueOf(tmpRes));
        } else {
            throw new IllegalArgumentException(errorMsg);
        }

        return result;
    }
}
