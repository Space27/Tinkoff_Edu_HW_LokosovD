package edu.hw9.Task3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ParallelDepthFirstSearch extends RecursiveTask<List<Coordinate>> implements Solver {

    private Maze maze;
    private Coordinate start;
    private Coordinate end;

    public ParallelDepthFirstSearch(Maze maze, Coordinate start, Coordinate end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
    }

    public ParallelDepthFirstSearch() {
        this(null, null, null);
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        this.maze = maze;
        this.start = start;
        this.end = end;

        return compute();
    }

    @Override
    protected List<Coordinate> compute() {
        List<Coordinate> way = new ArrayList<>();

        if (!maze.isIn(start) || !maze.isIn(end)
            || maze.getCellType(start) == Cell.Type.WALL || maze.getCellType(end) == Cell.Type.WALL) {
            return way;
        } else if (start.equals(end)) {
            way.add(start);
            return way;
        }

        maze.setCellType(start, Cell.Type.WALL);

        List<ParallelDepthFirstSearch> tasks = List.of(
            new ParallelDepthFirstSearch(maze, new Coordinate(start.col(), start.row() + 1), end),
            new ParallelDepthFirstSearch(maze, new Coordinate(start.col(), start.row() - 1), end),
            new ParallelDepthFirstSearch(maze, new Coordinate(start.col() + 1, start.row()), end),
            new ParallelDepthFirstSearch(maze, new Coordinate(start.col() - 1, start.row()), end)
        );

        List<Coordinate> wayList = ForkJoinTask.invokeAll(tasks).stream()
            .map(ForkJoinTask::join)
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
