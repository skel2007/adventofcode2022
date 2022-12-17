package ru.skel2007.adventofcode2022.day05;

import java.util.ArrayDeque;
import java.util.Deque;

import one.util.streamex.EntryStream;
import ru.skel2007.adventofcode2022.Utils;

class Part2 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part2.class);
        var inputReader = new InputReader(input);

        var stacks = inputReader.readStacks();
        while (true) {
            var action = inputReader.readAction();
            if (action.isEmpty()) {
                break;
            }

            var moved = new ArrayDeque<String>();
            for (int k = 0; k < action.get().amount(); k++) {
                var crate = stacks.get(action.get().from()).pop();
                moved.push(crate);
            }

            for (var crate : moved) {
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
