package com.xabe.game.tetris.swing;

import java.util.function.Consumer;
import java.util.stream.IntStream;

import com.xabe.game.tetris.common.Command;
import com.xabe.game.tetris.common.Point;
import com.xabe.game.tetris.common.Shape;
import com.xabe.game.tetris.common.ShapeFactory;
import com.xabe.game.tetris.common.ShapeType;
import com.xabe.game.tetris.common.Tetris;
import com.xabe.game.tetris.common.TetrisImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

import static com.xabe.game.tetris.common.TetrisImpl.BOARD_HEIGHT;
import static com.xabe.game.tetris.common.TetrisImpl.BOARD_WIDTH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@TestMethodOrder(OrderAnnotation.class)
class TetrisImplTest {

    private Command commandRepaint;
    private Consumer<String> consumerText;
    private Command commandTimer;
    private MockedStatic<ShapeFactory> mockedShapeFactory;

    private Tetris tetris;

    @BeforeEach
    public void setUp() throws Exception {
        this.commandRepaint = mock(Command.class);
        this.consumerText = mock(Consumer.class);
        this.commandTimer = mock(Command.class);
        this.mockedShapeFactory = mockStatic(ShapeFactory.class);
        this.mockedShapeFactory.when(ShapeFactory::getRandomShape).thenReturn(new Shape(ShapeType.SQUARE_SHAPE));
        this.tetris = new TetrisImpl(this.commandRepaint, this.consumerText, this.commandTimer);
        this.tetris.start();
    }

    @AfterEach
    public void close() {
        this.mockedShapeFactory.close();
    }

    @Test
    @Order(1)
    public void shouldBoardEmptyAndShapeRandomInitialGame() throws Exception {

        final Shape shape = this.tetris.getShape();

        assertThat(shape, is(notNullValue()));
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                assertThat(this.tetris.shapeAt(j, BOARD_HEIGHT - i - 1), is(ShapeType.NO_SHAPE));
            }
        }
    }

    @Test
    @Order(2)
    public void shouldDropDown() throws Exception {
        final Shape shapeOld = this.tetris.getShape();
        this.mockedShapeFactory.when(() -> ShapeFactory.getRandomShape())
            .thenReturn(new Shape(ShapeType.MIRRORED_L_SHAPE));

        this.tetris.dropDown();

        final Shape shapeNew = this.tetris.getShape();
        assertThat(shapeOld, is(notNullValue()));
        assertThat(shapeNew, is(notNullValue()));
        assertThat(shapeNew, is(not(sameInstance(shapeOld))));
        for (int i = 0; i < 4; i++) {
            final Point coordinate = shapeOld.getCoordinate(i);
            final int x = 6 + coordinate.getX();
            final int y = 1 - coordinate.getY();
            assertThat(this.tetris.shapeAt(x, y), is(shapeOld.getType()));
        }
    }

    @Test
    @Order(3)
    public void shouldRemoveLine() throws Exception {
        final ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        final Shape shape = this.tetris.getShape();
        final Point point = this.tetris.getPoint();

        this.tetris.tryMove(shape, Point.of(0, point.getY()));
        this.tetris.dropDown();

        this.tetris.tryMove(shape, Point.of(2, point.getY()));
        this.tetris.dropDown();

        this.tetris.tryMove(shape, Point.of(4, point.getY()));
        this.tetris.dropDown();

        this.tetris.tryMove(shape, Point.of(6, point.getY()));
        this.tetris.dropDown();

        this.tetris.tryMove(shape, Point.of(8, point.getY()));
        this.tetris.dropDown();

        verify(this.consumerText, times(5)).accept(argument.capture());

        assertThat(argument.getAllValues().get(4), is("2"));

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                assertThat(this.tetris.shapeAt(j, BOARD_HEIGHT - i - 1), is(ShapeType.NO_SHAPE));
            }
        }
    }

    @Test
    @Order(4)
    public void shouldGameOver() throws Exception {
        final ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        final Shape shape = this.tetris.getShape();
        final Point point = this.tetris.getPoint();

        IntStream.range(0, 11).forEach(i -> {
            this.tetris.tryMove(shape, point);
            this.tetris.dropDown();
        });

        verify(this.consumerText, times(12)).accept(argument.capture());

        assertThat(argument.getAllValues().get(11), is("Game over. Score: 0"));
        assertThat(this.tetris.getShape(), is(ShapeFactory.NO_SHAPE));
    }

    @Test
    @Order(5)
    public void shouldPausedGame() throws Exception {
        //Given
        final ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

        //When
        this.tetris.pause();

        //Then
        verify(this.consumerText, times(1)).accept(argument.capture());
        assertThat(argument.getValue(), is("Paused"));
        verify(this.commandRepaint, atLeast(1)).execute();
    }

    @Test
    @Order(6)
    public void shouldBackGame() throws Exception {
        //Given
        final ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

        //When
        this.tetris.pause();
        this.tetris.pause();

        //Then
        verify(this.consumerText, atLeast(2)).accept(argument.capture());
        assertThat(argument.getAllValues().get(0), is("Paused"));
        assertThat(argument.getAllValues().get(1), is("0"));
        verify(this.commandRepaint, atLeast(2)).execute();
    }

    @Test
    @Order(7)
    public void notDoGameCycleWhenPausedGame() throws Exception {
        //Given
        this.tetris.pause();

        //When
        this.tetris.doGameCycle();

        //Then
        verify(this.commandRepaint, times(2)).execute();
    }

    @Test
    @Order(8)
    public void doGameCycleWhenNotPausedGame() throws Exception {
        //Given

        //When
        this.tetris.doGameCycle();

        //Then
        verify(this.commandRepaint, times(3)).execute();
    }


}