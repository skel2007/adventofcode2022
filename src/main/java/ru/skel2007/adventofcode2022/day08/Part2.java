package ru.skel2007.adventofcode2022.day08;

import java.util.concurrent.atomic.AtomicLong;

import ru.skel2007.adventofcode2022.Utils;

class Part2 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part2.class);
        var forest = ForestReader.read(input);

        var result = new AtomicLong();
        forest.forEach((i, j) -> {
            var score = forest.scenicScore(i, j);
            if (result.get() < score) {
                result.set(score);
            }
        });

        System.out.println(result);
    }

}
