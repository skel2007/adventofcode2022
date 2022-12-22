package ru.skel2007.adventofcode2022.day11;

import ru.skel2007.adventofcode2022.Utils;

class Part2 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part1.class);
        var monkeys = Monkey.parse(input);
        var divider = Monkey.multiplyDividers(monkeys);

        var keepAway = new KeepAway(worryLevel -> worryLevel % divider, monkeys);
        for (int i = 0; i < 10_000; i++) {
            keepAway.round();
        }

        System.out.println(keepAway.calculateMonkeyBusiness());
    }

}
