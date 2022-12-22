package ru.skel2007.adventofcode2022.day11;

import org.junit.jupiter.api.Test;
import ru.skel2007.adventofcode2022.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExampleTest {

    @Test
    void run() {
        var input = Utils.readFile(ExampleTest.class, "example-input.txt");
        var monkeys = Monkey.parse(input);
        var divider = Monkey.multiplyDividers(monkeys);

        var output = Utils.readFile(ExampleTest.class, "example-output.txt");
        var expectedInspectedItemsCounts = ExampleOutputParser.parse(output);

        var keepAway = new KeepAway(worryLevel -> worryLevel % divider, monkeys);
        for (int i = 1; i <= 10_000; i++) {
            keepAway.round();

            if (expectedInspectedItemsCounts.containsKey(i)) {
                for (var entry: expectedInspectedItemsCounts.get(i).entrySet()) {
                    var actual = keepAway.inspectedItemsCounts(entry.getKey());
                    assertEquals(entry.getValue(), actual, "%d: %s".formatted(i, entry.getValue()));
                }

                System.out.printf("== After round %d ==%n", i);
                System.out.println(keepAway.printInspectedItemsCounts());
            }
        }
    }

}
