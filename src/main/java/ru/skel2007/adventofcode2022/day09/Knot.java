package ru.skel2007.adventofcode2022.day09;

import java.util.Optional;

class Knot {

    Position position;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    final Optional<Knot> next;

    Knot(Position position) {
        this.position = position;
        this.next = Optional.empty();
    }

    Knot(Position position, Knot next) {
        this.position = position;
        this.next = Optional.of(next);
    }

    void move(Move.Direction direction) {
        var newPosition = position.move(direction);
        move(newPosition);
    }

    private void move(Position position) {
        this.position = position;
        this.next.ifPresent(next -> next.adjust(position));
    }

    private void adjust(Position position) {
        var newPosition = this.position.adjust(position);
        newPosition.ifPresent(this::move);
    }

}
