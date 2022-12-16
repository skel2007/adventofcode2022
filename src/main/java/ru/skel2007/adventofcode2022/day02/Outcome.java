package ru.skel2007.adventofcode2022.day02;

enum Outcome {
    LOSE(0),
    DRAW(3),
    WIN(6);

    private final int score;

    Outcome(int score) {
        this.score = score;
    }

    int score() {
        return score;
    }

    int score(Shape shape) {
        return this.score + shape(shape).score();
    }

    private Shape shape(Shape shape) {
        int ordinal = (2 + this.ordinal() + shape.ordinal()) % 3;
        return Shape.values()[ordinal];
    }

    static Outcome valueOf(char c) {
        return switch (c) {
            case 'X' -> LOSE;
            case 'Y' -> DRAW;
            case 'Z' -> WIN;
            default -> throw new IllegalArgumentException("Unexpected value: " + c);
        };
    }

}
