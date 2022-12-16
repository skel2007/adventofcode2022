package ru.skel2007.adventofcode2022.day01;

import java.util.stream.LongStream;

import ru.skel2007.adventofcode2022.Utils;

public class Day01Part2 {

    public static void main(String[] args) {
        var topCalories = new long[3];
        var currentCalories = 0L;

        var input = Utils.readInput(Day01Part2.class);
        for (var line : input) {
            if (line.isBlank()) {
                recalculateTop(topCalories, currentCalories);
                currentCalories = 0L;

                continue;
            }

            currentCalories += Long.parseLong(line);
        }

        recalculateTop(topCalories, currentCalories);

        var sum = LongStream.of(topCalories).sum();
        System.out.println(sum);
    }

    private static void recalculateTop(long[] topCalories, long currentCalories) {
        for (int i = topCalories.length - 1; i >= 0; i--) {
            if (currentCalories >= topCalories[i]) {
                for (int j = 0; j < i; j++) {
                    topCalories[j] = topCalories[j + 1];
                }

                topCalories[i] = currentCalories;
                return;
            }
        }
    }

}
