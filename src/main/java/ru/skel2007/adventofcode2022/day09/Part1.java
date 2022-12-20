package ru.skel2007.adventofcode2022.day09;

import ru.skel2007.adventofcode2022.Utils;

class Part1 {

    public static void main(String[] args) {
        var rope = new Rope(2);

        var input = Utils.readInput(Part1.class);
        for (var line : input) {
            var move = Move.parse(line);
            rope.move(move);
        }

        System.out.println(rope.getVisitedByTailCount());
    }

}
