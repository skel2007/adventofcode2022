package ru.skel2007.adventofcode2022.day10;

import ru.skel2007.adventofcode2022.Utils;

class Part2 {

    public static void main(String[] args) {
        var computer = new Computer();
        var result = new boolean[6][];

        var input = Utils.readInput(Part2.class);
        for (var line : input) {
            var instruction = Instruction.parse(line);
            computer.execute(instruction, state -> {
                var pixel = (state.cycle() - 1) % 40;
                var lit = pixel >= state.x() - 1 && pixel <= state.x() + 1;

                var row = (state.cycle() -1) / 40;
                if (pixel == 0) {
                    result[row] = new boolean[40];
                }

                result[row][pixel] = lit;
            });
        }

        for (boolean[] row : result) {
            for (boolean lit : row) {
                System.out.print(lit ? '#' : '.');
            }

            System.out.println();
        }
    }

}
