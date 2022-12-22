package ru.skel2007.adventofcode2022.day11;

import ru.skel2007.adventofcode2022.Utils;

class Part1 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part1.class);
        var monkeys = Monkey.parse(input);

        var keepAway = new KeepAway(worryLevel -> worryLevel / 3, monkeys);
        for (int i = 0; i < 20; i++) {
            keepAway.round();
        }

        System.out.println(keepAway.calculateMonkeyBusiness());
    }

}
