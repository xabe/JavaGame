package com.xabe.game.snake.fx;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import com.xabe.game.common.TimeListener;
import com.xabe.game.common.Timer;
import com.xabe.game.snake.common.DirectionType;
import com.xabe.game.snake.common.Game;

public class GameImpl extends Application implements TimeListener, Game {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    private boolean inGame = true;
    private Food foot;
    private Snake snake;
    private GraphicsContext gc;
    private Timer timer;

    @Override
    public void start(final Stage primaryStage) {
        this.snake = new Snake(this);
        this.foot = new Food();
        final VBox root = new VBox();
        final Canvas c = new Canvas(WIDTH, HEIGHT);
        this.gc = c.getGraphicsContext2D();
        root.getChildren().add(c);
        this.timer = new Timer(this);
        final Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new Input(this));
        scene.getStylesheets().add(this.getClass().getResource("/application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("SNAKE GAME");
        primaryStage.show();
        this.initGame();
    }

    private void initGame() {
        this.foot.locateApple();
        this.snake.locateSnake();
        this.timer.start();
    }

    @Override
    public void tick() {
        if (this.inGame) {
            this.checkApple();
            this.snake.checkCollision();
            this.snake.move();
        }
        this.repaint();
    }

    private void repaint() {
        // fill background
        this.gc.setFill(Color.BLACK);
        this.gc.fillRect(0, 0, WIDTH, HEIGHT);
        // score
        this.gc.setFill(Color.WHITE);
        this.gc.setFont(new Font("", 30));
        this.gc.fillText("Score: " + this.foot.getScore(), 10, 30);
        if (this.inGame) {
            this.foot.render(this.gc);
            this.snake.render(this.gc);
        } else {
            this.gameOver(this.gc);
            this.timer.stop();
        }
    }

    private void gameOver(final GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.setFont(new Font("", 50));
        gc.fillText("GAME OVER", 100, 250);
    }

    private void checkApple() {
        if ((this.snake.getHead().equals(this.foot.getApple()))) {
            this.snake.add();
            this.foot.locateApple();
        }
    }

    @Override
    public void setInGame(final boolean inGame) {
        this.inGame = inGame;
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

