package edu.hw10.Task1;

import edu.hw10.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {

    private final RandomObjectGenerator rog = new RandomObjectGenerator();

    @Test
    @DisplayName("Record стандартный конструктор")
    void nextObject_shouldCreateNewRecordWithStandardConstructor() {
        Class<?> testClass = SimpleRecord.class;

        List<SimpleRecord> test = Stream.generate(() -> (SimpleRecord) rog.nextObject(testClass))
            .limit(100)
            .toList();

        assertThat(test)
            .doesNotContainNull()
            .isNotEqualTo(test.reversed())
            .anyMatch(record -> record.b() == null);
    }

    @Test
    @DisplayName("Record фабричный метод")
    void nextObject_shouldCreateNewRecordWithFabricMethod() {
        Class<?> testClass = SimpleRecord.class;

        List<SimpleRecord> test = Stream.generate(() -> (SimpleRecord) rog.nextObject(testClass, "create"))
            .limit(100)
            .toList();

        assertThat(test)
            .doesNotContainNull()
            .isNotEqualTo(test.reversed())
            .anyMatch(record -> record.b() == null);
    }

    @Test
    @DisplayName("Record конструктор с аннотациями")
    void nextObject_shouldCreateNewRecordWithConstructorWithAnnotations() {
        Class<?> testClass = AnnotationRecord.class;

        List<AnnotationRecord> test = Stream.generate(() -> (AnnotationRecord) rog.nextObject(testClass))
            .limit(100)
            .toList();

        assertThat(test)
            .doesNotContainNull()
            .isNotEqualTo(test.reversed());

        for (AnnotationRecord record : test) {
            assertThat(record.b())
                .isNotNull();
            assertThat(record.a())
                .isBetween(-5, 6);
        }
    }

    @Test
    @DisplayName("Record с несуществующим фабричным методом")
    void nextObject_shouldCreateNewRecordWithNotExistingFabricMethod() {
        Class<?> testClass = AnnotationRecord.class;

        AnnotationRecord record = (AnnotationRecord) rog.nextObject(testClass, "create");

        assertThat(record)
            .isNotNull();
    }

    @Test
    @DisplayName("Пустой класс")
    void nextObject_shouldCreateNewClassIfItEmpty() {
        Class<?> testClass = EmptyClass.class;

        EmptyClass emptyClass = (EmptyClass) rog.nextObject(testClass);

        assertThat(emptyClass)
            .isNotNull();
    }

    @Test
    @DisplayName("Класс с необрабатываемым типом")
    void nextObject_shouldCreateNewClassIfItHasNotHandledTypes() {
        Class<?> testClass = UndefinedTypeClass.class;

        UndefinedTypeClass undefinedTypeClass = (UndefinedTypeClass) rog.nextObject(testClass);

        assertThat(undefinedTypeClass)
            .isNotNull();
    }

    @Test
    @DisplayName("Class конструктор с аннотациями")
    void nextObject_shouldCreateNewClassWithConstructorWithAnnotations() {
        Class<?> testClass = AnnotationsClass.class;

        List<AnnotationsClass> test = Stream.generate(() -> (AnnotationsClass) rog.nextObject(testClass))
            .limit(100)
            .toList();

        assertThat(test)
            .doesNotContainNull()
            .isNotEqualTo(test.reversed());

        for ( AnnotationsClass i : test) {
            assertThat(i.getB())
                .isNotNull();
            assertThat(i.getA())
                .isGreaterThan(0);
        }
    }
}
