package com.xabe.game.snake.swing;

import java.awt.Graphics;
import java.util.List;

import com.xabe.game.snake.common.DirectionType;
import com.xabe.game.snake.common.Game;
import com.xabe.game.snake.common.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class SnakeTest {

    private Game game;
    private Snake snake;

    @BeforeEach
    public void setUp() throws Exception {
        this.game = mock(Game.class);
        this.snake = new Snake(this.game);
    }

    @Test
    public void shouldLocateSnake() throws Exception {
        //Given

        //When
        this.snake.locateSnake();

        //Then
        final List<Point> result = this.snake.getPoints();
        assertThat(result, is(notNullValue()));
        assertThat(result, is(hasSize(3)));
        assertThat(result.get(0).getImage(), is(not(result.get(1).getImage())));
        assertThat(this.snake.getDirectionType(), is(DirectionType.RIGHT));
    }

    @Test
    public void shouldRenderSnake() throws Exception {
        //Given
        final Graphics graphics = mock(Graphics.class);
        this.snake.locateSnake();

        //When
        this.snake.render(graphics);

        //Then
        verify(graphics, times(3)).drawImage(any(), anyInt(), anyInt(), eq(null));
    }

    @Test
    public void shouldMoveSnakeRight() throws Exception {
        //Given
        this.snake.locateSnake();
        this.snake.setDirectionType(DirectionType.RIGHT);
        final Point headBefore = this.snake.getHead().clone();

        //When
        this.snake.move();

        //Then
        assertThat(this.snake.getHead(), is(not(headBefore)));
        assertThat(this.snake.getPoints().get(1), is(headBefore));
        assertThat(this.snake.getHead().getX(), is(headBefore.getX() + 10));
        assertThat(this.snake.getHead().getY(), is(headBefore.getY()));
    }

    @Test
    public void shouldMoveSnakeLeft() throws Exception {
        //Given
        this.snake.locateSnake();
        this.snake.setDirectionType(DirectionType.LEFT);
        final Point headBefore = this.snake.getHead().clone();

        //When
        this.snake.move();

        //Then
        assertThat(this.snake.getHead(), is(not(headBefore)));
        assertThat(this.snake.getPoints().get(1), is(headBefore));
        assertThat(this.snake.getHead().getX(), is(headBefore.getX() - 10));
        assertThat(this.snake.getHead().getY(), is(headBefore.getY()));
    }

    @Test
    public void shouldMoveSnakeUp() throws Exception {
        //Given
        this.snake.locateSnake();
        this.snake.setDirectionType(DirectionType.UP);
        final Point headBefore = this.snake.getHead().clone();

        //When
        this.snake.move();

        //Then
        assertThat(this.snake.getHead(), is(not(headBefore)));
        assertThat(this.snake.getPoints().get(1), is(headBefore));
        assertThat(this.snake.getHead().getX(), is(headBefore.getX()));
        assertThat(this.snake.getHead().getY(), is(headBefore.getY() - 10));
    }

    @Test
    public void shouldMoveSnakeDown() throws Exception {
        //Given
        this.snake.locateSnake();
        this.snake.setDirectionType(DirectionType.DOWN);
        final Point headBefore = this.snake.getHead().clone();

        //When
        this.snake.move();

        //Then
        assertThat(this.snake.getHead(), is(not(headBefore)));
        assertThat(this.snake.getPoints().get(1), is(headBefore));
        assertThat(this.snake.getHead().getX(), is(headBefore.getX()));
        assertThat(this.snake.getHead().getY(), is(headBefore.getY() + 10));
    }

    @Test
    public void shouldAddSnake() throws Exception {
        //Given
        this.snake.locateSnake();
        final int sizeBefore = this.snake.getPoints().size();

        //When
        this.snake.add();

        //Then
        assertThat(this.snake.getPoints(), is(hasSize(sizeBefore + 1)));
    }

    @Test
    public void shouldNotCheckCollision() throws Exception {
        //Given
        this.snake.locateSnake();

        //When
        this.snake.checkCollision();

        //Then
        verify(this.game, never()).setInGame(anyBoolean());
    }

    @Test
    public void shouldCheckCollisionUpBroad() throws Exception {
        //Given
        this.snake.locateSnake();
        this.snake.getHead().setY(GameImpl.B_HEIGHT);

        //When
        this.snake.checkCollision();

        //Then
        verify(this.game).setInGame(eq(false));
    }

    @Test
    public void shouldCheckCollisionDownBroad() throws Exception {
        //Given
        this.snake.locateSnake();
        this.snake.getHead().setY(-1);

        //When
        this.snake.checkCollision();

        //Then
        verify(this.game).setInGame(eq(false));
    }

    @Test
    public void shouldCheckCollisionLeftBroad() throws Exception {
        //Given
        this.snake.locateSnake();
        this.snake.getHead().setX(-1);

        //When
        this.snake.checkCollision();

        //Then
        verify(this.game).setInGame(eq(false));
    }

    @Test
    public void shouldCheckCollisionRightBroad() throws Exception {
        //Given
        this.snake.locateSnake();
        this.snake.getHead().setX(GameImpl.B_WIDTH);

        //When
        this.snake.checkCollision();

        //Then
        verify(this.game).setInGame(eq(false));
    }

    @Test
    public void shouldNotCheckCollisionPoints() throws Exception {
        //Given
        this.snake.locateSnake();
        this.snake.add();
        this.snake.move();
        this.snake.add();
        this.snake.move();

        //When
        this.snake.checkCollision();

        //Then
        verify(this.game, never()).setInGame(anyBoolean());
    }

    @Test
    public void shouldCheckCollisionPoints() throws Exception {
        //Given
        this.snake.locateSnake();
        this.snake.add();
        this.snake.move();
        this.snake.add();
        this.snake.move();
        this.snake.getHead().setX(this.snake.getPoints().get(4).getX());
        this.snake.getHead().setY(this.snake.getPoints().get(4).getY());

        //When
        this.snake.checkCollision();

        //Then
        verify(this.game).setInGame(eq(false));
    }

}