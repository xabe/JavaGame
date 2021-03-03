package com.xabe.game.tetris.common;

import java.util.List;
import java.util.stream.Collectors;

public class Shape {

    private final ShapeType type;

    private final List<Point> coordinates;

    public Shape(final ShapeType type) {
        this.type = type;
        this.coordinates = type.getCoordinates().stream().map(Point::clone).collect(Collectors.toList());
    }

    public List<Point> getCoordinates() {
        return this.coordinates;
    }

    public ShapeType getType() {
        return this.type;
    }

    public Point getCoordinate(final int index) {
        return this.coordinates.get(index);
    }

    public int minY() {
        return this.coordinates.stream().mapToInt(Point::getY).min().getAsInt();
    }

    public void rotateLeft() {
        if (this.type != ShapeType.SQUARE_SHAPE) {
            this.coordinates.forEach(point -> {
                final int x = point.getY();
                final int y = -point.getX();
                point.setX(x);
                point.setY(y);
            });
        }
    }

    public void rotateRight() {
        if (this.type != ShapeType.SQUARE_SHAPE) {
            this.coordinates.forEach(point -> {
                final int x = -point.getY();
                final int y = point.getX();
                point.setX(x);
                point.setY(y);
            });
        }
    }
}
