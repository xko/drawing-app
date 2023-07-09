package com.springernature.drawing.command;

import com.springernature.drawing.Canvas;
import com.springernature.drawing.io.UserIO;
import java.util.Objects;

public class Quit implements DrawingCommand {

    @Override
    public boolean matches(String input) {
        return Objects.equals(input, "Q");
    }

    @Override
    public boolean isCanvasRequired() {
        return false;
    }

    @Override
    public Canvas handle(Canvas canvas, String userCommand, UserIO userIO) {
        userIO.finish();
        return canvas;
    }
}
