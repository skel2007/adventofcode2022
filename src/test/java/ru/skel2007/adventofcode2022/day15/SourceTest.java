package ru.skel2007.adventofcode2022.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SourceTest {

    @Test
    void parse() {
        var line = "Sensor at x=2, y=18: closest beacon is at x=-2, y=15";

        var expected = new Source(new Point(2, 18), new Point(-2, 15));
        assertEquals(expected, Source.parse(line));
    }

}
