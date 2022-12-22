package ru.skel2007.adventofcode2022.day11;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import one.util.streamex.EntryStream;

final class ExampleOutputParser {

    private static final Pattern OUTPUT_PATTERN = Pattern.compile("""
            == After round (.*) ==
            Monkey 0 inspected items (.*) times.
            Monkey 1 inspected items (.*) times.
            Monkey 2 inspected items (.*) times.
            Monkey 3 inspected items (.*) times.
            """, Pattern.DOTALL);

    static Map.Entry<Integer, Map<String, Integer>> parse(CharSequence output) {
        var matcher = OUTPUT_PATTERN.matcher(output);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unexpected output: " + output);
        }

        return Map.entry(
                Integer.valueOf(matcher.group(1)),
                Map.of(
                        "0", Integer.valueOf(matcher.group(2)),
                        "1", Integer.valueOf(matcher.group(3)),
                        "2", Integer.valueOf(matcher.group(4)),
                        "3", Integer.valueOf(matcher.group(5))
                )
        );
    }

    static Map<Integer, Map<String, Integer>> parse(List<String> output) {
        return EntryStream.of(ParserUtils.parse(output, ExampleOutputParser::parse).stream()).toMap();
    }

    private ExampleOutputParser() {
    }

}
