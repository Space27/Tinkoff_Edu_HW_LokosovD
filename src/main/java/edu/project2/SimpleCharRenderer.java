package edu.project2;

import java.util.List;

public class SimpleCharRenderer implements Renderer {

    private final static String WALL_ELEM = "██";
    private final static String PASSAGE_ELEM = "  ";
    private final static String PATH_ELEM = "◀▶";

    @Override
    public String render(Maze maze) {
        StringBuilder resultMaze = new StringBuilder();

        for (int row = 0; row < maze.getHeight(); ++row) {
            for (int col = 0; col < maze.getWidth(); ++col) {
                resultMaze.append(maze.getCellType(col, row) == Cell.Type.PASSAGE ? PASSAGE_ELEM : WALL_ELEM);
            }
            resultMaze.append('\n');
        }

        return resultMaze.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder resultMaze = new StringBuilder();

        for (int row = 0; row < maze.getHeight(); ++row) {
            for (int col = 0; col < maze.getWidth(); ++col) {
                if (path.contains(new Coordinate(col, row)) && maze.getCellType(col, row) == Cell.Type.PASSAGE) {
                    resultMaze.append(PATH_ELEM);
                } else {
                    resultMaze.append(maze.getCellType(col, row) == Cell.Type.PASSAGE ? PASSAGE_ELEM : WALL_ELEM);
                }
            }
            resultMaze.append('\n');
        }

        return resultMaze.toString();
    }
}
