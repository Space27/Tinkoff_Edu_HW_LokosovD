package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

class CellularTest {

    private final static MazeGenerator generator = new CellularAutomata();

    @ParameterizedTest
    @CsvSource({"0,0", "1,1", "-1,-1", "1,5", "5,1"})
    @DisplayName("Слишком маленькие размеры лабиринта")
    void generate_shouldReturnNullForIncorrectSides(int height, int width) {
        Maze maze = generator.generate(height, width);

        assertThat(maze)
            .isNull();
    }
}
