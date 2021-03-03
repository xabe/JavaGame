package com.xabe.game.snake.swing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.xabe.game.snake.common.DirectionType;
import com.xabe.game.snake.common.Game;

public class Input extends KeyAdapter {

    private final Game game;

    public Input(final Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();
        final DirectionType actualDirection = this.game.getDirectionType();
        if (key == KeyEvent.VK_LEFT && actualDirection != DirectionType.RIGHT) {
            this.game.setDirectionType(DirectionType.LEFT);
        }
        if (key == KeyEvent.VK_RIGHT && actualDirection != DirectionType.LEFT) {
            this.game.setDirectionType(DirectionType.RIGHT);
        }
        if (key == KeyEvent.VK_UP && actualDirection != DirectionType.DOWN) {
            this.game.setDirectionType(DirectionType.UP);
        }
        if (key == KeyEvent.VK_DOWN && actualDirection != DirectionType.UP) {
            this.game.setDirectionType(DirectionType.DOWN);
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

}
