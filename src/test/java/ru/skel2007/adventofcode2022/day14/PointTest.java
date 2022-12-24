package ru.skel2007.adventofcode2022.day14;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

    @Test
    void parseTrace() {
        assertEquals(Set.of(
                new Point(498, 4),
                new Point(498, 5),
                new Point(498, 6),
                new Point(497, 6),
                new Point(496, 6)
        ), Point.parseTrace("498,4 -> 498,6 -> 496,6").collect(Collectors.toSet()));
    }

}
