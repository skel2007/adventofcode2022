package ru.skel2007.adventofcode2022.day07;

import java.util.concurrent.atomic.AtomicLong;

import ru.skel2007.adventofcode2022.Utils;

class Part2 {

    private static final long TOTAL_SPACE_AVAILABLE = 70_000_000;
    private static final long MIN_UNUSED_SPACE = 30_000_000;

    public static void main(String[] args) {
        var input = Utils.readInput(Part1.class);
        var root = DiskReader.readTree(input);

        var totalSize = root.calculateSize();
        var unusedSpace = TOTAL_SPACE_AVAILABLE - totalSize;

        var minSizeToDelete = MIN_UNUSED_SPACE - unusedSpace;
        if (minSizeToDelete <= 0) {
            System.out.println("0");
            return;
        }

        var result = new AtomicLong(Long.MAX_VALUE);
        root.dfs(node -> {
            if (node.isFile()) {
                return;
            }

            var size = node.calculateSize();
            if (size <= result.get() && size >= minSizeToDelete) {
                result.set(size);
            }
        });

        System.out.println(result);
    }

}
