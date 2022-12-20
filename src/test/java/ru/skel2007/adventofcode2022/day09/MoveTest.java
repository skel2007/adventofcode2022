package ru.skel2007.adventofcode2022.day09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoveTest {

    @Test
    void parse() {
        assertEquals(new Move(Move.Direction.UP, 5), Move.parse("U 5"));
        assertEquals(new Move(Move.Direction.DOWN, 3), Move.parse("D 3"));
        assertEquals(new Move(Move.Direction.RIGHT, 1), Move.parse("R 1"));
        assertEquals(new Move(Move.Direction.LEFT, 10), Move.parse("L 10"));
    }

}
