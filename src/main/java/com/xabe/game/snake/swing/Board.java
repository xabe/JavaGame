package com.xabe.game.snake.swing;

import java.io.IOException;

import javax.swing.JFrame;

public class Board extends JFrame {

    public Board() throws IOException {
        this.add(new GameImpl());
        this.setResizable(false);
        this.pack();
        this.setTitle("Snake");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
