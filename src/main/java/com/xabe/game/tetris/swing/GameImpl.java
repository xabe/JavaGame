package com.xabe.game.tetris.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

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

public class GameImpl extends JPanel implements ActionListener {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 400;
    private static final int PERIOD_INTERVAL = 300;
    private final Color[] colors = {
        new Color(0, 0, 0),
        new Color(204, 102, 102),
        new Color(102, 204, 102),
        new Color(102, 102, 204),
        new Color(204, 204, 102),
        new Color(204, 102, 204),
        new Color(102, 204, 204),
        new Color(218, 170, 0)
    };
    private final Timer timer;
    private final JLabel statusbar;
    private final Tetris tetris;

    public GameImpl(final JLabel statusbar) {
        this.timer = new Timer(PERIOD_INTERVAL, this);
        this.tetris = new TetrisImpl(this::repaint, statusbar::setText, this.timer::stop);
        this.setFocusable(true);
        this.statusbar = statusbar;
        this.addKeyListener(new Input(this.tetris));
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.timer.start();
    }

    public void start() {
        this.tetris.start();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                final ShapeType shapeType = this.tetris.shapeAt(j, BOARD_HEIGHT - i - 1);
                if (shapeType != ShapeType.NO_SHAPE) {
                    this.drawSquare(g, j * SQUARE_WIDTH, BOARD_TOP + i * SQUARE_HEIGHT, shapeType);
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
                this.drawSquare(g, x * SQUARE_WIDTH, BOARD_TOP + (BOARD_HEIGHT - y - 1) * SQUARE_HEIGHT,
                    shape.getType());
            }
        }
    }

    private void drawSquare(final Graphics g, final int x, final int y, final ShapeType shape) {
        final var color = this.colors[shape.ordinal()];
        g.setColor(color);
        g.fillRect(x, y, SQUARE_WIDTH, SQUARE_HEIGHT);
        g.setColor(color.brighter());
        g.drawLine(x, y + SQUARE_HEIGHT_LINE, x, y);
        g.drawLine(x, y, x + SQUARE_WIDTH_LINE, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + SQUARE_HEIGHT_LINE, x + SQUARE_WIDTH_LINE, y + SQUARE_HEIGHT_LINE);
        g.drawLine(x + SQUARE_WIDTH_LINE, y + SQUARE_HEIGHT_LINE, x + SQUARE_WIDTH_LINE, y + 1);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        this.tetris.doGameCycle();
    }

}
