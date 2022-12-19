package ru.skel2007.adventofcode2022.day08;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ForestReaderTest {

    @Test
    void read() {
        var input = """
                20345
                02912
                02200
                """.split("\n");

        var expected = new int[][]{
                new int[]{2, 0, 3, 4, 5},
                new int[]{0, 2, 9, 1, 2},
                new int[]{0, 2, 2, 0, 0}
        };

        assertArrayEquals(expected, ForestReader.read(Arrays.asList(input)).array());
    }

}
