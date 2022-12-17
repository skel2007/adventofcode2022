package ru.skel2007.adventofcode2022.day05;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class InputReader {

    private final List<String> input;
    private int i = 0;

    InputReader(List<String> input) {
        this.input = input;
    }

    Map<String, Deque<String>> readStacks() {
        var stacksLines = new ArrayList<String>();
        for (; i < input.size(); i++) {
            var line = input.get(i);
            if (line.isBlank()) {
                i++;
                break;
            }

            stacksLines.add(line);
        }

        return StacksReader.read(stacksLines);
    }

    Optional<Action> readAction() {
        if (i == input.size()) {
            return Optional.empty();
        }

        return Optional.of(Action.valueOf(input.get(i++)));
    }

}
