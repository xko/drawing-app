package com.springernature.drawing.command;

import com.springernature.drawing.Canvas;
import com.springernature.drawing.io.UserIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateCanvasTest {

    @Mock
    private UserIO userIO;
    private CreateCanvas createCanvas;

    @BeforeEach
    public void setUp() {
        createCanvas = new CreateCanvas();
    }

    @Test
    public void shouldPrintNewCanvas() {
        final Canvas expectedCanvas = new Canvas(3, 5);
        final Canvas result = createCanvas.handle(null, "C 3 5", userIO);

        verify(userIO).print(expectedCanvas.toString());

        assertEquals(expectedCanvas.toString(), result.toString());
    }

    @Test
    public void shouldHandleWrongNumberOfArguments() {
        final Canvas expectedCanvas = mock(Canvas.class);
        final Canvas result = createCanvas.handle(expectedCanvas, "", userIO);

        verify(userIO).print("Invalid command");

        assertEquals(expectedCanvas, result);
    }

    @Test
    public void shouldHandleMalformedArguments() {
        final Canvas expectedCanvas = mock(Canvas.class);
        final Canvas updatedCanvas = createCanvas.handle(expectedCanvas, "C a b", userIO);

        verify(userIO).print("Invalid command");

        assertEquals(expectedCanvas, updatedCanvas);
    }

    @Test
    public void shouldNotRequireACanvas() { assertFalse(createCanvas.isCanvasRequired()); }

    @Test
    public void shouldMatchAValidCommand() { assertTrue(createCanvas.matches("C 10 10")); }

    @Test
    public void shouldNotMatchAnInvalidCommand() { assertFalse(createCanvas.matches("C a b")); }

}