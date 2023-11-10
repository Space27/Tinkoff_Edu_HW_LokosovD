package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {

    Path path = Path.of("src/test/java/edu/hw6/test.txt");

    @Test
    @DisplayName("При создании Map должен быть пустым")
    void DiskMap_ShouldBeEmptyWhenItInitialized() throws IOException {
        Map<String, String> map = new DiskMap(path);

        assertThat(map.size())
            .isZero();
        assertThat(map)
            .isEmpty();
    }

    @ParameterizedTest
    @CsvSource({"1,1", "0,0", "a,a", "a,b", "0,1", "a1,b0"})
    @DisplayName("При добавлении элемента размер должен увеличиться и должен появиться элемент")
    void put_ShouldIncSizeAndAppendNewElement(String key, String value) throws IOException {
        Map<String, String> map = new DiskMap(path);

        map.put(key, value);

        assertThat(map.size())
            .isEqualTo(1);
        assertThat(map.get(key))
            .isEqualTo(value);
        assertThat(map.containsKey(key))
            .isTrue();
        assertThat(map.containsValue(value))
            .isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,1", "0,0", "a,a", "a,b", "0,1", "a1,b0"})
    @DisplayName("При добавлении элемента дважды размер Map не должен измениться")
    void put_ShouldINotChangeIfAddElementInMap(String key, String value) throws IOException {
        Map<String, String> map = new DiskMap(path);

        map.put(key, value);
        map.put(key, value);

        assertThat(map.size())
            .isEqualTo(1);
        assertThat(map.get(key))
            .isEqualTo(value);
        assertThat(map.containsKey(key))
            .isTrue();
        assertThat(map.containsValue(value))
            .isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,1", "0,0", "a,a", "a,b", "0,1", "a1,b0"})
    @DisplayName("При удалении элемента Map должен стать пустым")
    void remove_ShouldBeEmptyIfElementWouldBeRemoved(String key, String value) throws IOException {
        Map<String, String> map = new DiskMap(path);

        map.put(key, value);
        map.remove(key);

        assertThat(map.isEmpty())
            .isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,1", "0,0", "a,a", "a,b", "0,1", "a1,b0"})
    @DisplayName("При удалении элемента с несуществующим ключом Map не должен измениться")
    void remove_ShouldNotChangeMapIfKeyIsNotInMap(String key, String value) throws IOException {
        Map<String, String> map = new DiskMap(path);

        map.put(key, value);
        map.remove(key + "0");

        assertThat(map.size())
            .isEqualTo(1);
        assertThat(map.get(key))
            .isEqualTo(value);
        assertThat(map.containsKey(key))
            .isTrue();
        assertThat(map.containsValue(value))
            .isTrue();
    }

    @Test
    @DisplayName("Добавление нескольких элементов")
    void putAll_ShouldAppendSeveralElements() throws IOException {
        Map<String, String> map = new DiskMap(path);
        Map<String, String> inputMap = new HashMap<>(Map.of(
            "0", "1",
            "1", "1",
            "2", "1",
            "3", "2"
        ));

        map.putAll(inputMap);

        assertThat(map.size())
            .isEqualTo(4);
        assertThat(map.keySet())
            .containsExactlyInAnyOrder("0", "1", "2", "3");
        assertThat(map.values())
            .containsExactlyInAnyOrder("1", "1", "1", "2");
    }

    @Test
    @DisplayName("Удаление конкретного элемента")
    void remove_ShouldRemoveElement() throws IOException {
        Map<String, String> map = new DiskMap(path);
        Map<String, String> inputMap = new HashMap<>(Map.of(
            "0", "1",
            "1", "1",
            "2", "1",
            "3", "2"
        ));

        map.putAll(inputMap);
        map.remove("0");

        assertThat(map.size())
            .isEqualTo(3);
        assertThat(map.keySet())
            .containsExactlyInAnyOrder("1", "2", "3");
        assertThat(map.values())
            .containsExactlyInAnyOrder("1", "1", "2");
    }

    @Test
    @DisplayName("Очистка должна полностью освободить Map")
    void clear_ShouldMakeMapEmpty() throws IOException {
        Map<String, String> map = new DiskMap(path);
        Map<String, String> inputMap = new HashMap<>(Map.of(
            "0", "1",
            "1", "1",
            "2", "1",
            "3", "2"
        ));

        map.putAll(inputMap);

        map.clear();

        assertThat(map)
            .isEmpty();
        assertThat(map.entrySet())
            .isEmpty();
    }
}
