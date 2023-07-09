package com.springernature.drawing;

import com.springernature.drawing.io.UserIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DrawingApplicationTest {
    @Mock
    private UserIO userIO;
    @Mock
    private DrawingCommands commands;

    private DrawingApplication application;

    @BeforeEach
    public void setUp() {
        application = new DrawingApplication(userIO, commands);
    }

    @Test
    public void shouldReadAndEvaluateACommand() {
        when(userIO.readNextCommand()).thenReturn("a command");
        when(userIO.isFinished()).thenReturn(false, true);

        application.startLoop();

        verify(commands).process(null, "a command", userIO);
    }

    @Test
    public void shouldReadAndEvaluateMultipleCommands() {
        final Canvas afterA = mock(Canvas.class, "result of A");
        final Canvas afterB = mock(Canvas.class, "result of B");
        when(commands.process(null, "a", userIO)).thenReturn(afterA);
        when(commands.process(afterA, "b", userIO)).thenReturn(afterB);
        when(userIO.readNextCommand()).thenReturn("a", "b", "c");
        when(userIO.isFinished()).thenReturn(false, false, false, true);

        application.startLoop();

        verify(commands).process(null, "a", userIO);
        verify(commands).process(afterA, "b", userIO);
        verify(commands).process(afterB, "c", userIO);
    }
}