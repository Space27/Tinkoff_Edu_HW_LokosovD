package edu.hw9.Task3;

public class Cell {

    public enum Type {
        WALL,
        PASSAGE
    }

    private Type type;

    public Cell() {
        this.type = Type.WALL;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
