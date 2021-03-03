package com.xabe.game.tetris.swing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.xabe.game.tetris.common.Shape;
import com.xabe.game.tetris.common.ShapeType;
import com.xabe.game.tetris.common.Tetris;


public class Input extends KeyAdapter {

    private final Tetris tetris;

    public Input(final Tetris tetris) {
        this.tetris = tetris;
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        final Shape shape = this.tetris.getShape();
        if (shape.getType() == ShapeType.NO_SHAPE) {
            return;
        }
        final int key = e.getKeyCode();
        if (key == KeyEvent.VK_P) {
            this.tetris.pause();
        }
        if (key == KeyEvent.VK_LEFT) {
            this.tetris.tryMove(shape, this.tetris.getPoint().minusX(1));
        }
        if (key == KeyEvent.VK_RIGHT) {
            this.tetris.tryMove(shape, this.tetris.getPoint().addX(1));
        }
        if (key == KeyEvent.VK_DOWN) {
            shape.rotateRight();
            if (!this.tetris.tryMove(shape, this.tetris.getPoint())) {
                shape.rotateLeft();
            }
        }
        if (key == KeyEvent.VK_UP) {
            shape.rotateLeft();
            if (!this.tetris.tryMove(shape, this.tetris.getPoint())) {
                shape.rotateRight();
            }
        }
        if (key == KeyEvent.VK_SPACE) {
            this.tetris.dropDown();
        }
        if (key == KeyEvent.VK_D) {
            this.tetris.oneLineDown();
        }
    }

}
