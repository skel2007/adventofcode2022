package ru.skel2007.adventofcode2022.day08;

import java.util.List;

import one.util.streamex.StreamEx;

final class ForestReader {

    static Forest read(List<String> input) {
        var array = StreamEx.of(input)
                .map(it -> it.chars().map(c -> c - '0').toArray())
                .toArray(int[][]::new);

        return new Forest(array);
    }

}
