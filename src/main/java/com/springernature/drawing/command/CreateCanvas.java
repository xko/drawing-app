package com.springernature.drawing.command;

import com.springernature.drawing.Canvas;
import com.springernature.drawing.io.UserIO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateCanvas implements DrawingCommand {

    private final Pattern pattern = Pattern.compile("C (\\d+) (\\d+)");

    @Override
    public boolean matches(String input) {
        return pattern.matcher(input).matches();
    }

    @Override
    public boolean isCanvasRequired() {
        return false;
    }

    @Override
    public Canvas handle(Canvas canvas, String userCommand, UserIO userIO) {
        final Matcher matcher = pattern.matcher(userCommand);
        if (!matcher.find()) {
            userIO.print("Invalid command");
            return canvas;
        }
        final int width = Integer.parseInt(matcher.group(1));
        final int height = Integer.parseInt(matcher.group(2));
        final Canvas newCanvas = new Canvas(width, height);
        userIO.print(newCanvas.toString());
        return newCanvas;
    }
}
