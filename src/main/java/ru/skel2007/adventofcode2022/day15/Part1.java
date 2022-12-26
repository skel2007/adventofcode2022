package ru.skel2007.adventofcode2022.day15;

import ru.skel2007.adventofcode2022.Utils;

class Part1 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part1.class);
        var sources = Sources.parse(input);

        System.out.println(sources.countNotBeaconPoints(2_000_000));
    }

}
