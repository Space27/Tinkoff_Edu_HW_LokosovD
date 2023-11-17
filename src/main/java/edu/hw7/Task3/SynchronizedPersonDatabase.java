package edu.hw7.Task3;

import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

public class SynchronizedPersonDatabase implements PersonDatabase {

    private final Set<Person> database;

    public SynchronizedPersonDatabase() {
        database = new HashSet<>();
    }

    @Override
    public synchronized void add(Person person) {
        database.add(person);
    }

    @Override
    public synchronized void delete(int id) {
        database.removeIf(person -> person.id() == id);
    }

    @Override
    @Nullable
    public synchronized Person findByName(String name) {
        return database.stream()
            .filter(person -> person.name().equals(name))
            .findFirst()
            .orElse(null);
    }

    @Override
    @Nullable
    public synchronized Person findByAddress(String address) {
        return database.stream()
            .filter(person -> person.address().equals(address))
            .findFirst()
            .orElse(null);
    }

    @Override
    @Nullable
    public synchronized Person findByPhone(String phone) {
        return database.stream()
            .filter(person -> person.phoneNumber().equals(phone))
            .findFirst()
            .orElse(null);
    }
}
