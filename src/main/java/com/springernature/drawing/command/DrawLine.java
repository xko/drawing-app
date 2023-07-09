package com.springernature.drawing.command;


import com.springernature.drawing.Canvas;
import com.springernature.drawing.io.UserIO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrawLine implements DrawingCommand {

    private final Pattern pattern = Pattern.compile("L (\\d+) (\\d+) (\\d+) (\\d+)");

    @Override
    public boolean matches(String input) {
        return pattern.matcher(input).matches();
    }

    @Override
    public boolean isCanvasRequired() {
        return true;
    }

    @Override
    public Canvas handle(Canvas canvas, String command, UserIO userIO) {
        final Matcher matcher = pattern.matcher(command);
        if (!matcher.find()) {
            userIO.print("Invalid 'draw line' command");
            return canvas;
        }
        int x1 = Integer.parseInt(matcher.group(1));
        int y1 = Integer.parseInt(matcher.group(2));
        int x2 = Integer.parseInt(matcher.group(3));
        int y2 = Integer.parseInt(matcher.group(4));
        if(x1 != x2 && y1 != y2){
            userIO.print("Invalid 'draw line' command (line is not straight)");
            return canvas;
        }
        final Canvas result = draw(canvas, x1, y1, x2, y2);
        userIO.print(result.toString());
        return result;
    }

    Canvas draw(Canvas canvas, int x1, int y1, int x2, int y2) {
        for (int y = y1; y <= y2; y++) {
            canvas = canvas.paint(x1, y);
        }
        for (int x = x1; x <= x2; x++) {
            canvas = canvas.paint(x, y1);
        }

        return canvas;
    }
}
