package ru.skel2007.adventofcode2022.day02;

import ru.skel2007.adventofcode2022.Utils;

class Part2 {

    public static void main(String[] args) {
        var result = 0L;

        var input = Utils.readInput(Part2.class);
        for (var line : input) {
            var shape1 = Shape.valueOf(line.charAt(0));
            var outcome = Outcome.valueOf(line.charAt(2));

            result += outcome.score(shape1);
        }

        System.out.println(result);
    }

}
