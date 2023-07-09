package com.springernature.drawing.io;

public interface UserIO {
    String readNextCommand();

    boolean isFinished();

    void finish();

    void print(String text);

    String NEW_LINE = System.lineSeparator();
}
