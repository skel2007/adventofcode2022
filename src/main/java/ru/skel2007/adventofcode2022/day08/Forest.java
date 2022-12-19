package ru.skel2007.adventofcode2022.day08;

import java.util.stream.IntStream;

import one.util.streamex.IntStreamEx;
import one.util.streamex.StreamEx;

record Forest(int[][] array) {

    @FunctionalInterface
    interface TreeConsumer {

        void accept(int i, int j);

    }

    void forEach(TreeConsumer consumer) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                consumer.accept(i, j);
            }
        }
    }

    boolean isVisible(int i, int j) {
        return views(i, j).anyMatch(view -> isVisible(i, j, view.streamOfI, view.streamOfJ));
    }

    long scenicScore(int i, int j) {
        return views(i, j).mapToLong(view -> viewingDistance(i, j, view.streamOfI, view.streamOfJ))
                .reduce(Math::multiplyExact)
                .orElseThrow();
    }

    private StreamEx<View> views(int i, int j) {
        return StreamEx.of(
                new View(IntStreamEx.range(0, i).reverseSorted(), IntStreamEx.of(j)),
                new View(IntStreamEx.range(i + 1, array.length), IntStreamEx.of(j)),
                new View(IntStreamEx.of(i), IntStreamEx.range(0, j).reverseSorted()),
                new View(IntStreamEx.of(i), IntStreamEx.range(j + 1, array[i].length))
        );
    }

    private boolean isVisible(int i, int j, IntStream streamOfI, IntStream streamOfJ) {
        var arrayOfI = streamOfI.toArray();
        var arrayOfJ = streamOfJ.toArray();

        for (int ii : arrayOfI) {
            for (int jj : arrayOfJ) {
                if (array[ii][jj] >= array[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private long viewingDistance(int i, int j, IntStream streamOfI, IntStream streamOfJ) {
        var arrayOfI = streamOfI.toArray();
        var arrayOfJ = streamOfJ.toArray();

        var result = 0L;
        for (int ii : arrayOfI) {
            for (int jj : arrayOfJ) {
                result++;
                if (array[ii][jj] >= array[i][j]) {
                    return result;
                }
            }
        }

        return result;
    }

    private record View(IntStream streamOfI, IntStream streamOfJ) {

    }

}
