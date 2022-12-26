package ru.skel2007.adventofcode2022.day15;

import ru.skel2007.adventofcode2022.Utils;

class Part2 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part1.class);
        var sources = Sources.parse(input);

        var distressBeacon = sources.findDistressBeacon(4_000_000);
        var tuningFrequency = (long) distressBeacon.x() * 4_000_000 + distressBeacon.y();

        System.out.println(tuningFrequency);
    }

}
