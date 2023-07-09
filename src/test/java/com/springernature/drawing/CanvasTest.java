package com.springernature.drawing;

import org.junit.jupiter.api.Test;

import static com.springernature.drawing.io.UserIO.NEW_LINE;
import static org.junit.jupiter.api.Assertions.*;

public class CanvasTest {

    @Test
    public void shouldHaveBasicStringRepresentation() {
        final Canvas canvas = new Canvas(3, 5);

        assertEquals("-----"+ NEW_LINE +
                "|   |"+ NEW_LINE +
                "|   |"+ NEW_LINE +
                "|   |"+ NEW_LINE +
                "|   |"+ NEW_LINE +
                "|   |"+ NEW_LINE +
                "-----", canvas.toString());
    }

    @Test
    public void shouldBeImmutable(){
        final Canvas before = new Canvas(1, 1);

        before.paint(1,1);

        assertEquals(' ', before.get(1, 1));
    }

    @Test
    public void shouldAllowPaintingUsingCoordinatesStartingWith1() {
        final Canvas canvas = new Canvas(1, 1);

        final Canvas paintedCanvas = canvas.paint(1, 1);

        assertEquals(
                "---"+ NEW_LINE +
                        "|x|"+ NEW_LINE +
                        "---",
                paintedCanvas.toString()
        );
    }

    @Test
    public void shouldAllowPaintingOnOtherCoordinates() {
        final Canvas canvas = new Canvas(5, 3);

        final Canvas paintedCanvas = canvas.paint(5, 1);

        assertEquals(
                "-------"+ NEW_LINE +
                        "|    x|"+ NEW_LINE +
                        "|     |"+ NEW_LINE +
                        "|     |"+ NEW_LINE +
                        "-------"
                , paintedCanvas.toString()
        );
    }

    @Test
    public void shouldIgnorePaintingOutOfBoundaries() {
        final Canvas canvas = new Canvas(1, 1);

        final Canvas paintedCanvas = canvas.paint(2, 3);

        assertEquals(
                "---"+ NEW_LINE +
                        "| |"+ NEW_LINE +
                        "---",
                paintedCanvas.toString()
        );
    }

    @Test
    public void shouldAllowRetrievingTheCurrentValueOfACoordinate() {
        final Canvas canvas = new Canvas(4, 4);

        final Canvas paintedCanvas = canvas.paint(2, 3);

        assertEquals('x', paintedCanvas.get(2, 3));
    }

    @Test
    public void shouldReturnEmptyForInvalidCoordinates() {
        final Canvas canvas = new Canvas(1, 1);

        final Canvas paintedCanvas = canvas.paint(2, 3);

        assertEquals(' ', paintedCanvas.get(9, 0));
    }

    @Test
    public void shouldCheckValidCoordinates() {
        final Canvas canvas = new Canvas(1, 1);

        assertTrue(canvas.isValid(1, 1));
        assertFalse(canvas.isValid(0, 0));
        assertFalse(canvas.isValid(2, 2));
    }
}