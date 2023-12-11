package edu.project4;

public record Color(int r, int g, int b) {

    private static final int BIG_SHIFT = 16;
    private static final int MED_SHIFT = 8;

    public int getColorByOneNum() {
        return (r << BIG_SHIFT) + (g << MED_SHIFT) + b;
    }
}
