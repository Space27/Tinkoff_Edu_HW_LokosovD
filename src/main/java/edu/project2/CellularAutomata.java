package edu.project2;

import java.util.Random;

public class CellularAutomata implements MazeGenerator {

    private final static double CHANCE_TO_WALL = 0.45;
    private final static int DEATH_LIMIT = 7;
    private final static int BIRTH_LIMIT = 2;
    private final static int STEP_NUMBER = 7;

    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        Random rnd = new Random();

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (rnd.nextDouble() < CHANCE_TO_WALL) {
                    maze.setCellType(j, i, Cell.Type.WALL);
                } else {
                    maze.setCellType(j, i, Cell.Type.PASSAGE);
                }
            }
        }

        for (int i = 0; i < STEP_NUMBER; ++i) {
            maze = doStimulationStep(maze);
        }

        return maze;
    }

    private int countNeighbourWalls(Maze maze, int x, int y) {
        int count = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int neighX = x + i;
                int neighY = y + j;

                if (i == 0 && j == 0) {
                    continue;
                }
                if (neighX < 0 || neighY < 0 || neighX >= maze.getWidth() || neighY >= maze.getHeight()
                    || maze.getCellType(neighX, neighY) == Cell.Type.WALL) {
                    ++count;
                }
            }
        }

        return count;
    }

    private Maze doStimulationStep(Maze oldMaze) {
        Maze newMaze = new Maze(oldMaze.getHeight(), oldMaze.getWidth());

        for (int x = 0; x < oldMaze.getWidth(); x++) {
            for (int y = 0; y < oldMaze.getHeight(); y++) {
                int nbs = countNeighbourWalls(oldMaze, x, y);

                if (oldMaze.getCellType(x, y) == Cell.Type.WALL) {
                    if (nbs < DEATH_LIMIT) {
                        newMaze.setCellType(x, y, Cell.Type.PASSAGE);
                    } else {
                        newMaze.setCellType(x, y, Cell.Type.WALL);
                    }
                } else {
                    if (nbs > BIRTH_LIMIT) {
                        newMaze.setCellType(x, y, Cell.Type.WALL);
                    } else {
                        newMaze.setCellType(x, y, Cell.Type.PASSAGE);
                    }
                }
            }
        }

        return newMaze;
    }
}
