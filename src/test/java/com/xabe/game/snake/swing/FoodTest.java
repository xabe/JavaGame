package com.xabe.game.snake.swing;

import java.awt.Graphics;

import com.xabe.game.snake.common.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class FoodTest {

    private Food food;

    @BeforeEach
    public void setUp() throws Exception {
        this.food = new Food();
    }

    @Test
    public void shouldCreatePointApple() throws Exception {
        //Given
        this.food.locateApple();

        //When
        final Point result = this.food.getApple();

        //Then
        assertThat(result, is(notNullValue()));
        assertThat(result.getX(), is(notNullValue()));
        assertThat(result.getY(), is(notNullValue()));
    }

    @Test
    public void shouldRenderApple() throws Exception {
        //Given
        final Graphics graphics = mock(Graphics.class);

        //When
        this.food.render(graphics);

        //Then
        verify(graphics).drawImage(any(), anyInt(), anyInt(), eq(null));
    }

}