package ru.skel2007.adventofcode2022.day07;

import java.util.concurrent.atomic.AtomicLong;

import ru.skel2007.adventofcode2022.Utils;

class Part1 {

    private static final long MAX_SIZE = 100_000;

    public static void main(String[] args) {
        var input = Utils.readInput(Part1.class);
        var root = DiskReader.readTree(input);

        var result = new AtomicLong();
        root.dfs(node -> {
            if (node.isFile()) {
                return;
            }

            var size = node.calculateSize();
            if (size <= MAX_SIZE) {
                result.addAndGet(size);
            }
        });

        System.out.println(result.get());
    }

}
