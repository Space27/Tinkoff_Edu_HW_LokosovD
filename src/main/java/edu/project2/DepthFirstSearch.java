package edu.project2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class DepthFirstSearch implements Solver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Coordinate> way = new ArrayList<>();

        if (maze.getCellType(start) == Cell.Type.WALL || maze.getCellType(end) == Cell.Type.WALL
            || !maze.isIn(start) || !maze.isIn(end)) {
            return way;
        } else if (start.equals(end)) {
            way.add(start);
            return way;
        }

        maze.setCellType(start, Cell.Type.WALL);

        List<Coordinate> wayList = Stream.of(
                solve(maze, new Coordinate(start.col(), start.row() + 1), end),
                solve(maze, new Coordinate(start.col(), start.row() - 1), end),
                solve(maze, new Coordinate(start.col() + 1, start.row()), end),
                solve(maze, new Coordinate(start.col() - 1, start.row()), end)
            )
            .filter(list -> !list.isEmpty())
            .min(Comparator.comparingInt(List::size))
            .orElse(null);
        if (wayList != null) {
            way.add(start);
            way.addAll(wayList);
        }

        maze.setCellType(start, Cell.Type.PASSAGE);

        return way;
    }
}
