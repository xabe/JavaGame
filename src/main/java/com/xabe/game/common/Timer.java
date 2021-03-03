package com.xabe.game.common;

import javafx.animation.AnimationTimer;

public class Timer extends AnimationTimer {


    private final int speed;
    private final TimeListener timeListener;
    private long lastTick;

    public Timer(final TimeListener timeListener) {
        this.timeListener = timeListener;
        this.speed = 1000000000 / 5;
        this.lastTick = 0;
    }

    public Timer(final TimeListener timeListener, final int speed) {
        this.timeListener = timeListener;
        this.speed = speed;
        this.lastTick = 0;
    }


    @Override
    public void handle(final long now) {
        if (this.lastTick == 0) {
            this.lastTick = now;
            this.timeListener.tick();
            return;
        }
        if (now - this.lastTick > this.speed) {
            this.lastTick = now;
            this.timeListener.tick();
        }
    }
}
