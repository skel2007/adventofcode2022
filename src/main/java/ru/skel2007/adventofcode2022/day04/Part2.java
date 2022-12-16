package ru.skel2007.adventofcode2022.day04;

import ru.skel2007.adventofcode2022.Utils;

class Part2 {

    public static void main(String[] args) {
        var result = 0L;

        var input = Utils.readInput(Part2.class);
        for (var line : input) {
            var ranges = Ranges.valueOf(line);

            if ((ranges.ll() <= ranges.rl() && ranges.lr() >= ranges.rl())
                    || (ranges.ll() <= ranges.rr() && ranges.lr() >= ranges.rr())
                    || (ranges.rl() <= ranges.ll() && ranges.rr() >= ranges.ll())
                    || (ranges.rl() <= ranges.lr() && ranges.rr() >= ranges.lr())
            ) {
                result++;
            }
        }

        System.out.println(result);
    }

}
