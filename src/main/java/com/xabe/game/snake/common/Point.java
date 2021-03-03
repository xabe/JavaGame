package com.xabe.game.snake.common;

import java.awt.Image;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Point {

    private Image image;
    private int x;
    private int y;

    public Point(final int x, final int y, final Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public static Point of(final int x, final int y, final Image image) {
        return new Point(x, y, image);
    }

    public static Point of(final int x, final int y) {
        return new Point(x, y, null);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Image getImage() {
        return this.image;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof Point) {
            final Point point = (Point) o;
            return new EqualsBuilder()
                .append(this.x, point.x)
                .append(this.y, point.y)
                .isEquals();
        }
        return false;
    }

    @Override
    public Point clone() {
        return of(this.x, this.y, this.image);
    }

    public void update(final Point point, final Image image) {
        this.x = point.x;
        this.y = point.y;
        this.image = image;
    }

    public void update(final Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public void addX(final int size) {
        this.x += size;
    }

    public void addY(final int size) {
        this.y += size;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public void setImage(final Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Point{" +
            "x=" + this.x +
            ", y=" + this.y +
            '}';
    }
}
