package ru.skel2007.adventofcode2022.day10;

import java.util.function.Consumer;

class Computer {

    private int cycle;
    private int x = 1;

    void execute(Instruction instruction, Consumer<State> consumer) {
        switch (instruction) {
            case Instruction.Noop ignored -> consumer.accept(new State(++cycle, x));
            case Instruction.Addx addx -> {
                consumer.accept(new State(++cycle, x));
                consumer.accept(new State(++cycle, x));

                x += addx.value();
            }
        }
    }

    record State(int cycle, int x) {

        int strength() {
            return cycle * x;
        }

    }

}
