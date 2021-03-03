package com.xabe.game.tetris.common;

import java.util.List;

public enum ShapeType {
    NO_SHAPE(List.of(Point.of(0, 0), Point.of(0, 0), Point.of(0, 0), Point.of(0, 0))),
    Z_SHAPE(List.of(Point.of(-1, 1), Point.of(-1, 0), Point.of(0, 0), Point.of(0, -1))),
    S_SHAPE(List.of(Point.of(0, -1), Point.of(0, 0), Point.of(1, 0), Point.of(1, 1))),
    LINE_SHAPE(List.of(Point.of(0, 2), Point.of(0, 1), Point.of(0, 0), Point.of(0, -1))),
    T_SHAPE(List.of(Point.of(-1, 0), Point.of(0, 0), Point.of(1, 0), Point.of(0, 1))),
    SQUARE_SHAPE(List.of(Point.of(0, 0), Point.of(1, 0), Point.of(0, 1), Point.of(1, 1))),
    L_SHAPE(List.of(Point.of(-1, -1), Point.of(0, -1), Point.of(0, 0), Point.of(0, 1))),
    MIRRORED_L_SHAPE(List.of(Point.of(1, -1), Point.of(0, -1), Point.of(0, 0), Point.of(0, 1)));
    private final List<Point> coordinates;

    ShapeType(final List<Point> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Point> getCoordinates() {
        return this.coordinates;
    }
}
