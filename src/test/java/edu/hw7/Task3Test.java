package edu.hw7;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import edu.hw7.Task3.SynchronizedPersonDatabase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {

    private static final List<Person> TEST_LIST = List.of(
        new Person(1, "James Hetfield", "Metallica", "Cool"),
        new Person(0, "Dimebag Darrell", "Pantera", "RIP"),
        new Person(7, "Mick Thomson", "Slipknot", "Seven"),
        new Person(3, "Jeff Waters", "Annihilator", "Master"),
        new Person(4, "John Bonham", "Led Zeppelin", "Legend"),
        new Person(5, "Till Lindemann", "Rammstein", "Crazy")
    );

    @Test
    @DisplayName("Тест добавления в однопотоке")
    void add_shouldAddNewElements() {
        PersonDatabase personDatabase = new SynchronizedPersonDatabase();

        for (var i : TEST_LIST) {
            personDatabase.add(i);
            personDatabase.add(i);
        }

        for (var i : TEST_LIST) {
            assertThat(i)
                .isEqualTo(personDatabase.findByName(i.name()))
                .isEqualTo(personDatabase.findByPhone(i.phoneNumber()))
                .isEqualTo(personDatabase.findByAddress(i.address()));
        }
    }

    @Test
    @DisplayName("Тест удаления в однопотоке")
    void delete_shouldDeleteElementForId() {
        PersonDatabase personDatabase = new SynchronizedPersonDatabase();

        for (var i : TEST_LIST) {
            personDatabase.add(i);
        }

        for (var i : TEST_LIST) {
            personDatabase.delete(i.id());
            assertThat(personDatabase.findByName(i.name()))
                .isNull();
        }
    }

    @Test
    @DisplayName("Тест добавления в многопотоке")
    void add_shouldAddNewElementsInParallel() {
        PersonDatabase personDatabase = new SynchronizedPersonDatabase();
        Thread leftThread = new Thread(() -> {
            for (int i = 0; i <= TEST_LIST.size() / 2; ++i) {
                personDatabase.add(TEST_LIST.get(i));
            }
        });
        Thread rightThread = new Thread(() -> {
            for (int i = TEST_LIST.size() - 1; i > TEST_LIST.size() / 2; --i) {
                personDatabase.add(TEST_LIST.get(i));
            }
        });

        leftThread.start();
        rightThread.start();

        try {
            leftThread.join();
            rightThread.join();
        } catch (InterruptedException ignored) {
        }

        for (var i : TEST_LIST) {
            assertThat(i)
                .isEqualTo(personDatabase.findByName(i.name()))
                .isEqualTo(personDatabase.findByPhone(i.phoneNumber()))
                .isEqualTo(personDatabase.findByAddress(i.address()));
        }
    }

    @Test
    @DisplayName("Тест удаления в многопотоке")
    void delete_shouldDeleteElementsInParallel() {
        PersonDatabase personDatabase = new SynchronizedPersonDatabase();
        Thread leftThread = new Thread(() -> {
            for (int i = 0; i <= TEST_LIST.size() / 2; ++i) {
                personDatabase.delete(TEST_LIST.get(i).id());
            }
        });
        Thread rightThread = new Thread(() -> {
            for (int i = TEST_LIST.size() - 1; i > TEST_LIST.size() / 2; --i) {
                personDatabase.delete(TEST_LIST.get(i).id());
            }
        });

        for (var i : TEST_LIST) {
            personDatabase.add(i);
        }

        leftThread.start();
        rightThread.start();

        try {
            leftThread.join();
            rightThread.join();
        } catch (InterruptedException ignored) {
        }

        for (var i : TEST_LIST) {
            assertThat(personDatabase.findByName(i.name()))
                .isNull();
        }
    }

    @Test
    @DisplayName("Добавление и удаление одних элементов")
    void delete_shouldInParallelWithAdd() {
        PersonDatabase personDatabase = new SynchronizedPersonDatabase();
        Thread leftThread = new Thread(() -> {
            for (Person person : TEST_LIST) {
                personDatabase.add(person);
            }
        });
        Thread rightThread = new Thread(() -> {
            for (Person person : TEST_LIST) {
                personDatabase.delete(person.id());
            }
        });

        leftThread.start();
        rightThread.start();

        try {
            leftThread.join();
            rightThread.join();
        } catch (InterruptedException ignored) {
        }

        for (var i : TEST_LIST) {
            assertThat(personDatabase.findByName(i.name()))
                .isNull();
        }
    }

    @Test
    @DisplayName("Поиск в многопотоке")
    void find_shouldReturnSameResultAsOtherFinds() {
        PersonDatabase personDatabase = new SynchronizedPersonDatabase();
        Thread thread1 = new Thread(() -> {
            for (Person person : TEST_LIST) {
                assertThat(personDatabase.findByName(person.name()))
                    .isEqualTo(personDatabase.findByAddress(person.address()))
                    .isEqualTo(personDatabase.findByPhone(person.phoneNumber()));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (Person person : TEST_LIST) {
                assertThat(personDatabase.findByAddress(person.address()))
                    .isEqualTo(personDatabase.findByPhone(person.phoneNumber()))
                    .isEqualTo(personDatabase.findByName(person.name()));
            }
        });
        Thread thread3 = new Thread(() -> {
            for (Person person : TEST_LIST) {
                assertThat(personDatabase.findByPhone(person.phoneNumber()))
                    .isEqualTo(personDatabase.findByName(person.name()))
                    .isEqualTo(personDatabase.findByAddress(person.address()));
            }
        });

        for (int i = 0; i < TEST_LIST.size() / 2; ++i) {
            personDatabase.add(TEST_LIST.get(i));
        }

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException ignored) {
        }
    }

    @Test
    @DisplayName("Поиск с добавлением в многопотоке")
    void add_shouldWorkWithFindInParallel() {
        PersonDatabase personDatabase = new SynchronizedPersonDatabase();
        Thread thread1 = new Thread(() -> {
            for (Person person : TEST_LIST) {
                assertThat(personDatabase.findByName(person.name()))
                    .isEqualTo(personDatabase.findByAddress(person.address()))
                    .isEqualTo(personDatabase.findByPhone(person.phoneNumber()));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (Person person : TEST_LIST) {
                personDatabase.add(person);
            }
        });

        thread2.start();
        thread1.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ignored) {
        }
    }
}
