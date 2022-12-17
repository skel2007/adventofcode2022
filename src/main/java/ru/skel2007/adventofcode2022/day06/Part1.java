package ru.skel2007.adventofcode2022.day06;

import ru.skel2007.adventofcode2022.Utils;

class Part1 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part1.class);
        System.out.println(MarkerFinder.findMarker(input, 4));
    }

}
