package com.xabe.game.tetris.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Point {

    private int x;
    private int y;

    private Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Point of(final int x, final int y) {
        return new Point(x, y);
    }

    @Override
    public Point clone() {
        return new Point(this.x, this.y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public Point minusX(final int i) {
        return of(this.x - i, this.y);
    }

    public Point addX(final int i) {
        return of(this.x + i, this.y);
    }

    public Point minusY(final int i) {
        return of(this.x, this.y - i);
    }

    public void set(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public void copy(final Point newPoint) {
        this.x = newPoint.x;
        this.y = newPoint.y;
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
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(this.x)
            .append(this.y)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "Point{" +
            "x=" + this.x +
            ", y=" + this.y +
            '}';
    }
}
