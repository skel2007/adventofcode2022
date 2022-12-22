package ru.skel2007.adventofcode2022.day11;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

record Monkey(
        String id,
        List<Long> items,
        Operation operation,
        ThrowDecision decision
) {

    private static final Pattern MONKEY_PATTERN = Pattern.compile("""
            Monkey (.*):
              Starting items: (.*)
              Operation: new = (.*)
              Test: divisible by (.*)
                If true: throw to monkey (.*)
                If false: throw to monkey (.*)
            """, Pattern.DOTALL);

    static Monkey parse(CharSequence input) {
        var matcher = MONKEY_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unexpected input: " + input);
        }

        var id = matcher.group(1);
        var startingItems = matcher.group(2);
        var operation = matcher.group(3);
        var decisionDivider = matcher.group(4);
        var decisionIfTrueId = matcher.group(5);
        var decisionIfFalseId = matcher.group(6);

        return new Monkey(
                id,
                Arrays.stream(startingItems.split(", ")).map(Long::parseLong).collect(Collectors.toList()),
                Operation.parse(operation),
                new ThrowDecision(
                        Integer.parseInt(decisionDivider),
                        decisionIfTrueId,
                        decisionIfFalseId
                )
        );
    }

    static List<Monkey> parse(List<String> input) {
        return ParserUtils.parse(input, Monkey::parse);
    }

    static long multiplyDividers(List<Monkey> monkeys) {
        return monkeys.stream()
                .map(Monkey::decision)
                .mapToLong(ThrowDecision::divider)
                .reduce(Math::multiplyExact)
                .orElseThrow();
    }

}
