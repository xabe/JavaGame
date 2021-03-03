package com.xabe.game.snake.fx;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import com.xabe.game.snake.common.DirectionType;
import com.xabe.game.snake.common.Game;
import com.xabe.game.snake.common.Point;

public class Snake {

    private static final int CORNER_SIZE = 25;
    private static final int CORNER_SIZE_LIGHT = CORNER_SIZE - 1;
    private static final int CORNER_SIZE_NORMAL = CORNER_SIZE - 2;
    private static final int NEGATIVE_DOT_SIZE = -1 * CORNER_SIZE;

    private final List<Point> points;
    private final Map<DirectionType, Consumer<Point>> operators;
    private final Game game;
    private DirectionType directionType;

    public Snake(final Game game) {
        this.game = game;
        this.points = new LinkedList<>();
        this.operators = new HashMap<>();
        this.operators.put(DirectionType.LEFT, point -> {
            point.addX(NEGATIVE_DOT_SIZE);
        });
        this.operators.put(DirectionType.UP, point -> {
            point.addY(NEGATIVE_DOT_SIZE);
        });
        this.operators.put(DirectionType.RIGHT, point -> {
            point.addX(CORNER_SIZE);
        });
        this.operators.put(DirectionType.DOWN, point -> {
            point.addY(CORNER_SIZE);
        });
        this.directionType = DirectionType.RIGHT;
    }

    public void locateSnake() {
        IntStream.range(0, 3).forEach(i -> this.points.add(Point.of(50 + (i * CORNER_SIZE), 50)));
    }

    public void render(final GraphicsContext graphicsContext) {
        this.points.forEach(point -> {
            graphicsContext.setFill(Color.LIGHTGREEN);
            graphicsContext.fillRect(point.getX(), point.getY(), CORNER_SIZE_LIGHT, CORNER_SIZE_LIGHT);
            graphicsContext.setFill(Color.GREEN);
            graphicsContext.fillRect(point.getX(), point.getY(), CORNER_SIZE_NORMAL, CORNER_SIZE_NORMAL);
        });
    }

    public void move() {
        final int to = this.points.size() - 1;
        IntStream.iterate(to, i -> i - 1).limit(to).forEach(i -> {
            this.points.get(i).update(this.points.get(i - 1));
        });
        this.operators.get(this.directionType).accept(this.getHead());
    }

    public void checkCollision() {
        final Point head = this.getHead();
        final int to = this.points.size() - 1;
        IntStream.iterate(to, i -> i - 1).limit(this.getLimit(to)).forEach(i -> {
                if (head.equals(this.points.get(i))) {
                    this.game.setInGame(false);
                }
            }
        );
        if (head.getY() >= GameImpl.HEIGHT || head.getY() < 0 || head.getX() >= GameImpl.WIDTH || head.getX() < 0) {
            this.game.setInGame(false);
        }
    }

    private long getLimit(final long to) {
        final long limit = to - 3;
        return limit <= 0 ? 0 : limit;
    }

    public void add() {
        this.points.add(this.points.get(this.points.size() - 1).clone());
    }

    public Point getHead() {
        return this.points.get(0);
    }

    public List<Point> getPoints() {
        return this.points;
    }

    public void setDirectionType(final DirectionType directionType) {
        this.directionType = directionType;
    }

    public DirectionType getDirectionType() {
        return this.directionType;
    }
}
