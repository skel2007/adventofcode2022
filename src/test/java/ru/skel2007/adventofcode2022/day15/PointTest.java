package ru.skel2007.adventofcode2022.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

    @Test
    void distanceTo() {
        assertEquals(3, new Point(2, 18).distanceTo(new Point(2, 15)));
        assertEquals(4, new Point(2, 18).distanceTo(new Point(-2, 18)));
        assertEquals(7, new Point(2, 18).distanceTo(new Point(-2, 15)));
    }

}
