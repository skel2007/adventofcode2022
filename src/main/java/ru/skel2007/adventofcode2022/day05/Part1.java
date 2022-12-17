package ru.skel2007.adventofcode2022.day05;

import java.util.Deque;

import one.util.streamex.EntryStream;
import ru.skel2007.adventofcode2022.Utils;

class Part1 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part1.class);
        var inputReader = new InputReader(input);

        var stacks = inputReader.readStacks();
        while (true) {
            var action = inputReader.readAction();
            if (action.isEmpty()) {
                break;
            }

            for (int k = 0; k < action.get().amount(); k++) {
                var crate = stacks.get(action.get().from()).pop();
                stacks.get(action.get().to()).push(crate);
            }
        }

        var result = EntryStream.of(stacks)
                .values()
                .map(Deque::pop)
                .joining("");

        System.out.println(result);
    }

}
