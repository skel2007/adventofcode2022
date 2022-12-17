package ru.skel2007.adventofcode2022.day05;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class StacksReader {

    static Map<String, Deque<String>> read(List<String> lines) {
        var indexes = readIndexes(lines.get(lines.size() - 1));

        var result = new LinkedHashMap<String, Deque<String>>();
        for (var s : indexes.keySet()) {
            result.put(s, new ArrayDeque<>());
        }

        for (int i = lines.size() - 2; i >= 0; i--) {
            var line = lines.get(i);

            for (var entry : indexes.entrySet()) {
                var s = entry.getKey();
                var start = entry.getValue();

                if (line.charAt(start) != ' ') {
                    var end = line.indexOf(']', start);

                    var crate = line.substring(start, end);
                    result.get(s).push(crate);
                }
            }
        }

        return result;
    }

    static Map<String, Integer> readIndexes(String line) {
        var result = new HashMap<String, Integer>();

        for (int i = 0; i < line.length(); ) {
            if (line.charAt(i) == ' ') {
                i++;
                continue;
            }

            var j = line.indexOf(' ', i);
            if (j == -1) {
                var s = line.substring(i);
                result.put(s, i);
                break;
            }

            var s = line.substring(i, j);
            result.put(s, i);

            i = j;
        }

        return result;
    }

    private StacksReader() {
    }

}
