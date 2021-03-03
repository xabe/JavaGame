package com.xabe.game.snake.common;

public interface Game {

    void setInGame(boolean inGame);

    DirectionType getDirectionType();

    void setDirectionType(DirectionType left);
}
