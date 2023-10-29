package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class PrimGenerator implements MazeGenerator {

    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        Random rnd = new Random();
        int x = rnd.nextInt(width / 2) * 2 + 1;
        int y = rnd.nextInt(height / 2) * 2 + 1;
        maze.setCellType(x, y, Cell.Type.PASSAGE);

        List<Coordinate> toCheck = getAvailableCoordinates(height, width, x, y);

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
                switch (directions.get(dirIndex)) {
                    case Direction.NORTH:
                        if (y - 2 >= 0 && maze.getCellType(x, y - 2) == Cell.Type.PASSAGE) {
                            maze.setCellType(x, y - 1, Cell.Type.PASSAGE);
                            directions.clear();
                        }
                        break;
                    case Direction.SOUTH:
                        if (y + 2 < height && maze.getCellType(x, y + 2) == Cell.Type.PASSAGE) {
                            maze.setCellType(x, y + 1, Cell.Type.PASSAGE);
                            directions.clear();
                        }
                        break;
                    case Direction.EAST:
                        if (x - 2 >= 0 && maze.getCellType(x - 2, y) == Cell.Type.PASSAGE) {
                            maze.setCellType(x - 1, y, Cell.Type.PASSAGE);
                            directions.clear();
                        }
                        break;
                    case Direction.WEST:
                        if (x + 2 < width && maze.getCellType(x + 2, y) == Cell.Type.PASSAGE) {
                            maze.setCellType(x + 1, y, Cell.Type.PASSAGE);
                            directions.clear();
                        }
                        break;
                    default:
                        break;
                }
                if (!directions.isEmpty()) {
                    directions.remove(dirIndex);
                }
            }

            addAvailableWalls(height, width, x, y, maze, toCheck);
        }

        return maze;
    }

    private void addAvailableWalls(int height, int width, int x, int y, Maze maze, List<Coordinate> toCheck) {
        if (y - 2 >= 0 && maze.getCellType(x, y - 2) == Cell.Type.WALL) {
            toCheck.add(new Coordinate(x, y - 2));
        }
        if (y + 2 < height && maze.getCellType(x, y + 2) == Cell.Type.WALL) {
            toCheck.add(new Coordinate(x, y + 2));
        }
        if (x - 2 >= 0 && maze.getCellType(x - 2, y) == Cell.Type.WALL) {
            toCheck.add(new Coordinate(x - 2, y));
        }
        if (x + 2 < width && maze.getCellType(x + 2, y) == Cell.Type.WALL) {
            toCheck.add(new Coordinate(x + 2, y));
        }
    }

    @NotNull private static List<Coordinate> getAvailableCoordinates(int height, int width, int x, int y) {
        List<Coordinate> toCheck = new ArrayList<>();

        if (y - 2 >= 0) {
            toCheck.add(new Coordinate(x, y - 2));
        }
        if (y + 2 < height) {
            toCheck.add(new Coordinate(x, y + 2));
        }
        if (x - 2 >= 0) {
            toCheck.add(new Coordinate(x - 2, y));
        }
        if (x + 2 < width) {
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
