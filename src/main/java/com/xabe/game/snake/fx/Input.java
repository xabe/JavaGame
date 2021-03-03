package com.xabe.game.snake.fx;


import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import com.xabe.game.snake.common.DirectionType;
import com.xabe.game.snake.common.Game;


public class Input implements EventHandler<KeyEvent> {

    private final Game game;

    public Input(final Game game) {
        this.game = game;
    }

    @Override
    public void handle(final KeyEvent keyEvent) {
        final KeyCode key = keyEvent.getCode();
        final DirectionType actualDirection = this.game.getDirectionType();
        if (key == KeyCode.LEFT && actualDirection != DirectionType.RIGHT) {
            this.game.setDirectionType(DirectionType.LEFT);
        }
        if (key == KeyCode.RIGHT && actualDirection != DirectionType.LEFT) {
            this.game.setDirectionType(DirectionType.RIGHT);
        }
        if (key == KeyCode.UP && actualDirection != DirectionType.DOWN) {
            this.game.setDirectionType(DirectionType.UP);
        }
        if (key == KeyCode.DOWN && actualDirection != DirectionType.UP) {
            this.game.setDirectionType(DirectionType.DOWN);
        }
        if (key == KeyCode.ESCAPE) {
            System.exit(0);
        }
    }
}
