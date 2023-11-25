package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class SolverTest {

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 11, 17, 21, 31, 51})
    @DisplayName("Квадратный лабиринт с нечетным размером для поиска в ширину")
    void breadthFirstSearch_ShouldSolveSquareMazeWithOddWalls(int size) {
        MazeGenerator generator = new PrimGenerator();
        Solver solver = new BreadthFirstSearch();
        Maze maze = generator.generate(size,size);

        List<Coordinate> coordinates = solver.solve(maze, new Coordinate(1, 1), new Coordinate(size - 2, size - 2));

        assertThat(coordinates)
            .isNotEmpty()
            .hasSizeGreaterThan(2 * (size - 3))
            .contains(new Coordinate(1, 1))
            .contains(new Coordinate(size - 2, size - 2));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 11, 17, 21, 31, 51})
    @DisplayName("Квадратный лабиринт с нечетным размером для поиска в глубину")
    void depthFirstSearch_ShouldSolveSquareMazeWithOddWalls(int size) {
        MazeGenerator generator = new PrimGenerator();
        Solver solver = new DepthFirstSearch();
        Maze maze = generator.generate(size, size);

        List<Coordinate> coordinates = solver.solve(maze, new Coordinate(1, 1), new Coordinate(size - 2, size - 2));

        assertThat(coordinates)
            .isNotEmpty()
            .hasSizeGreaterThan(2 * (size - 3))
            .contains(new Coordinate(1, 1))
            .contains(new Coordinate(size - 2, size - 2));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 11, 17, 21, 31, 51})
    @DisplayName("Сравнение путей алгоритмов поиска в квадратном идеальном лабиринте с нечетными стенами")
    void depthFirstSearch_ShouldSolveSquareMazeWithOddWallsLikeBreadthFirstSearchInPerfectMaze(int size) {
        MazeGenerator generator = new PrimGenerator();
        Solver depthSolver = new DepthFirstSearch();
        Solver breadthSolver = new BreadthFirstSearch();
        Maze maze = generator.generate(size, size);

        List<Coordinate> depthCoordinates =
            depthSolver.solve(maze, new Coordinate(1, 1), new Coordinate(size - 2, size - 2));
        List<Coordinate> breadthCoordinates =
            breadthSolver.solve(maze, new Coordinate(1, 1), new Coordinate(size - 2, size - 2));

        assertThat(depthCoordinates)
            .isEqualTo(breadthCoordinates);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 10, 16, 20, 30, 50})
    @DisplayName("Квадратный лабиринт с четным размером для поиска в ширину")
    void breadthFirstSearch_ShouldSolveSquareMazeWithEvenWalls(int size) {
        MazeGenerator generator = new PrimGenerator();
        Solver solver = new BreadthFirstSearch();
        Maze maze = generator.generate(size, size);

        List<Coordinate> coordinates = solver.solve(maze, new Coordinate(1, 1), new Coordinate(size - 1, size - 1));

        assertThat(coordinates)
            .isNotEmpty()
            .hasSizeGreaterThan(2 * (size - 3))
            .contains(new Coordinate(1, 1))
            .contains(new Coordinate(size - 1, size - 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 10, 16, 20, 30, 50})
    @DisplayName("Квадратный лабиринт с нечетным размером для поиска в глубину")
    void depthFirstSearch_ShouldSolveSquareMazeWithEvenWalls(int size) {
        MazeGenerator generator = new PrimGenerator();
        Solver solver = new DepthFirstSearch();
        Maze maze = generator.generate(size, size);

        List<Coordinate> coordinates = solver.solve(maze, new Coordinate(1, 1), new Coordinate(size - 1, size - 1));

        assertThat(coordinates)
            .isNotEmpty()
            .hasSizeGreaterThan(2 * (size - 3))
            .contains(new Coordinate(1, 1))
            .contains(new Coordinate(size - 1, size - 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 10, 16, 20, 30, 50})
    @DisplayName("Сравнение путей алгоритмов поиска в квадратном идеальном лабиринте с четными стенами")
    void depthFirstSearch_ShouldSolveSquareMazeWithEvenWallsLikeBreadthFirstSearchInPerfectMaze(int size) {
        MazeGenerator generator = new PrimGenerator();
        Solver depthSolver = new DepthFirstSearch();
        Solver breadthSolver = new BreadthFirstSearch();
        Maze maze = generator.generate(size, size);

        List<Coordinate> depthCoordinates =
            depthSolver.solve(maze, new Coordinate(1, 1), new Coordinate(size - 1, size - 1));
        List<Coordinate> breadthCoordinates =
            breadthSolver.solve(maze, new Coordinate(1, 1), new Coordinate(size - 1, size - 1));

        assertThat(depthCoordinates)
            .isEqualTo(breadthCoordinates);
    }

    @ParameterizedTest
    @CsvSource({"3, 5", "5, 3", "9, 11", "11, 9", "51, 31", "31, 51"})
    @DisplayName("Прямоугольный лабиринт для поиска в ширину")
    void breadthFirstSearch_ShouldSolveRectangleMaze(int width, int height) {
        MazeGenerator generator = new PrimGenerator();
        Solver solver = new BreadthFirstSearch();
        Maze maze = generator.generate(height, width);

        List<Coordinate> coordinates = solver.solve(maze, new Coordinate(1, 1), new Coordinate(width - 2, height - 2));

        assertThat(coordinates)
            .isNotEmpty()
            .hasSizeGreaterThan(width + height - 6)
            .contains(new Coordinate(1, 1))
            .contains(new Coordinate(width - 2, height - 2));
    }

    @ParameterizedTest
    @CsvSource({"3, 5", "5, 3", "9, 11", "11, 9", "51, 31", "31, 51"})
    @DisplayName("Прямоугольный лабиринт для поиска в глубину")
    void depthFirstSearch_ShouldSolveSquareMazeWithEvenWalls(int width, int height) {
        MazeGenerator generator = new PrimGenerator();
        Solver solver = new DepthFirstSearch();
        Maze maze = generator.generate(height, width);

        List<Coordinate> coordinates = solver.solve(maze, new Coordinate(1, 1), new Coordinate(width - 2, height - 2));

        assertThat(coordinates)
            .isNotEmpty()
            .hasSizeGreaterThan(width + height - 6)
            .contains(new Coordinate(1, 1))
            .contains(new Coordinate(width - 2, height - 2));
    }

    @ParameterizedTest
    @CsvSource({"3, 5", "5, 3", "9, 11", "11, 9", "51, 31", "31, 51"})
    @DisplayName("Сравнение путей алгоритмов поиска в прямоугольном идеальном лабиринте")
    void depthFirstSearch_ShouldSolveRectangleMazeLikeBreadthFirstSearchInPerfectMaze(int width, int height) {
        MazeGenerator generator = new PrimGenerator();
        Solver depthSolver = new DepthFirstSearch();
        Solver breadthSolver = new BreadthFirstSearch();
        Maze maze = generator.generate(height, width);

        List<Coordinate> depthCoordinates =
            depthSolver.solve(maze, new Coordinate(1, 1), new Coordinate(width - 2, height - 2));
        List<Coordinate> breadthCoordinates =
            breadthSolver.solve(maze, new Coordinate(1, 1), new Coordinate(width - 2, height - 2));

        assertThat(depthCoordinates)
            .isEqualTo(breadthCoordinates);
    }

    @Test
    @DisplayName("Путь в стену для поиска в ширину")
    void breadthFirstSearch_ShouldReturnEmptyListIfEndIfWall() {
        MazeGenerator generator = new PrimGenerator();
        Solver solver = new BreadthFirstSearch();
        Maze maze = generator.generate(81, 81);

        List<Coordinate> coordinates = solver.solve(maze, new Coordinate(1, 1), new Coordinate(80, 80));

        assertThat(coordinates)
            .isEmpty();
    }

    @Test
    @DisplayName("Путь в стену для поиска в глубину")
    void depthFirstSearch_ShouldReturnEmptyListIfEndIfWall() {
        MazeGenerator generator = new PrimGenerator();
        Solver solver = new DepthFirstSearch();
        Maze maze = generator.generate(81, 81);

        List<Coordinate> coordinates = solver.solve(maze, new Coordinate(1, 1), new Coordinate(80, 80));

        assertThat(coordinates)
            .isEmpty();
    }
}
