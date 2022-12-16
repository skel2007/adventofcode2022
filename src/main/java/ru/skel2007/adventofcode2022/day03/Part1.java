package ru.skel2007.adventofcode2022.day03;

import java.util.stream.Collectors;

import ru.skel2007.adventofcode2022.Utils;

class Part1 {

    public static void main(String[] args) {
        var result = 0L;

        var input = Utils.readInput(Part1.class);
        for (var line : input) {
            var length = line.length() / 2;

            var left = line.substring(0, length);
            var right = line.substring(length);

            var leftChars = left.chars().boxed().collect(Collectors.toSet());
            var c = (char) right.chars().filter(leftChars::contains).findFirst().orElseThrow();

            result += PriorityUtils.priority(c);
        }

        System.out.println(result);
    }

}
