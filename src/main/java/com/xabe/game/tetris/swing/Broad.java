package com.xabe.game.tetris.swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Broad extends JFrame {


    public Broad() {
        final JLabel statusbar = new JLabel(" 0");
        this.add(statusbar, BorderLayout.SOUTH);
        final GameImpl game = new GameImpl(statusbar);
        this.add(game, BorderLayout.CENTER);
        game.start();
        this.setResizable(false);
        this.pack();
        this.setTitle("Tetris");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
