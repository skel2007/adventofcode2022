package ru.skel2007.adventofcode2022.day10;

import java.util.TreeMap;

import ru.skel2007.adventofcode2022.Utils;

class Part2 {

    public static void main(String[] args) {
        var computer = new Computer();
        var result = new TreeMap<Integer, boolean[]>();

        var input = Utils.readInput(Part2.class);
        for (var line : input) {
            var instruction = Instruction.parse(line);
            computer.execute(instruction, state -> {
                var pixel = (state.cycle() - 1) % 40;
                var lit = pixel >= state.x() - 1 && pixel <= state.x() + 1;

                var row = (state.cycle() -1) / 40;
                result.computeIfAbsent(row, it -> new boolean[40])[pixel] = lit;
            });
        }

        for (boolean[] row : result.values()) {
            for (boolean lit : row) {
                System.out.print(lit ? '#' : '.');
            }

            System.out.println();
        }
    }

}
