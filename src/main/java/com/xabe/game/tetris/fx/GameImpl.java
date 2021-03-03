package com.xabe.game.tetris.fx;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import com.xabe.game.common.TimeListener;
import com.xabe.game.common.Timer;
import com.xabe.game.tetris.common.Point;
import com.xabe.game.tetris.common.Shape;
import com.xabe.game.tetris.common.ShapeType;
import com.xabe.game.tetris.common.Tetris;
import com.xabe.game.tetris.common.TetrisImpl;

import static com.xabe.game.tetris.common.TetrisImpl.BOARD_HEIGHT;
import static com.xabe.game.tetris.common.TetrisImpl.BOARD_TOP;
import static com.xabe.game.tetris.common.TetrisImpl.BOARD_WIDTH;
import static com.xabe.game.tetris.common.TetrisImpl.SQUARE_HEIGHT;
import static com.xabe.game.tetris.common.TetrisImpl.SQUARE_HEIGHT_LINE;
import static com.xabe.game.tetris.common.TetrisImpl.SQUARE_WIDTH;
import static com.xabe.game.tetris.common.TetrisImpl.SQUARE_WIDTH_LINE;

public class GameImpl extends Pane implements TimeListener {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 400;
    private final Color[] colors = {
        Color.BLACK,
        Color.rgb(204, 102, 102),
        Color.rgb(102, 204, 102),
        Color.rgb(102, 102, 204),
        Color.rgb(204, 204, 102),
        Color.rgb(204, 102, 204),
        Color.rgb(102, 204, 204),
        Color.rgb(218, 170, 0)
    };
    private final GraphicsContext gc;
    private final Timer timer;
    private final Tetris tetris;

    public GameImpl(final Label statusbar) {
        this.timer = new Timer(this, 1000000000 / 6);
        this.setPrefSize(WIDTH, HEIGHT);
        final Canvas c = new Canvas(WIDTH, HEIGHT);
        this.gc = c.getGraphicsContext2D();
        this.getChildren().add(c);
        this.tetris = new TetrisImpl(this::repaint, statusbar::setText, this.timer::stop);
        this.addEventFilter(KeyEvent.KEY_PRESSED, new Input(this.tetris));
        this.setFocusTraversable(true);
        this.requestFocus();
        this.timer.start();
    }

    public void start() {
        this.tetris.start();
    }

    @Override
    public void tick() {
        this.tetris.doGameCycle();
    }

    public void repaint() {
        this.gc.clearRect(0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                final ShapeType shapeType = this.tetris.shapeAt(j, BOARD_HEIGHT - i - 1);
                if (shapeType != ShapeType.NO_SHAPE) {
                    this.drawSquare(j * SQUARE_WIDTH, BOARD_TOP + i * SQUARE_HEIGHT, shapeType);
                }
            }
        }
        final Shape shape = this.tetris.getShape();
        final Point point = this.tetris.getPoint();
        if (shape.getType() != ShapeType.NO_SHAPE) {
            for (int i = 0; i < 4; i++) {
                final Point coordinate = shape.getCoordinate(i);
                final int x = point.getX() + coordinate.getX();
                final int y = point.getY() - coordinate.getY();
                this.drawSquare(x * SQUARE_WIDTH, BOARD_TOP + (BOARD_HEIGHT - y - 1) * SQUARE_HEIGHT, shape.getType());
            }
        }
    }

    private void drawSquare(final int x, final int y, final ShapeType shape) {
        final var color = this.colors[shape.ordinal()];
        this.gc.setFill(color);
        this.gc.fillRect(x, y, SQUARE_WIDTH, SQUARE_HEIGHT);
        this.gc.setStroke(color.brighter());
        this.gc.strokeLine(x, y + SQUARE_HEIGHT_LINE, x, y);
        this.gc.strokeLine(x, y, x + SQUARE_WIDTH_LINE, y);
        this.gc.setStroke(color.darker());
        this.gc.strokeLine(x + 1, y + SQUARE_HEIGHT_LINE, x + SQUARE_WIDTH_LINE, y + SQUARE_HEIGHT_LINE);
        this.gc.strokeLine(x + SQUARE_WIDTH_LINE, y + SQUARE_HEIGHT_LINE, x + SQUARE_WIDTH_LINE, y + 1);
    }
}
