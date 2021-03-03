package com.xabe.game.snake.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.xabe.game.snake.common.DirectionType;
import com.xabe.game.snake.common.Game;

public class GameImpl extends JPanel implements ActionListener, Game {

    public static final int B_WIDTH = 300;
    public static final int B_HEIGHT = 300;

    private boolean inGame = true;
    private final Timer timer;
    private final Food foot;
    private final Snake snake;

    public GameImpl() throws IOException {
        this.snake = new Snake(this);
        this.foot = new Food();
        this.timer = new Timer(130, this);
        this.addKeyListener(new Input(this));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        this.initGame();
    }

    private void initGame() {
        this.foot.locateApple();
        this.snake.locateSnake();
        this.timer.start();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.doDrawing(g);
    }

    private void doDrawing(final Graphics g) {
        if (this.inGame) {
            this.foot.render(g);
            this.snake.render(g);
        } else {
            this.gameOver(g);
            this.timer.stop();
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(final Graphics g) {
        final String msg = "Game Over";
        final Font small = new Font("Helvetica", Font.BOLD, 14);
        final FontMetrics metr = this.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    @Override
    public void setInGame(final boolean inGame) {
        this.inGame = inGame;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (this.inGame) {
            this.checkApple();
            this.snake.checkCollision();
            this.snake.move();
        }
        this.repaint();
    }

    private void checkApple() {
        if ((this.snake.getHead().equals(this.foot.getApple()))) {
            this.snake.add();
            this.foot.locateApple();
        }
    }

    @Override
    public DirectionType getDirectionType() {
        return this.snake.getDirectionType();
    }

    @Override
    public void setDirectionType(final DirectionType directionType) {
        this.snake.setDirectionType(directionType);
    }
}
