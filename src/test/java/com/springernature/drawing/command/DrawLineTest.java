package com.springernature.drawing.command;

import com.springernature.drawing.Canvas;
import com.springernature.drawing.io.UserIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.springernature.drawing.io.UserIO.NEW_LINE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DrawLineTest {

    @Mock
    private UserIO io;
    private DrawLine drawLine;

    @BeforeEach
    public void setUp() {
        drawLine = new DrawLine();
    }

    @Test
    public void shouldDrawSinglePoint() {
        final Canvas canvasBefore = new Canvas(2, 2);

        final Canvas canvas = drawLine.handle(canvasBefore, "L 2 1 2 1", io);

        assertEquals(
                "----"+ NEW_LINE +
                        "| x|"+ NEW_LINE +
                        "|  |"+ NEW_LINE +
                        "----",
                canvas.toString()
        );
    }

    @Test
    public void shouldDrawHorizontalLine() {
        final Canvas canvasBefore = new Canvas(5, 2);

        final Canvas canvas = drawLine.handle(canvasBefore, "L 2 2 5 2", io);

        assertEquals(
                "-------"+ NEW_LINE +
                "|     |"+ NEW_LINE +
                "| xxxx|"+ NEW_LINE +
                "-------", canvas.toString());
    }

    @Test
    public void shouldDrawVerticalLine() {
        final Canvas canvasBefore = new Canvas(3, 7);

        final Canvas canvas = drawLine.handle(canvasBefore, "L 1 1 1 6", io);

        assertEquals(
                        "-----"+ NEW_LINE +
                        "|x  |"+ NEW_LINE +
                        "|x  |"+ NEW_LINE +
                        "|x  |"+ NEW_LINE +
                        "|x  |"+ NEW_LINE +
                        "|x  |"+ NEW_LINE +
                        "|x  |"+ NEW_LINE +
                        "|   |"+ NEW_LINE +
                        "-----", canvas.toString()
        );
    }

    @Test
    public void shouldHandleInvalidCoordinates() {
        final Canvas canvasBefore = new Canvas(1, 1);

        final Canvas canvas = drawLine.handle(canvasBefore, "L 1 1 5 1", io);

        assertEquals(
                "---"+ NEW_LINE +
                "|x|"+ NEW_LINE +
                "---",
                canvas.toString()
        );
    }

    @Test
    public void shouldHandleNonStraightLines(){
        final Canvas canvas = drawLine.handle(new Canvas(5, 5), "L 1 1 5 5", io);

        verify(io).print("Invalid 'draw line' command (line is not straight)");
        assertEquals(
                "-------"+ NEW_LINE +
                        "|     |"+ NEW_LINE +
                        "|     |"+ NEW_LINE +
                        "|     |"+ NEW_LINE +
                        "|     |"+ NEW_LINE +
                        "|     |"+ NEW_LINE +
                        "-------",
                canvas.toString());
    }

    @Test
    public void shouldDetectInvalidCommandFormat() {
        final Canvas canvasBefore = new Canvas(1, 1);
        final Canvas canvas = drawLine.handle(canvasBefore, "L meh", io);

        verify(io).print("Invalid 'draw line' command");
        assertEquals(canvas.toString(),
                "---"+ NEW_LINE +
                        "| |"+ NEW_LINE +
                        "---");
    }

    @Test
    public void shouldRequireACanvas() { assertTrue(drawLine.isCanvasRequired()); }

    @Test
    public void shouldMatchAValidCommand() { assertTrue(drawLine.matches("L 1 1 1 10")); }

    @Test
    public void shouldNotMatchAnInvalidCommand() { assertFalse(drawLine.matches("L a b c d")); }

}