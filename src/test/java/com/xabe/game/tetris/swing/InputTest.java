package com.xabe.game.tetris.swing;

import java.awt.Component;
import java.awt.event.KeyEvent;

import com.xabe.game.tetris.common.Point;
import com.xabe.game.tetris.common.Shape;
import com.xabe.game.tetris.common.ShapeFactory;
import com.xabe.game.tetris.common.ShapeType;
import com.xabe.game.tetris.common.Tetris;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InputTest {

    private Shape shape = new Shape(ShapeType.LINE_SHAPE);

    private Tetris tetris;

    private Input input;

    @BeforeEach
    public void setUp() throws Exception {
        this.shape = spy(new Shape(ShapeType.LINE_SHAPE));
        this.tetris = mock(Tetris.class);
        this.input = new Input(this.tetris);
    }

    @Test
    public void notShouldNothingWhenShapeNotValid() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_P, ' ');
        when(this.tetris.getShape()).thenReturn(ShapeFactory.NO_SHAPE);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.tetris, never()).pause();
        verify(this.tetris, never()).tryMove(any(), any());
        verify(this.tetris, never()).dropDown();
        verify(this.tetris, never()).oneLineDown();
    }

    @Test
    public void shouldPauseGameWhenPressP() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_P, ' ');
        when(this.tetris.getShape()).thenReturn(this.shape);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.tetris).pause();
    }

    @Test
    public void shouldMoveLeftGameWhenPressLeft() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_LEFT, ' ');
        when(this.tetris.getShape()).thenReturn(this.shape);
        when(this.tetris.getPoint()).thenReturn(Point.of(0, 0));
        final ArgumentCaptor<Point> argumentCaptor = ArgumentCaptor.forClass(Point.class);
        when(this.tetris.tryMove(eq(this.shape), argumentCaptor.capture())).thenReturn(true);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        final Point result = argumentCaptor.getValue();
        assertThat(result, is(notNullValue()));
        assertThat(result.getX(), is(-1));
        assertThat(result.getY(), is(0));
    }

    @Test
    public void shouldMoveRightGameWhenPressRight() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_RIGHT, ' ');
        when(this.tetris.getShape()).thenReturn(this.shape);
        when(this.tetris.getPoint()).thenReturn(Point.of(0, 0));
        final ArgumentCaptor<Point> argumentCaptor = ArgumentCaptor.forClass(Point.class);
        when(this.tetris.tryMove(eq(this.shape), argumentCaptor.capture())).thenReturn(true);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        final Point result = argumentCaptor.getValue();
        assertThat(result, is(notNullValue()));
        assertThat(result.getX(), is(1));
        assertThat(result.getY(), is(0));
    }

    @Test
    public void shouldRotateRightGameWhenPressDownIsValid() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_DOWN, ' ');
        when(this.tetris.getShape()).thenReturn(this.shape);
        when(this.tetris.getPoint()).thenReturn(Point.of(0, 0));
        final ArgumentCaptor<Point> argumentCaptor = ArgumentCaptor.forClass(Point.class);
        when(this.tetris.tryMove(eq(this.shape), argumentCaptor.capture())).thenReturn(true);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.shape).rotateRight();
        verify(this.shape, never()).rotateLeft();
        final Point result = argumentCaptor.getValue();
        assertThat(result, is(notNullValue()));
        assertThat(result.getX(), is(0));
        assertThat(result.getY(), is(0));
    }

    @Test
    public void notShouldRotateRightGameWhenPressDownIsNotValid() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_DOWN, ' ');
        when(this.tetris.getShape()).thenReturn(this.shape);
        when(this.tetris.getPoint()).thenReturn(Point.of(0, 0));
        final ArgumentCaptor<Point> argumentCaptor = ArgumentCaptor.forClass(Point.class);
        when(this.tetris.tryMove(eq(this.shape), argumentCaptor.capture())).thenReturn(false);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.shape).rotateRight();
        verify(this.shape).rotateLeft();
        final Point result = argumentCaptor.getValue();
        assertThat(result, is(notNullValue()));
        assertThat(result.getX(), is(0));
        assertThat(result.getY(), is(0));
    }

    @Test
    public void shouldRotateLeftGameWhenPressDownIsValid() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_UP, ' ');
        when(this.tetris.getShape()).thenReturn(this.shape);
        when(this.tetris.getPoint()).thenReturn(Point.of(0, 0));
        final ArgumentCaptor<Point> argumentCaptor = ArgumentCaptor.forClass(Point.class);
        when(this.tetris.tryMove(eq(this.shape), argumentCaptor.capture())).thenReturn(true);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.shape).rotateLeft();
        verify(this.shape, never()).rotateRight();
        final Point result = argumentCaptor.getValue();
        assertThat(result, is(notNullValue()));
        assertThat(result.getX(), is(0));
        assertThat(result.getY(), is(0));
    }

    @Test
    public void notShouldRotateLeftGameWhenPressDownIsNotValid() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_UP, ' ');
        when(this.tetris.getShape()).thenReturn(this.shape);
        when(this.tetris.getPoint()).thenReturn(Point.of(0, 0));
        final ArgumentCaptor<Point> argumentCaptor = ArgumentCaptor.forClass(Point.class);
        when(this.tetris.tryMove(eq(this.shape), argumentCaptor.capture())).thenReturn(false);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.shape).rotateLeft();
        verify(this.shape).rotateRight();
        final Point result = argumentCaptor.getValue();
        assertThat(result, is(notNullValue()));
        assertThat(result.getX(), is(0));
        assertThat(result.getY(), is(0));
    }

    @Test
    public void shouldDownShapeGameWhenPressSpace() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_SPACE, ' ');
        when(this.tetris.getShape()).thenReturn(this.shape);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.tetris).dropDown();
    }

    @Test
    public void shouldDownOneLineShapeGameWhenPressD() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_D, ' ');
        when(this.tetris.getShape()).thenReturn(this.shape);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.tetris).oneLineDown();
    }
}