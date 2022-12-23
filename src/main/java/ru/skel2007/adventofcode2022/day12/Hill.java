package ru.skel2007.adventofcode2022.day12;

import java.util.List;
import java.util.stream.IntStream;

import one.util.streamex.EntryStream;

record Hill(Point point, int height, boolean start, boolean end) {

    private static final char START = 'S';
    private static final char END = 'E';

    private Hill(int x, int y, char c) {
        this(new Point(x, y), height(c), c == START, c == END);
    }

    static List<Hill> parse(List<String> input) {
        return EntryStream.of(input)
                .flatMapKeyValue((i, line) -> IntStream.range(0, line.length()).mapToObj(j -> new Hill(i, j, line.charAt(j))))
                .toList();
    }

    private static int height(char c) {
        switch (c) {
            case START -> c = 'a';
            case END -> c = 'z';
        }

        return c - 'a';
    }

    record Point(int x, int y) {

        List<Point> possibleNeighbors() {
            return List.of(
                    new Point(x - 1, y),
                    new Point(x + 1, y),
                    new Point(x, y - 1),
                    new Point(x, y + 1)
            );
        }

    }

}
