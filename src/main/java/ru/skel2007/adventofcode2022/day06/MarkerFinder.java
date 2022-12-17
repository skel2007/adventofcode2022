package ru.skel2007.adventofcode2022.day06;

import java.util.HashSet;
import java.util.List;

final class MarkerFinder {

    static int findMarker(List<String> input, int markerLength) {
        var line = input.get(0);

        for (int i = markerLength; i < line.length(); i++) {
            var set = new HashSet<Character>();
            for (int j = i - markerLength; j < i; j++) {
                set.add(line.charAt(j));
            }

            if (set.size() == markerLength) {
                return i;
            }
        }

        throw new IllegalArgumentException("Can't find the marker");
    }

    private MarkerFinder() {
    }

}
