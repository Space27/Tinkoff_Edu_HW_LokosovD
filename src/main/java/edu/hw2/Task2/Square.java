package edu.hw2.Task2;

public class Square extends Rectangle {

    final private Rectangle likeSquare = new Rectangle();

    private boolean isChanged = false;

    @Override
    public Rectangle setWidth(int width) {
        if (!isChanged) {
            likeSquare.setHeight(width);
        }
        likeSquare.setWidth(width);
        super.setHeight(width);
        super.setWidth(width);
        isChanged = true;

        return likeSquare;
    }

    @Override
    public Rectangle setHeight(int height) {
        if (!isChanged) {
            likeSquare.setWidth(height);
        }
        likeSquare.setHeight(height);
        super.setHeight(height);
        super.setWidth(height);
        isChanged = true;

        return likeSquare;
    }
}
