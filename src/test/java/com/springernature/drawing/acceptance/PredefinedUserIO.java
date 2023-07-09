package com.springernature.drawing.acceptance;

import com.springernature.drawing.io.UserIO;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PredefinedUserIO implements UserIO {

    private final Queue<UserInteraction> interactions = new LinkedList<>();
    private UserInteraction currentInteraction = null;
    private boolean finished;

    public PredefinedUserIO(List<String> lines) {
        extractUserInteractions(lines);
    }

    private void extractUserInteractions(List<String> lines) {
        final StringBuilder currentUserInput = new StringBuilder();
        final StringBuilder currentUserOutput = new StringBuilder();
        for (final String line : lines) {
            if (line.startsWith("enter command: ")) {
                if (currentUserInput.length() > 0 && currentUserOutput.length() > 0) {
                    interactions.add(new UserInteraction(currentUserInput.toString(), currentUserOutput.toString()));
                    currentUserInput.setLength(0);
                    currentUserOutput.setLength(0);
                }
                currentUserInput.append(line.substring("enter command: ".length()));
            } else {
                currentUserOutput.append(line);
                currentUserOutput.append(NEW_LINE);
            }
        }
        interactions.add(new UserInteraction(currentUserInput.toString(), currentUserOutput.toString()));
    }


    @Override
    public String readNextCommand() {
        currentInteraction = interactions.remove();
        if (interactions.isEmpty()) {
            finished = true;
        }
        return currentInteraction.input;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void finish() {

    }

    @Override
    public void print(final String text) {
        assertEquals(currentInteraction.output, text + NEW_LINE, "Invalid output for command " + currentInteraction.input);
    }

    private static class UserInteraction {
        private final String input;
        private final String output;

        public UserInteraction(String input, String output) {
            this.input = input;
            this.output = output;
        }

    }
}
