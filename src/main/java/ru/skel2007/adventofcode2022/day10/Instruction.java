package ru.skel2007.adventofcode2022.day10;

sealed interface Instruction {

    static Instruction parse(String value) {
        var instruction = value.split(" ");
        return switch (instruction[0]) {
            case "noop" -> new Noop();
            case "addx" -> new Addx(Integer.parseInt(instruction[1]));
            default -> throw new IllegalArgumentException("Unexpected instruction: " + value);
        };
    }

    record Noop() implements Instruction {

    }

    record Addx(int value) implements Instruction {

    }

}
