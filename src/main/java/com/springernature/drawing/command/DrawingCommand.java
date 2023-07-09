package com.springernature.drawing.command;

import com.springernature.drawing.Canvas;
import com.springernature.drawing.io.UserIO;

public interface DrawingCommand {

    boolean matches(String input);

    boolean isCanvasRequired();

    Canvas handle(Canvas canvas, String userCommand, UserIO userIO);
}
