package com.xabe.game.tetris.common;

public interface Tetris {

    Shape getShape();

    ShapeType shapeAt(final int x, final int y);

    void pause();

    boolean tryMove(final Shape newPiece, final Point point);

    void dropDown();

    void oneLineDown();

    Point getPoint();

    void doGameCycle();

    void start();
}
