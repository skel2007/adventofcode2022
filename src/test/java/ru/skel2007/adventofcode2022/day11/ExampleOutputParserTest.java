package ru.skel2007.adventofcode2022.day11;

import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExampleOutputParserTest {

    @Test
    void parse() {
        var output = """
                == After round 20 ==
                Monkey 0 inspected items 99 times.
                Monkey 1 inspected items 97 times.
                Monkey 2 inspected items 8 times.
                Monkey 3 inspected items 103 times.
                """;

        var expected = Map.entry(
                20,
                Map.of(
                        "0", 99,
                        "1", 97,
                        "2", 8,
                        "3", 103
                )
        );

        assertEquals(expected, ExampleOutputParser.parse(output));
    }

}
