package ru.skel2007.adventofcode2022.day02;

import ru.skel2007.adventofcode2022.Utils;

class Part1 {

    public static void main(String[] args) {
        var result = 0L;

        var input = Utils.readInput(Part1.class);
        for (var line : input) {
            var shape1 = Shape.valueOf(line.charAt(0));
            var shape2 = shape2(line.charAt(2));

            result += shape2.score(shape1);
        }

        System.out.println(result);
    }

    private static Shape shape2(char c) {
        return switch (c) {
            case 'X' -> Shape.ROCK;
            case 'Y' -> Shape.PAPER;
            case 'Z' -> Shape.SCISSORS;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }

}
