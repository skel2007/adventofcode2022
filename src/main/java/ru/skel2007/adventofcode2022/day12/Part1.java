package ru.skel2007.adventofcode2022.day12;

import ru.skel2007.adventofcode2022.Utils;

class Part1 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part1.class);
        var routeMap = RouteMap.parse(input);

        System.out.println(routeMap.minRouteLengthFromStart());
    }

}
