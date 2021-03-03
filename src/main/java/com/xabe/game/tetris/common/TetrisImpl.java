package com.xabe.game.tetris.common;

import java.util.function.Consumer;

public class TetrisImpl implements Tetris {

    public static final int HEIGHT = 400;
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 22;
    public static final int INITIAL_POINT_X = BOARD_WIDTH / 2 + 1;
    public static final int INITIAL_POINT_Y = BOARD_HEIGHT - 1;
    public static final String PAUSED = "Paused";
    public static final int SQUARE_HEIGHT = 20;
    public static final int SQUARE_HEIGHT_LINE = SQUARE_HEIGHT - 1;
    public static final int SQUARE_WIDTH = 20;
    public static final int SQUARE_WIDTH_LINE = SQUARE_WIDTH - 1;
    public static final int BOARD_TOP = HEIGHT - BOARD_HEIGHT * SQUARE_HEIGHT;

    private boolean isPaused = false;
    private int numLinesRemoved = 0;
    private final Point point;
    private final Command commandStop;
    private final Command commandRepaint;
    private final Consumer<String> commandText;
    private final ShapeType[] board;
    private Shape concurrentShape;

    public TetrisImpl(final Command commandRepaint, final Consumer<String> commandText, final Command commandStop) {
        this.commandRepaint = commandRepaint;
        this.commandText = commandText;
        this.commandStop = commandStop;
        this.point = Point.of(INITIAL_POINT_X, INITIAL_POINT_Y);
        this.board = new ShapeType[BOARD_WIDTH * BOARD_HEIGHT];
        this.clearBoard();
    }

    @Override
    public void start() {
        this.newPiece();
    }

    @Override
    public ShapeType shapeAt(final int x, final int y) {
        return this.board[(y * BOARD_WIDTH) + x];
    }

    @Override
    public void pause() {
        this.isPaused = !this.isPaused;
        if (this.isPaused) {
            this.commandText.accept(PAUSED);
        } else {
            this.commandText.accept(String.valueOf(this.numLinesRemoved));
        }
        this.commandRepaint.execute();
    }

    @Override
    public void dropDown() {
        while (this.point.getY() > 0) {
            if (!this.tryMove(this.concurrentShape, this.point.minusY(1))) {
                break;
            }
        }
        this.pieceDropped();
    }

    @Override
    public void oneLineDown() {
        if (!this.tryMove(this.concurrentShape, this.point.minusY(1))) {
            this.pieceDropped();
        }
    }

    private void pieceDropped() {
        for (int i = 0; i < 4; i++) {
            final Point coordinate = this.concurrentShape.getCoordinate(i);
            final int x = this.point.getX() + coordinate.getX();
            final int y = this.point.getY() - coordinate.getY();
            this.board[(y * BOARD_WIDTH) + x] = this.concurrentShape.getType();
        }

        this.numLinesRemoved += this.removeFullLines();
        this.commandText.accept(String.valueOf(this.numLinesRemoved));
        this.newPiece();
    }

    private void newPiece() {
        this.concurrentShape = ShapeFactory.getRandomShape();
        this.point.set(INITIAL_POINT_X, INITIAL_POINT_Y + this.concurrentShape.minY());
        if (!this.tryMove(this.concurrentShape, this.point)) {
            this.concurrentShape = ShapeFactory.NO_SHAPE;
            this.commandStop.execute();
            this.commandText.accept(String.format("Game over. Score: %d", this.numLinesRemoved));
        }
    }

    @Override
    public boolean tryMove(final Shape newPiece, final Point newPoint) {
        for (int i = 0; i < 4; i++) {
            final Point coordinate = newPiece.getCoordinate(i);
            final int x = newPoint.getX() + coordinate.getX();
            final int y = newPoint.getY() - coordinate.getY();
            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT || this.shapeAt(x, y) != ShapeType.NO_SHAPE) {
                return false;
            }
        }
        this.concurrentShape = newPiece;
        this.point.copy(newPoint);
        this.commandRepaint.execute();
        return true;
    }

    private int removeFullLines() {
        int numFullLines = 0;
        for (int y = BOARD_HEIGHT - 1; y >= 0; y--) {
            boolean lineIsFull = true;
            for (int x = 0; x < BOARD_WIDTH; x++) {
                if (this.shapeAt(x, y) == ShapeType.NO_SHAPE) {
                    lineIsFull = false;
                    break;
                }
            }
            if (lineIsFull) {
                numFullLines++;
                for (int k = y; k < BOARD_HEIGHT - 1; k++) {
                    for (int x = 0; x < BOARD_WIDTH; x++) {
                        this.board[(k * BOARD_WIDTH) + x] = this.shapeAt(x, k + 1);
                    }
                }
            }
        }
        return numFullLines;
    }

    @Override
    public void doGameCycle() {
        if (this.isPaused) {
            return;
        }
        this.oneLineDown();
        this.commandRepaint.execute();
    }

    @Override
    public Shape getShape() {
        return this.concurrentShape;
    }

    @Override
    public Point getPoint() {
        return this.point;
    }

    private void clearBoard() {
        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {
            this.board[i] = ShapeType.NO_SHAPE;
        }
    }

}
