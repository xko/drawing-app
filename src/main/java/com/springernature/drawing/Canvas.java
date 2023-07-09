package com.springernature.drawing;


import java.util.HashMap;
import java.util.stream.IntStream;

import static com.springernature.drawing.io.UserIO.NEW_LINE;

public class Canvas {
    private final static Character lineCharacter = 'x';
    private final int width;
    private final int height;

    private final HashMap<Integer, Character> content;

    public Canvas(int width, int height) {
        this(width, height, new HashMap<>());
    }

    private Canvas(int width, int height, HashMap<Integer, Character> content) {
        this.width = width;
        this.height = height;
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        IntStream.rangeClosed(0, width + 1).forEach(value -> builder.append("-"));
        builder.append(NEW_LINE);
        for (int y = 1; y <= height; y++) {
            builder.append("|");
            for (int x = 1; x <= width; x++) {
                builder.append(get(x, y));
            }
            builder.append("|");
            builder.append(NEW_LINE);
        }
        IntStream.rangeClosed(0, width + 1).forEach(value -> builder.append("-"));
        return builder.toString();
    }

    private int calculateCoordinate(int x, int y) {
        return (y * width) + x;
    }

    public boolean isValid(int x, int y) {
        return x > 0 && x <= width && y > 0 && y <= height;
    }

    public Canvas paint(int x, int y) {
        if (isValid(x, y)) {
            final int index = calculateCoordinate(x - 1, y - 1);
            return new Canvas(width, height, insertLineCharacterAt(index));
        }
        return this;
    }

    private HashMap<Integer, Character> insertLineCharacterAt(int index) {
        HashMap<Integer, Character> copy = new HashMap<>(content);
        copy.put(index, lineCharacter);
        return copy;
    }

    public char get(int x, int y) {
        final int key = calculateCoordinate(x - 1, y - 1);
        if (isValid(x, y) && content.containsKey(key)) {
            return content.get(key);
        }
        return ' ';
    }
}
