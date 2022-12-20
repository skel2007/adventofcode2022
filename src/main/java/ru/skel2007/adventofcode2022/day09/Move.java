package ru.skel2007.adventofcode2022.day09;

record Move(Direction direction, int count) {

    static Move parse(String line) {
        var move = line.split(" ");

        var count = Integer.parseInt(move[1]);
        var direction = switch (move[0]) {
            case "U" -> Direction.UP;
            case "D" -> Direction.DOWN;
            case "R" -> Direction.RIGHT;
            case "L" -> Direction.LEFT;
            default -> throw new IllegalArgumentException("Unexpected direction: " + move[0]);
        };

        return new Move(direction, count);
    }

    enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

}
