package edu.hw3.Task5;

import org.jetbrains.annotations.NotNull;

public class Contact implements Comparable<Contact> {

    private final String name;
    private final String surName;

    public Contact(String fullName) {
        if (fullName == null) {
            this.name = "";
            this.surName = "";
        } else {
            String[] splitName = fullName.split(" ");

            this.name = splitName[0];
            this.surName = splitName.length > 1 ? splitName[1] : "";
        }
    }

    @Override
    public int compareTo(@NotNull Contact o) {
        return (!surName.isEmpty() ? surName : name).compareTo(!o.surName.isEmpty() ? o.surName : o.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Contact o = (Contact) obj;

        return name.equals(o.name) && surName.equals(o.surName);
    }

    @Override
    public int hashCode() {
        final int prime = 96717037;

        return (2 * name.hashCode() + surName.hashCode()) % prime;
    }
}
