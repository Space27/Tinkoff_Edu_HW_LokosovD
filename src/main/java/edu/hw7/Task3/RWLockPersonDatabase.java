package edu.hw7.Task3;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class RWLockPersonDatabase implements PersonDatabase {

    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();
    private static final int SLEEP_DURATION = 5;
    private final Set<Person> database;

    public RWLockPersonDatabase() {
        database = new HashSet<>();
    }

    @Override
    public void add(Person person) {
        try {
            LOCK.writeLock().lock();
            database.add(person);
            Thread.sleep(SLEEP_DURATION);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        try {
            LOCK.writeLock().lock();
            database.removeIf(person -> person.id() == id);
            Thread.sleep(SLEEP_DURATION);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    @Override
    @Nullable
    public Person findByName(String name) {
        try {
            LOCK.readLock().lock();
            return database.stream()
                .filter(person -> person.name().equals(name))
                .findFirst()
                .orElse(null);
        } finally {
            LOCK.readLock().unlock();
        }
    }

    @Override
    @Nullable
    public Person findByAddress(String address) {
        try {
            LOCK.readLock().lock();
            return database.stream()
                .filter(person -> person.address().equals(address))
                .findFirst()
                .orElse(null);
        } finally {
            LOCK.readLock().unlock();
        }
    }

    @Override
    @Nullable
    public Person findByPhone(String phone) {
        try {
            LOCK.readLock().lock();
            return database.stream()
                .filter(person -> person.phoneNumber().equals(phone))
                .findFirst()
                .orElse(null);
        } finally {
            LOCK.readLock().unlock();
        }
    }
}
