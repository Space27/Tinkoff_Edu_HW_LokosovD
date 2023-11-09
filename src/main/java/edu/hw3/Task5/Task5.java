package edu.hw3.Task5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public final class Task5 {

    private Task5() {
    }

    public enum Order {
        ASC,
        DESC
    }

    public static Contact[] parseContacts(String[] contacts, Order order) {
        if (contacts == null) {
            return new Contact[0];
        }

        return Arrays.stream(contacts)
            .filter(Objects::nonNull)
            .map(Contact::new)
            .sorted(order == Order.ASC ? Comparator.naturalOrder() : Comparator.reverseOrder())
            .toArray(Contact[]::new);
    }
}
