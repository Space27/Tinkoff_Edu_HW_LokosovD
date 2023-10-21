package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;

public final class Task5 {

    private Task5() {
    }

    public enum Order {
        ASC,
        DESC
    }

    public static String[] parseContacts(String[] contacts, Order order) {
        ArrayList<String> contactsList = new ArrayList<>(Arrays.asList(contacts));

        contactsList.sort((s1, s2) -> {
            String s1Cmp = s1;
            String s2Cmp = s2;

            if (s1Cmp == null) {
                return order == Order.ASC ? -1 : 1;
            } else if (s1Cmp.split(" ").length == 2) {
                s1Cmp = s1Cmp.split(" ")[1];
            } else {
                s1Cmp = s1Cmp.split(" ")[0];
            }

            if (s2Cmp == null) {
                return order == Order.ASC ? 1 : -1;
            } else if (s2Cmp.split(" ").length == 2) {
                s2Cmp = s2Cmp.split(" ")[1];
            } else {
                s2Cmp = s2Cmp.split(" ")[0];
            }

            return order == Order.ASC ? s1Cmp.compareTo(s2Cmp) : s2Cmp.compareTo(s1Cmp);
        });

        return contactsList.toArray(new String[0]);
    }
}
