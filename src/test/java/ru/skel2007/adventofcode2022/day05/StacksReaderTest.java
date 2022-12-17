package ru.skel2007.adventofcode2022.day05;

import java.util.Arrays;
import java.util.Map;

import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StacksReaderTest {

    @Test
    void readIndexes() {
        var expected = Map.of(
                "1", 1,
                "2", 5,
                "3", 9,
                "4", 13,
                "5", 17,
                "6", 21,
                "7", 25,
                "8", 29,
                "9", 33
        );

        var line = " 1   2   3   4   5   6   7   8   9";

        assertEquals(expected, StacksReader.readIndexes(line));
    }

    @Test
    void read() {
        var expected = Map.of(
                "1", "SPWNJZ",
                "2", "TSG",
                "3", "HLRQV",
                "4", "DTSV",
                "5", "JMBDTZQ",
                "6", "LZCDJTWM",
                "7", "JTGWMPL",
                "8", "HQFBTMGN",
                "9", "WQBPCGDR"
        );

        var lines = """
                                    [L]     [H] [W]
                                [J] [Z] [J] [Q] [Q]
                [S]             [M] [C] [T] [F] [B]
                [P]     [H]     [B] [D] [G] [B] [P]
                [W]     [L] [D] [D] [J] [W] [T] [C]
                [N] [T] [R] [T] [T] [T] [M] [M] [G]
                [J] [S] [Q] [S] [Z] [W] [P] [G] [D]
                [Z] [G] [V] [V] [Q] [M] [L] [N] [R]
                 1   2   3   4   5   6   7   8   9
                """.split("\n");

        var actual = EntryStream.of(StacksReader.read(Arrays.asList(lines)))
                .mapValues(stack -> StreamEx.of(stack).joining(""))
                .toMap();

        assertEquals(expected, actual);
    }

}
