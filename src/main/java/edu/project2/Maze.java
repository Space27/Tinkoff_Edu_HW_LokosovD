package edu.project2;

public final class Maze {

    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[this.height][this.width];
    }

    public void setCellType(int col, int row, Cell.Type type) {
        grid[col][row].setType(type);
    }
}
