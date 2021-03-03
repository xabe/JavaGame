package com.xabe.game.tetris.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Board extends Application {

    @Override
    public void start(final Stage primaryStage) {
        final Label statusbar = new Label(" 0");
        final GridPane root = new GridPane();

        final GameImpl center = new GameImpl(statusbar);
        root.add(center, 0, 0);
        center.start();

        final BorderPane bot = new BorderPane();
        statusbar.setTextAlignment(TextAlignment.LEFT);
        bot.setLeft(statusbar);
        root.add(bot, 0, 1);
        final Scene scene = new Scene(root, Color.WHITESMOKE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tetris");
        primaryStage.show();
    }
}
