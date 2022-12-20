package ru.skel2007.adventofcode2022.day09;

import java.util.Optional;

record Position(int x, int y) {

    static final Position START = new Position(0, 0);

    Position move(Move.Direction direction) {
        return switch (direction) {
            case UP -> new Position(x, y + 1);
            case DOWN -> new Position(x, y - 1);
            case RIGHT -> new Position(x + 1, y);
            case LEFT -> new Position(x - 1, y);
        };
    }

    Optional<Position> adjust(Position front) {
        if (this.x + 1 < front.x || this.x - 1 > front.x || this.y + 1 < front.y || this.y - 1 > front.y) {
            var x = this.x + Integer.compare(front.x, this.x);
            var y = this.y + Integer.compare(front.y, this.y);

            return Optional.of(new Position(x, y));
        }

        return Optional.empty();
    }

}
