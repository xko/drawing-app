package com.springernature.drawing;

import com.springernature.drawing.io.ConsoleUserIO;
import com.springernature.drawing.io.UserIO;

public class DrawingApplication {
    private final UserIO userIO;
    private final DrawingCommands commands;

    public DrawingApplication(UserIO userIO, DrawingCommands commands) {
        this.userIO = userIO;
        this.commands = commands;
    }

    public static void main(String[] args) {
        new DrawingApplication(new ConsoleUserIO(), new DrawingCommands()).startLoop();
    }

    public void startLoop() {
        Canvas canvas = null;
        while (!userIO.isFinished()) {
            canvas = commands.process(canvas, userIO.readNextCommand(), userIO);
        }
    }
}
