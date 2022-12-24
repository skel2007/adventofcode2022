package ru.skel2007.adventofcode2022.day14;

import ru.skel2007.adventofcode2022.Utils;

class Part2 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part2.class);
        var cave = Cave.parse(input, true);

        System.out.println(cave.produceSandUnits());
    }

}
