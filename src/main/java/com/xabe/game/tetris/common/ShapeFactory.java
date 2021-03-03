package com.xabe.game.tetris.common;

import java.util.Random;

public class ShapeFactory {

    public static final Shape NO_SHAPE = new Shape(ShapeType.NO_SHAPE);
    private static final Random RANDOM = new Random();
    private static final int NUM_ITEMS = ShapeType.values().length - 1;

    private ShapeFactory() {
    }

    public static Shape getRandomShape() {
        final int x = getRandomNumberUsingInts(1, NUM_ITEMS);
        return new Shape(ShapeType.values()[x]);
    }


    private static int getRandomNumberUsingInts(final int min, final int max) {
        return RANDOM.ints(min, max).findFirst().getAsInt();
    }

}
