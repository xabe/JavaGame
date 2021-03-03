package com.xabe.game.tetris.fx;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import com.xabe.game.tetris.common.Shape;
import com.xabe.game.tetris.common.ShapeType;
import com.xabe.game.tetris.common.Tetris;

public class Input implements EventHandler<KeyEvent> {

    private final Tetris tetris;

    public Input(final Tetris tetris) {
        this.tetris = tetris;
    }

    @Override
    public void handle(final KeyEvent keyEvent) {
        final Shape shape = this.tetris.getShape();
        if (shape.getType() == ShapeType.NO_SHAPE) {
            return;
        }
        final KeyCode key = keyEvent.getCode();
        if (key == KeyCode.P) {
            this.tetris.pause();
        }
        if (key == KeyCode.LEFT) {
            this.tetris.tryMove(shape, this.tetris.getPoint().minusX(1));
        }
        if (key == KeyCode.RIGHT) {
            this.tetris.tryMove(shape, this.tetris.getPoint().addX(1));
        }
        if (key == KeyCode.DOWN) {
            shape.rotateRight();
            if (!this.tetris.tryMove(shape, this.tetris.getPoint())) {
                shape.rotateLeft();
            }
        }
        if (key == KeyCode.UP) {
            shape.rotateLeft();
            if (!this.tetris.tryMove(shape, this.tetris.getPoint())) {
                shape.rotateRight();
            }
        }
        if (key == KeyCode.SPACE) {
            this.tetris.dropDown();
        }
        if (key == KeyCode.D) {
            this.tetris.oneLineDown();
        }
    }
}
