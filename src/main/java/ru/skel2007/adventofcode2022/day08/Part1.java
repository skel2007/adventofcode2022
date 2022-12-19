package ru.skel2007.adventofcode2022.day08;

import java.util.concurrent.atomic.AtomicLong;

import ru.skel2007.adventofcode2022.Utils;

class Part1 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part1.class);
        var forest = ForestReader.read(input);

        var result = new AtomicLong();
        forest.forEach((i, j) -> {
            if (forest.isVisible(i, j)) {
                result.incrementAndGet();
            }
        });

        System.out.println(result);
    }

}
