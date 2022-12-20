package ru.skel2007.adventofcode2022.day10;

import java.util.concurrent.atomic.AtomicLong;

import ru.skel2007.adventofcode2022.Utils;

class Part1 {

    public static void main(String[] args) {
        var computer = new Computer();
        var result = new AtomicLong();

        var input = Utils.readInput(Part1.class);
        for (var line : input) {
            var instruction = Instruction.parse(line);
            computer.execute(instruction, state -> {
                if (state.cycle() % 40 != 20) {
                    return;
                }

                result.addAndGet(state.strength());
            });
        }

        System.out.println(result);
    }

}
