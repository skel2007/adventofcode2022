package ru.skel2007.adventofcode2022.day09;

import java.util.HashSet;
import java.util.Set;

class Rope {

    final Knot head;
    final Knot tail;

    final Set<Position> visitedByTail = new HashSet<>();

    Rope(int size) {
        this.tail = new Knot(Position.START);
        this.visitedByTail.add(tail.position);

        var next = tail;
        for (int i = 1; i < size; i++) {
            next = new Knot(Position.START, next);
        }

        this.head = next;
    }

    int getVisitedByTailCount() {
        return visitedByTail.size();
    }

    void move(Move move) {
        for (int i = 0; i < move.count(); i++) {
            move(move.direction());
        }
    }

    void move(Move.Direction direction) {
        head.move(direction);
        visitedByTail.add(tail.position);
    }

}
