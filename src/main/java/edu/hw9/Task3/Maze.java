package edu.hw9.Task3;

public final class Maze {

    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void setCellType(int col, int row, Cell.Type type) {
        grid[row][col].setType(type);
    }

    public void setCellType(Coordinate coordinate, Cell.Type type) {
        grid[coordinate.row()][coordinate.col()].setType(type);
    }

    public Cell.Type getCellType(int col, int row) {
        return grid[row][col].getType();
    }

    public Cell.Type getCellType(Coordinate coordinate) {
        return grid[coordinate.row()][coordinate.col()].getType();
    }

    public boolean isWall(int col, int row) {
        return grid[row][col].getType() == Cell.Type.WALL;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isIn(Coordinate coordinate) {
        return coordinate.col() > 0 && coordinate.row() > 0 && coordinate.col() < width && coordinate.row() < height;
    }

    public boolean isIn(int col, int row) {
        return col > 0 && row > 0 && col < width && row < height;
    }
}
