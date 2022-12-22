package ru.skel2007.adventofcode2022.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

final class ParserUtils {

    static <T> List<T> parse(List<String> lines, Function<? super CharSequence, ? extends T> parser) {
        var result = new ArrayList<T>();

        var sb = new StringBuilder();
        for (var line : lines) {
            if (line.isBlank()) {
                result.add(parser.apply(sb));
                sb = new StringBuilder();

                continue;
            }

            sb.append(line);
            sb.append("\n");
        }

        result.add(parser.apply(sb));

        return result;
    }

    private ParserUtils() {
    }

}
