package com.springernature.drawing.acceptance;

import com.springernature.drawing.DrawingApplication;
import com.springernature.drawing.DrawingCommands;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.springernature.drawing.io.UserIO.NEW_LINE;

public class DrawingApplicationAcceptanceTest {

    private final DrawingCommands commands = new DrawingCommands();

    private static final String EXAMPLE =
            "enter command: C 20 4" + NEW_LINE  +
            "----------------------" + NEW_LINE  +
            "|                    |" + NEW_LINE  +
            "|                    |" + NEW_LINE  +
            "|                    |" + NEW_LINE  +
            "|                    |" + NEW_LINE  +
            "----------------------" + NEW_LINE  +
            "enter command: L 1 2 6 2" + NEW_LINE  +
            "----------------------" + NEW_LINE  +
            "|                    |" + NEW_LINE  +
            "|xxxxxx              |" + NEW_LINE  +
            "|                    |" + NEW_LINE  +
            "|                    |" + NEW_LINE  +
            "----------------------" + NEW_LINE  +
            "enter command: L 6 3 6 4" + NEW_LINE  +
            "----------------------" + NEW_LINE  +
            "|                    |" + NEW_LINE  +
            "|xxxxxx              |" + NEW_LINE  +
            "|     x              |" + NEW_LINE  +
            "|     x              |" + NEW_LINE  +
            "----------------------";

    @Test
    public void shouldRunExample() {
        final PredefinedUserIO predefinedUserIO = new PredefinedUserIO(Arrays.asList(EXAMPLE.split(NEW_LINE)));
        new DrawingApplication(predefinedUserIO, commands).startLoop();
    }

}