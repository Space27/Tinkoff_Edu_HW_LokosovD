package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

class PrimTest {

    private final static MazeGenerator generator = new PrimGenerator();

    @ParameterizedTest
    @CsvSource({"0,0", "1,1", "-1,-1", "1,5", "5,1"})
    @DisplayName("Слишком маленькие размеры лабиринта")
    void generate_shouldReturnNullForIncorrectSides(int height, int width) {
        Maze maze = generator.generate(height, width);

        assertThat(maze)
            .isNull();
    }

    @Test
    @DisplayName("Размер 2 на 2")
    void generate_shouldReturnMazeWithThreeWallsAndOnePassage() {
        Maze maze = generator.generate(2, 2);

        assertThat(maze.isWall(0, 0))
            .isTrue();
        assertThat(maze.isWall(1, 0))
            .isTrue();
        assertThat(maze.isWall(0, 1))
            .isTrue();
        assertThat(maze.isWall(1, 1))
            .isFalse();
    }

    @Test
    @DisplayName("Размер 3 на 3")
    void generate_shouldReturnMazeWithEightWallsAndOnePassage() {
        Maze maze = generator.generate(3, 3);
        int passageCount = 0;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                passageCount += maze.isWall(i, j) ? 0 : 1;
            }
        }

        assertThat(passageCount)
            .isOne();
    }
}
