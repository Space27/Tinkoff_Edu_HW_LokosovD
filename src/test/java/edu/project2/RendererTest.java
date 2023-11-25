package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class RendererTest {

    @Test
    @DisplayName("Отрисовка маленького лабиринта")
    void render_ShouldReturnMazeStrWithPrettyPrint() {
        Maze maze = new Maze(3, 3);
        maze.setCellType(0, 0, Cell.Type.PASSAGE);
        maze.setCellType(1, 1, Cell.Type.PASSAGE);
        maze.setCellType(1, 2, Cell.Type.PASSAGE);

        SimpleCharRenderer simpleCharRenderer = new SimpleCharRenderer();
        String strMaze = simpleCharRenderer.render(maze);

        String expected = "  ████\n██  ██\n██  ██\n";

        assertThat(strMaze)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("Отрисовка маленького лабиринта с путем")
    void render_ShouldReturnMazeStrWithPrettyPrintWithPath() {
        Maze maze = new Maze(3, 3);
        maze.setCellType(0, 0, Cell.Type.PASSAGE);
        maze.setCellType(0, 1, Cell.Type.PASSAGE);
        maze.setCellType(0, 2, Cell.Type.PASSAGE);
        maze.setCellType(1, 2, Cell.Type.PASSAGE);
        maze.setCellType(2, 2, Cell.Type.PASSAGE);
        List<Coordinate> coordinates = new ArrayList<>(List.of(new Coordinate(0, 0), new Coordinate(0, 1)));

        SimpleCharRenderer simpleCharRenderer = new SimpleCharRenderer();
        String strMaze = simpleCharRenderer.render(maze, coordinates);

        String expected = "◀▶████\n◀▶████\n      \n";

        assertThat(strMaze)
            .isEqualTo(expected);
    }
}
