package ru.skel2007.adventofcode2022.day03;

import java.util.stream.Collectors;

import ru.skel2007.adventofcode2022.Utils;

class Part2 {

    public static void main(String[] args) {
        var result = 0L;

        var input = Utils.readInput(Part2.class);
        for (int i = 0; i < input.size() / 3; i++) {
            var line1 = input.get(3 * i);
            var line2 = input.get(3 * i + 1);
            var line3 = input.get(3 * i + 2);

            var chars1 = line1.chars().boxed().collect(Collectors.toSet());
            var chars2 = line2.chars().boxed().filter(chars1::contains).collect(Collectors.toSet());
            var c = (char) line3.chars().filter(chars2::contains).findFirst().orElseThrow();

            result += PriorityUtils.priority(c);
        }

        System.out.println(result);
    }

}
