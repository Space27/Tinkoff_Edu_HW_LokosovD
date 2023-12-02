package edu.hw9;

import edu.hw9.Task3.Coordinate;
import edu.hw9.Task3.Maze;
import edu.hw9.Task3.MazeGenerator;
import edu.hw9.Task3.ParallelDepthFirstSearch;
import edu.hw9.Task3.PrimGenerator;
import edu.hw9.Task3.Solver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 11, 17, 21, 31, 51})
    @DisplayName("Квадратный лабиринт с нечетным размером для поиска в глубину")
    void parallelDepthFirstSearch_shouldSolveSquareMazeWithOddWalls(int size) {
        MazeGenerator generator = new PrimGenerator();
        Solver solver = new ParallelDepthFirstSearch();
        Maze maze = generator.generate(size, size);

        List<Coordinate> coordinates = solver.solve(maze, new Coordinate(1, 1), new Coordinate(size - 2, size - 2));

        assertThat(coordinates)
            .isNotEmpty()
            .hasSizeGreaterThan(2 * (size - 3))
            .contains(new Coordinate(1, 1))
            .contains(new Coordinate(size - 2, size - 2));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 10, 16, 20, 30, 50})
    @DisplayName("Квадратный лабиринт с нечетным размером для поиска в глубину")
    void parallelDepthFirstSearch_shouldSolveSquareMazeWithEvenWalls(int size) {
        MazeGenerator generator = new PrimGenerator();
        Solver solver = new ParallelDepthFirstSearch();
        Maze maze = generator.generate(size, size);

        List<Coordinate> coordinates = solver.solve(maze, new Coordinate(1, 1), new Coordinate(size - 1, size - 1));

        assertThat(coordinates)
            .isNotEmpty()
            .hasSizeGreaterThan(2 * (size - 3))
            .contains(new Coordinate(1, 1))
            .contains(new Coordinate(size - 1, size - 1));
    }

    @ParameterizedTest
    @CsvSource({"3, 5", "5, 3", "9, 11", "11, 9", "51, 31", "31, 51"})
    @DisplayName("Прямоугольный лабиринт для поиска в глубину")
    void parallelDepthFirstSearch_shouldSolveSquareMazeWithEvenWalls(int width, int height) {
        MazeGenerator generator = new PrimGenerator();
        Solver solver = new ParallelDepthFirstSearch();
        Maze maze = generator.generate(height, width);

        List<Coordinate> coordinates = solver.solve(maze, new Coordinate(1, 1), new Coordinate(width - 2, height - 2));

        assertThat(coordinates)
            .isNotEmpty()
            .hasSizeGreaterThan(width + height - 6)
            .contains(new Coordinate(1, 1))
            .contains(new Coordinate(width - 2, height - 2));
    }

    @Test
    @DisplayName("Путь в стену для поиска в глубину")
    void parallelDepthFirstSearch_shouldReturnEmptyListIfEndIfWall() {
        MazeGenerator generator = new PrimGenerator();
        Solver solver = new ParallelDepthFirstSearch();
        Maze maze = generator.generate(81, 81);

        List<Coordinate> coordinates = solver.solve(maze, new Coordinate(1, 1), new Coordinate(80, 80));

        assertThat(coordinates)
            .isEmpty();
    }
}
