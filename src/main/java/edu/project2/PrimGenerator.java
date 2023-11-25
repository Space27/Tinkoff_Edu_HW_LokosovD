package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class PrimGenerator implements MazeGenerator {

    @Override
    public Maze generate(int height, int width) {
        if (height <= 1 || width <= 1) {
            return null;
        }
        Maze maze = new Maze(height, width);
        Random rnd = new Random();
        int x = rnd.nextInt(width / 2) * 2 + 1;
        int y = rnd.nextInt(height / 2) * 2 + 1;
        maze.setCellType(x, y, Cell.Type.PASSAGE);

        List<Coordinate> toCheck = getAvailableCoordinates(maze, x, y);

        while (!toCheck.isEmpty()) {
            int index = rnd.nextInt(toCheck.size());
            Coordinate cell = toCheck.get(index);
            x = cell.col();
            y = cell.row();
            maze.setCellType(x, y, Cell.Type.PASSAGE);
            toCheck.removeAll(Collections.singleton(toCheck.get(index)));

            List<Direction> directions =
                new ArrayList<>(List.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
            while (!directions.isEmpty()) {
                int dirIndex = rnd.nextInt(directions.size());
                digPathInFreeDirection(maze, directions, x, y, dirIndex);
                if (!directions.isEmpty()) {
                    directions.remove(dirIndex);
                }
            }

            addAvailableWalls(x, y, maze, toCheck);
        }

        return maze;
    }

    private void digPathInFreeDirection(Maze maze, List<Direction> directions, int x, int y, int dirIndex) {
        switch (directions.get(dirIndex)) {
            case Direction.NORTH:
                if (maze.isIn(x, y - 2) && !maze.isWall(x, y - 2)) {
                    maze.setCellType(x, y - 1, Cell.Type.PASSAGE);
                    directions.clear();
                }
                break;
            case Direction.SOUTH:
                if (maze.isIn(x, y + 2) && !maze.isWall(x, y + 2)) {
                    maze.setCellType(x, y + 1, Cell.Type.PASSAGE);
                    directions.clear();
                }
                break;
            case Direction.EAST:
                if (maze.isIn(x - 2, y) && !maze.isWall(x - 2, y)) {
                    maze.setCellType(x - 1, y, Cell.Type.PASSAGE);
                    directions.clear();
                }
                break;
            case Direction.WEST:
                if (maze.isIn(x + 2, y) && !maze.isWall(x + 2, y)) {
                    maze.setCellType(x + 1, y, Cell.Type.PASSAGE);
                    directions.clear();
                }
                break;
            default:
                break;
        }
    }

    private void addAvailableWalls(int x, int y, Maze maze, List<Coordinate> toCheck) {
        if (maze.isIn(x, y - 2) && maze.isWall(x, y - 2)) {
            toCheck.add(new Coordinate(x, y - 2));
        }
        if (maze.isIn(x, y + 2) && maze.isWall(x, y + 2)) {
            toCheck.add(new Coordinate(x, y + 2));
        }
        if (maze.isIn(x - 2, y) && maze.isWall(x - 2, y)) {
            toCheck.add(new Coordinate(x - 2, y));
        }
        if (maze.isIn(x + 2, y) && maze.isWall(x + 2, y)) {
            toCheck.add(new Coordinate(x + 2, y));
        }
    }

    @NotNull
    private static List<Coordinate> getAvailableCoordinates(Maze maze, int x, int y) {
        List<Coordinate> toCheck = new ArrayList<>();

        if (maze.isIn(x, y - 2)) {
            toCheck.add(new Coordinate(x, y - 2));
        }
        if (maze.isIn(x, y + 2)) {
            toCheck.add(new Coordinate(x, y + 2));
        }
        if (maze.isIn(x - 2, y)) {
            toCheck.add(new Coordinate(x - 2, y));
        }
        if (maze.isIn(x + 2, y)) {
            toCheck.add(new Coordinate(x + 2, y));
        }

        return toCheck;
    }

    private enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
}
