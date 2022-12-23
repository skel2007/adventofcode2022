package ru.skel2007.adventofcode2022.day12;

import ru.skel2007.adventofcode2022.Utils;

class Part2 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part2.class);
        var routeMap = RouteMap.parse(input);

        System.out.println(routeMap.minRouteLengthFromAny());
    }

}
