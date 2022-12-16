package ru.skel2007.adventofcode2022.day02;

enum Shape {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int score;

    Shape(int score) {
        this.score = score;
    }

    int score() {
        return score;
    }

    int score(Shape that) {
        return this.score + outcome(that).score();
    }

    Outcome outcome(Shape that) {
        var ordinal = (4 + this.ordinal() - that.ordinal()) % 3;
        return Outcome.values()[ordinal];
    }

    static Shape valueOf(char c) {
        return switch (c) {
            case 'A' -> Shape.ROCK;
            case 'B' -> Shape.PAPER;
            case 'C' -> Shape.SCISSORS;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }

}
