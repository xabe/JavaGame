package com.xabe.game.snake.swing;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.xabe.game.snake.common.DirectionType;
import com.xabe.game.snake.common.Game;
import com.xabe.game.snake.common.Point;

import static com.xabe.game.snake.swing.GameImpl.B_HEIGHT;
import static com.xabe.game.snake.swing.GameImpl.B_WIDTH;

public class Snake {

    private static final int DOT_SIZE = 10;
    private static final int NEGATIVE_DOT_SIZE = -1 * DOT_SIZE;

    private final List<Point> points;
    private final Map<DirectionType, Consumer<Point>> operators;
    private final Image ball;
    private final Image head;
    private final Game game;
    private DirectionType directionType;

    public Snake(final Game game) throws IOException {
        this.game = game;
        this.points = new ArrayList<>(900);
        final InputStream streamDot = this.getClass().getResourceAsStream("/dot.png");
        final InputStream streamHead = this.getClass().getResourceAsStream("/head.png");
        final ImageIcon iconDot = new ImageIcon(ImageIO.read(streamDot));
        this.ball = iconDot.getImage();
        final ImageIcon iconHead = new ImageIcon(ImageIO.read(streamHead));
        this.head = iconHead.getImage();
        this.operators = new HashMap<>();
        this.operators.put(DirectionType.LEFT, point -> {
            point.addX(NEGATIVE_DOT_SIZE);
            point.setImage(this.head);
        });
        this.operators.put(DirectionType.UP, point -> {
            point.addY(NEGATIVE_DOT_SIZE);
            point.setImage(this.head);
        });
        this.operators.put(DirectionType.RIGHT, point -> {
            point.addX(DOT_SIZE);
            point.setImage(this.head);
        });
        this.operators.put(DirectionType.DOWN, point -> {
            point.addY(DOT_SIZE);
            point.setImage(this.head);
        });
        this.directionType = DirectionType.RIGHT;
    }

    public void locateSnake() {
        IntStream.range(0, 3).forEach(i -> this.points.add(new Point(50 - (i * DOT_SIZE), 50, this.ball)));
        this.getHead().setImage(this.head);
    }

    public void render(final Graphics g) {
        this.points.forEach(point -> g.drawImage(point.getImage(), point.getX(), point.getY(), null));
    }

    public void move() {
        final int to = this.points.size() - 1;
        IntStream.iterate(to, i -> i - 1).limit(to).forEach(i -> {
            this.points.get(i).update(this.points.get(i - 1), this.ball);
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
        if (head.getY() >= B_HEIGHT || head.getY() < 0 || head.getX() >= B_WIDTH || head.getX() < 0) {
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
