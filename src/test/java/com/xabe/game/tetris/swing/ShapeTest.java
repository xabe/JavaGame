package com.xabe.game.tetris.swing;

import com.xabe.game.tetris.common.Point;
import com.xabe.game.tetris.common.Shape;
import com.xabe.game.tetris.common.ShapeType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

class ShapeTest {

    @Test
    public void shouldRotateLeftZShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.Z_SHAPE);

        //When
        shape.rotateLeft();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.Z_SHAPE));
        assertThat(shape.getCoordinates(), is(not(equalTo((ShapeType.Z_SHAPE.getCoordinates())))));
        assertThat(shape.getCoordinates().get(0), is(Point.of(1, 1)));
        assertThat(shape.getCoordinates().get(1), is(Point.of(0, 1)));
        assertThat(shape.getCoordinates().get(2), is(Point.of(0, 0)));
        assertThat(shape.getCoordinates().get(3), is(Point.of(-1, 0)));
    }

    @Test
    public void shouldRotateRightZShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.Z_SHAPE);

        //When
        shape.rotateRight();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.Z_SHAPE));
        assertThat(shape.getCoordinates(), is(not(equalTo((ShapeType.Z_SHAPE.getCoordinates())))));
        assertThat(shape.getCoordinates().get(0), is(Point.of(-1, -1)));
        assertThat(shape.getCoordinates().get(1), is(Point.of(0, -1)));
        assertThat(shape.getCoordinates().get(2), is(Point.of(0, 0)));
        assertThat(shape.getCoordinates().get(3), is(Point.of(1, 0)));
    }

    @Test
    public void shouldRotateLeftSShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.S_SHAPE);

        //When
        shape.rotateLeft();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.S_SHAPE));
        assertThat(shape.getCoordinates(), is(not(equalTo((ShapeType.S_SHAPE.getCoordinates())))));
        assertThat(shape.getCoordinates().get(0), is(Point.of(-1, 0)));
        assertThat(shape.getCoordinates().get(1), is(Point.of(0, 0)));
        assertThat(shape.getCoordinates().get(2), is(Point.of(0, -1)));
        assertThat(shape.getCoordinates().get(3), is(Point.of(1, -1)));
    }

    @Test
    public void shouldRotateRightSShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.S_SHAPE);

        //When
        shape.rotateRight();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.S_SHAPE));
        assertThat(shape.getCoordinates(), is(not(equalTo((ShapeType.S_SHAPE.getCoordinates())))));
        assertThat(shape.getCoordinates().get(0), is(Point.of(1, 0)));
        assertThat(shape.getCoordinates().get(1), is(Point.of(0, 0)));
        assertThat(shape.getCoordinates().get(2), is(Point.of(0, 1)));
        assertThat(shape.getCoordinates().get(3), is(Point.of(-1, 1)));
    }

    @Test
    public void shouldRotateLeftLineShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.LINE_SHAPE);

        //When
        shape.rotateLeft();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.LINE_SHAPE));
        assertThat(shape.getCoordinates(), is(not(equalTo((ShapeType.LINE_SHAPE.getCoordinates())))));
        assertThat(shape.getCoordinates().get(0), is(Point.of(2, 0)));
        assertThat(shape.getCoordinates().get(1), is(Point.of(1, 0)));
        assertThat(shape.getCoordinates().get(2), is(Point.of(0, 0)));
        assertThat(shape.getCoordinates().get(3), is(Point.of(-1, 0)));
    }

    @Test
    public void shouldRotateRightLineShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.LINE_SHAPE);

        //When
        shape.rotateRight();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.LINE_SHAPE));
        assertThat(shape.getCoordinates(), is(not(equalTo((ShapeType.LINE_SHAPE.getCoordinates())))));
        assertThat(shape.getCoordinates().get(0), is(Point.of(-2, 0)));
        assertThat(shape.getCoordinates().get(1), is(Point.of(-1, 0)));
        assertThat(shape.getCoordinates().get(2), is(Point.of(0, 0)));
        assertThat(shape.getCoordinates().get(3), is(Point.of(1, 0)));
    }

    @Test
    public void shouldRotateLeftTShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.T_SHAPE);

        //When
        shape.rotateLeft();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.T_SHAPE));
        assertThat(shape.getCoordinates(), is(not(equalTo((ShapeType.T_SHAPE.getCoordinates())))));
        assertThat(shape.getCoordinates().get(0), is(Point.of(0, 1)));
        assertThat(shape.getCoordinates().get(1), is(Point.of(0, 0)));
        assertThat(shape.getCoordinates().get(2), is(Point.of(0, -1)));
        assertThat(shape.getCoordinates().get(3), is(Point.of(1, 0)));
    }

    @Test
    public void shouldRotateRightTShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.T_SHAPE);

        //When
        shape.rotateRight();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.T_SHAPE));
        assertThat(shape.getCoordinates(), is(not(equalTo((ShapeType.T_SHAPE.getCoordinates())))));
        assertThat(shape.getCoordinates().get(0), is(Point.of(0, -1)));
        assertThat(shape.getCoordinates().get(1), is(Point.of(0, 0)));
        assertThat(shape.getCoordinates().get(2), is(Point.of(0, 1)));
        assertThat(shape.getCoordinates().get(3), is(Point.of(-1, 0)));
    }

    @Test
    public void shouldRotateLeftSquareShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.SQUARE_SHAPE);

        //When
        shape.rotateLeft();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.SQUARE_SHAPE));
        assertThat(shape.getCoordinates(), is((ShapeType.SQUARE_SHAPE.getCoordinates())));
    }

    @Test
    public void shouldRotateRightSquareShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.SQUARE_SHAPE);

        //When
        shape.rotateRight();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.SQUARE_SHAPE));
        assertThat(shape.getCoordinates(), is((ShapeType.SQUARE_SHAPE.getCoordinates())));
    }

    @Test
    public void shouldRotateLeftLShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.L_SHAPE);

        //When
        shape.rotateLeft();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.L_SHAPE));
        assertThat(shape.getCoordinates(), is(not(equalTo((ShapeType.L_SHAPE.getCoordinates())))));
        assertThat(shape.getCoordinates().get(0), is(Point.of(-1, 1)));
        assertThat(shape.getCoordinates().get(1), is(Point.of(-1, 0)));
        assertThat(shape.getCoordinates().get(2), is(Point.of(0, 0)));
        assertThat(shape.getCoordinates().get(3), is(Point.of(1, 0)));
    }

    @Test
    public void shouldRotateRightLShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.L_SHAPE);

        //When
        shape.rotateRight();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.L_SHAPE));
        assertThat(shape.getCoordinates(), is(not(equalTo((ShapeType.L_SHAPE.getCoordinates())))));
        assertThat(shape.getCoordinates().get(0), is(Point.of(1, -1)));
        assertThat(shape.getCoordinates().get(1), is(Point.of(1, 0)));
        assertThat(shape.getCoordinates().get(2), is(Point.of(0, 0)));
        assertThat(shape.getCoordinates().get(3), is(Point.of(-1, 0)));
    }

    @Test
    public void shouldRotateLeftMirroredLShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.MIRRORED_L_SHAPE);

        //When
        shape.rotateLeft();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.MIRRORED_L_SHAPE));
        assertThat(shape.getCoordinates(), is(not(equalTo((ShapeType.MIRRORED_L_SHAPE.getCoordinates())))));
        assertThat(shape.getCoordinates().get(0), is(Point.of(-1, -1)));
        assertThat(shape.getCoordinates().get(1), is(Point.of(-1, 0)));
        assertThat(shape.getCoordinates().get(2), is(Point.of(0, 0)));
        assertThat(shape.getCoordinates().get(3), is(Point.of(1, 0)));
    }

    @Test
    public void shouldRotateRightMirroredLShape() throws Exception {
        //Given
        final Shape shape = new Shape(ShapeType.MIRRORED_L_SHAPE);

        //When
        shape.rotateRight();

        //Then
        assertThat(shape.getType(), is(notNullValue()));
        assertThat(shape.getType(), is(ShapeType.MIRRORED_L_SHAPE));
        assertThat(shape.getCoordinates(), is(not(equalTo((ShapeType.MIRRORED_L_SHAPE.getCoordinates())))));
        assertThat(shape.getCoordinates().get(0), is(Point.of(1, 1)));
        assertThat(shape.getCoordinates().get(1), is(Point.of(1, 0)));
        assertThat(shape.getCoordinates().get(2), is(Point.of(0, 0)));
        assertThat(shape.getCoordinates().get(3), is(Point.of(-1, 0)));
    }
}