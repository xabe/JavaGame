package com.xabe.game.snake.swing;

import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.xabe.game.snake.common.Point;

public class Food {

    private static final int DOT_SIZE = 10;
    private static final int RAND_POS = 29;

    private final Random random;
    private final Point point;

    public Food() throws IOException {
        final InputStream streamApple = this.getClass().getResourceAsStream("/apple.png");
        final ImageIcon iconApple = new ImageIcon(ImageIO.read(streamApple));
        this.random = new Random();
        this.point = Point.of(RAND_POS, RAND_POS, iconApple.getImage());
    }

    public void locateApple() {
        final int x = this.random.nextInt(RAND_POS);
        final int y = this.random.nextInt(RAND_POS);
        this.point.setX(x * DOT_SIZE);
        this.point.setY(y * DOT_SIZE);
    }

    public void render(final Graphics graphics) {
        graphics.drawImage(this.point.getImage(), this.point.getX(), this.point.getY(), null);
    }

    public Point getApple() {
        return this.point;
    }
}
