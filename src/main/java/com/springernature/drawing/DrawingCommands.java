package com.springernature.drawing;

import com.springernature.drawing.command.CreateCanvas;
import com.springernature.drawing.command.DrawLine;
import com.springernature.drawing.command.DrawingCommand;
import com.springernature.drawing.command.Quit;
import com.springernature.drawing.io.UserIO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrawingCommands {

    private final List<DrawingCommand> commands = new ArrayList<>();

    public DrawingCommands(){
        this(new Quit(), new CreateCanvas(), new DrawLine());
    }

    public DrawingCommands(DrawingCommand... commands) {
        this.commands.addAll(Arrays.asList(commands));
    }

    public Canvas process(Canvas canvas, String userInput, UserIO userIO) {
        DrawingCommand drawingCommand = commandFrom(userInput);
        if(drawingCommand == null){
            userIO.print("Unknown command");
            return canvas;
        }
        if(canvas == null && drawingCommand.isCanvasRequired()){
            userIO.print("Please create a canvas first (e.g. 'C 10 10').");
            return null;
        }
        return drawingCommand.handle(canvas, userInput, userIO);
    }

    private DrawingCommand commandFrom(String userInput) {
        for (DrawingCommand command : commands) {
            if (command.matches(userInput)) {
                return command;
            }
        }
        return null;
    }
}
