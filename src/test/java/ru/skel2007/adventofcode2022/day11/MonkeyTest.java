package ru.skel2007.adventofcode2022.day11;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonkeyTest {

    @Test
    void parse() {
        var input = """
                Monkey 0:
                  Starting items: 79, 98
                  Operation: new = old * 19
                  Test: divisible by 23
                    If true: throw to monkey 2
                    If false: throw to monkey 3
                """;

        var expected = new Monkey(
                "0",
                List.of(79L, 98L),
                new Operation(new Operation.Operand.Old(), new Operation.Operand.Value(19L), new Operation.Type.Multiply()),
                new ThrowDecision(23, "2", "3")
        );

        assertEquals(expected, Monkey.parse(input));
    }

}
