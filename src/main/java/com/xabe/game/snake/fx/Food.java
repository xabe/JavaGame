package com.xabe.game.snake.fx;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import com.xabe.game.snake.common.Point;

public class Food {

    private static final int RAND_POS = 20;
    private static final int CORNER_SIZE = 25;

    private final Random random;
    private final Point point;
    private final Map<Integer, Color> colorMap;
    private int foodColor;
    private int score;

    public Food() {
        this.random = new Random();
        this.foodColor = 0;
        this.score = -1;
        this.point = Point.of(RAND_POS, RAND_POS);
        this.colorMap = new HashMap<>();
        this.colorMap.put(0, Color.WHITE);
        this.colorMap.put(1, Color.PURPLE);
        this.colorMap.put(2, Color.LIGHTBLUE);
        this.colorMap.put(3, Color.YELLOW);
        this.colorMap.put(4, Color.PINK);
        this.colorMap.put(5, Color.ORANGE);
    }

    public void locateApple() {
        final int x = this.random.nextInt(RAND_POS);
        final int y = this.random.nextInt(RAND_POS);
        this.point.setX(x * CORNER_SIZE);
        this.point.setY(y * CORNER_SIZE);
        this.foodColor = this.random.nextInt(5);
        this.score++;
    }

    public void render(final GraphicsContext graphicsContext) {
        graphicsContext.setFill(this.colorMap.getOrDefault(this.foodColor, Color.WHITE));
        graphicsContext.fillOval(this.point.getX(), this.point.getY(), CORNER_SIZE, CORNER_SIZE);
    }

    public Point getApple() {
        return this.point;
    }

    public int getScore() {
        return this.score;
    }
}
