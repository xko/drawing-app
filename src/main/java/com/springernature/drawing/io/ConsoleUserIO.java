package com.springernature.drawing.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUserIO implements UserIO {
    private boolean finished;

    @Override
    public String readNextCommand() {
        System.out.print("enter command: ");
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Could not read from input", e);
        }
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void finish() {
        finished = true;
    }

    @Override
    public void print(String text) {
        System.out.println(text);
    }
}
