package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task8Test {

    @Test
    @DisplayName("Запрос несуществующего элемента")
    void BackwardIterator_shouldNotIterateArraysIfLenEqualsZero() {
        ArrayList<Object> arrayList = new ArrayList<>();
        Iterator<Object> backwardIterator = new BackwardIterator<>(arrayList);

        assertThrows(NoSuchElementException.class, backwardIterator::next);
    }

    @ParameterizedTest
    @MethodSource("provideCollectionsWithOneElement")
    @DisplayName("В коллекции один элемент")
    void BackwardIterator_shouldIterateCollectionsWithOneElement(Collection<Object> collection) {
        Object[] resArr = new Object[collection.size()];
        Object[] expectedArr = new Object[collection.size()];
        Iterator<Object> backwardIterator = new BackwardIterator<>(collection);
        Iterator<Object> commonIterator = collection.iterator();

        for (int i = 0; i < collection.size(); ++i) {
            resArr[i] = backwardIterator.next();
            expectedArr[collection.size() - i - 1] = commonIterator.next();
        }

        assertThat(resArr)
            .isEqualTo(expectedArr);
    }

    @ParameterizedTest
    @MethodSource("provideCollectionsWithSeveralElements")
    @DisplayName("В коллекции несколько элементов")
    void BackwardIterator_shouldIterateCollectionsWithSeveralElements(Collection<Object> collection) {
        Object[] resArr = new Object[collection.size()];
        Object[] expectedArr = new Object[collection.size()];
        Iterator<Object> backwardIterator = new BackwardIterator<>(collection);
        Iterator<Object> commonIterator = collection.iterator();

        for (int i = 0; i < collection.size(); ++i) {
            resArr[i] = backwardIterator.next();
            expectedArr[collection.size() - i - 1] = commonIterator.next();
        }

        assertThat(resArr)
            .isEqualTo(expectedArr);
    }

    private static Stream<Arguments> provideCollectionsWithOneElement() {
        return Stream.of(
            Arguments.of(
                new ArrayList<Object>(List.of(1))
            ),
            Arguments.of(
                new ArrayList<Object>(List.of('c'))
            ),
            Arguments.of(
                new ArrayList<Object>(List.of("abc"))
            ),
            Arguments.of(
                new LinkedList<Object>(List.of(1))
            ),
            Arguments.of(
                new LinkedList<Object>(List.of('c'))
            ),
            Arguments.of(
                new LinkedList<Object>(List.of("abc"))
            ),
            Arguments.of(
                new PriorityQueue<>(List.of(1))
            ),
            Arguments.of(
                new PriorityQueue<>(List.of('c'))
            ),
            Arguments.of(
                new PriorityQueue<>(List.of("abc"))
            ),
            Arguments.of(
                new HashSet<>(List.of(1))
            ),
            Arguments.of(
                new HashSet<>(List.of('c'))
            ),
            Arguments.of(
                new HashSet<>(List.of("abc"))
            )
        );
    }

    private static Stream<Arguments> provideCollectionsWithSeveralElements() {
        return Stream.of(
            Arguments.of(
                new ArrayList<Object>(List.of(1, 2, 3, 4, 10, 100, -5, -20, 50, -100, 20, 15, -1000, 0))
            ),
            Arguments.of(
                new LinkedList<Object>(List.of(1, 2, 3, 4, 10, 100, -5, -20, 50, -100, 20, 15, -1000, 0))
            ),
            Arguments.of(
                new PriorityQueue<>(List.of(1, 2, 3, 4, 10, 100, -5, -20, 50, -100, 20, 15, -1000, 0))
            ),
            Arguments.of(
                new HashSet<>(List.of(1, 2, 3, 4, 10, 100, -5, -20, 50, -100, 20, 15, -1000, 0))
            )
        );
    }
}
