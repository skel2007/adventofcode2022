package ru.skel2007.adventofcode2022.day01;

import ru.skel2007.adventofcode2022.Utils;

public class Day01Part1 {

    public static void main(String[] args) {
        var maxCalories = 0L;
        var currentCalories = 0L;

        var input = Utils.readInput(Day01Part1.class);
        for (var line : input) {
            if (line.isBlank()) {
                maxCalories = Math.max(maxCalories, currentCalories);
                currentCalories = 0L;

                continue;
            }

            currentCalories += Long.parseLong(line);
        }

        maxCalories = Math.max(maxCalories, currentCalories);
        System.out.println(maxCalories);
    }

}
