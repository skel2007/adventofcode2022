package ru.skel2007.adventofcode2022.day14;

import java.util.Arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaveTest {

    @ParameterizedTest
    @CsvSource({
            "false,24",
            "true,93",
    })
    void produceSandUnits(boolean withFloor, int expected) {
        var input = """
                498,4 -> 498,6 -> 496,6
                503,4 -> 502,4 -> 502,9 -> 494,9
                """.split("\n");

        var cave = Cave.parse(Arrays.asList(input), withFloor);
        cave.print();

        var producesSandUnits = cave.produceSandUnits();
        cave.print();

        assertEquals(expected, producesSandUnits);
    }

}
