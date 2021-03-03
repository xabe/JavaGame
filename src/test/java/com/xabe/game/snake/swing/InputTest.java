package com.xabe.game.snake.swing;

import java.awt.Component;
import java.awt.event.KeyEvent;

import com.ginsberg.junit.exit.ExpectSystemExit;
import com.xabe.game.snake.common.DirectionType;
import com.xabe.game.snake.common.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InputTest {

    private Game game;

    private Input input;

    @BeforeEach
    public void setUp() throws Exception {
        this.game = mock(Game.class);
        this.input = new Input(this.game);
    }

    @Test
    public void givenAKeyEventLeftWhenInvokeKeyPressedSetDirection() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_LEFT, ' ');
        when(this.game.getDirectionType()).thenReturn(DirectionType.UP);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.game).setDirectionType(eq(DirectionType.LEFT));
    }

    @Test
    public void givenAKeyEventLeftWhenInvokeKeyPressedNotSetDirection() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_LEFT, ' ');
        when(this.game.getDirectionType()).thenReturn(DirectionType.RIGHT);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.game, never()).setDirectionType(any());
    }

    @Test
    public void givenAKeyEventRightWhenInvokeKeyPressedSetDirection() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_RIGHT, ' ');
        when(this.game.getDirectionType()).thenReturn(DirectionType.UP);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.game).setDirectionType(eq(DirectionType.RIGHT));
    }

    @Test
    public void givenAKeyEventRightWhenInvokeKeyPressedNotSetDirection() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_RIGHT, ' ');
        when(this.game.getDirectionType()).thenReturn(DirectionType.LEFT);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.game, never()).setDirectionType(any());
    }

    @Test
    public void givenAKeyEventUpWhenInvokeKeyPressedSetDirection() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_UP, ' ');
        when(this.game.getDirectionType()).thenReturn(DirectionType.LEFT);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.game).setDirectionType(eq(DirectionType.UP));
    }

    @Test
    public void givenAKeyEventUpWhenInvokeKeyPressedNotSetDirection() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_UP, ' ');
        when(this.game.getDirectionType()).thenReturn(DirectionType.DOWN);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.game, never()).setDirectionType(any());
    }

    @Test
    public void givenAKeyEventDownWhenInvokeKeyPressedSetDirection() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_DOWN, ' ');
        when(this.game.getDirectionType()).thenReturn(DirectionType.RIGHT);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.game).setDirectionType(eq(DirectionType.DOWN));
    }

    @Test
    public void givenAKeyEventDownWhenInvokeKeyPressedNotSetDirection() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_DOWN, ' ');
        when(this.game.getDirectionType()).thenReturn(DirectionType.UP);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.game, never()).setDirectionType(any());
    }

    @Test
    @ExpectSystemExit
    public void givenAKeyEventExitWhenInvokeKeyPressedExit() throws Exception {
        //Given
        final KeyEvent keyEvent = new KeyEvent(mock(Component.class), 0, 0L, 0, KeyEvent.VK_ESCAPE, ' ');
        when(this.game.getDirectionType()).thenReturn(DirectionType.UP);

        //When
        this.input.keyPressed(keyEvent);

        //Then
        verify(this.game, never()).setDirectionType(any());
    }

}