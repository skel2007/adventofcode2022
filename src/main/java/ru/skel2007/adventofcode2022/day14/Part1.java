package ru.skel2007.adventofcode2022.day14;

import ru.skel2007.adventofcode2022.Utils;

class Part1 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part1.class);
        var cave = Cave.parse(input, false);

        System.out.println(cave.produceSandUnits());
    }

}
