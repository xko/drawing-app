package com.springernature.drawing;

import com.springernature.drawing.command.DrawingCommand;
import com.springernature.drawing.io.UserIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DrawingCommandsTest {

    @Mock
    private UserIO userIO;

    @Mock
    private DrawingCommand commandA;

    @Mock
    private DrawingCommand commandB;

    @Mock
    private Canvas canvas;

    @Mock
    private Canvas updatedCanvas;

    private DrawingCommands drawingCommands;

    @BeforeEach
    public void setUp() {
        lenient().when(commandA.matches("A 1 2 3")).thenReturn(true);
        lenient().when(commandB.matches("B x y")).thenReturn(true);
        drawingCommands = new DrawingCommands(commandA, commandB);
    }

    @Test
    public void shouldProcessCommandA() {
        when(commandA.handle(canvas, "A 1 2 3", userIO)).thenReturn(updatedCanvas);

        final Canvas result = drawingCommands.process(canvas, "A 1 2 3", userIO);

        verify(commandA).handle(canvas, "A 1 2 3", userIO);
        assertEquals(updatedCanvas, result);
    }

    @Test
    public void shouldProcessCommandB() {
        when(commandB.handle(canvas, "B x y", userIO)).thenReturn(updatedCanvas);

        final Canvas result = drawingCommands.process(canvas, "B x y", userIO);

        verify(commandB).handle(canvas, "B x y", userIO);
        verify(commandA, never()).handle(eq(canvas), anyString(), any());
        assertEquals(updatedCanvas, result);
    }

    @Test
    public void shouldHandleEmptyCommand() {
        drawingCommands.process(canvas, "", userIO);

        verify(userIO).print("Unknown command");
        verify(commandA, never()).handle(any(), anyString(), any());
        verify(commandB, never()).handle(any(), anyString(), any());
    }

    @Test
    public void shouldHandleUnsupportedCommand() {
        drawingCommands.process(canvas, "C", userIO);
        verify(userIO).print("Unknown command");
        verify(commandA, never()).handle(any(), anyString(), any());
        verify(commandB, never()).handle(any(), anyString(), any());
    }

    @Test
    public void shouldHandleCommandWithoutExistingCanvas(){
        when(commandA.isCanvasRequired()).thenReturn(true);

        drawingCommands.process(null, "A 1 2 3", userIO);

        verify(userIO).print("Please create a canvas first (e.g. 'C 10 10').");
    }
}