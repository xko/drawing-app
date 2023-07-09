package com.springernature.drawing.command;

import com.springernature.drawing.io.UserIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class QuitTest {

    private Quit quit;

    @Mock
    private UserIO userIO;

    @BeforeEach
    public void setUp() { quit = new Quit(); }

    @Test
    public void shouldFinishIO() {
        quit.handle(null, "Q", userIO);
        verify(userIO).finish();
    }

    @Test
    public void shouldNotRequireACanvas() { assertFalse(quit.isCanvasRequired()); }

    @Test
    public void shouldMatchAValidCommand() { assertTrue(quit.matches("Q")); }

    @Test
    public void shouldNotMatchAnInvalidCommand() { assertFalse(quit.matches("X")); }

}