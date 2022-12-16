package ru.skel2007.adventofcode2022.day03;

final class PriorityUtils {

    static int priority(char c) {
        if (c >= 'a' && c <= 'z') {
            return 1 + c - 'a';
        }

        if (c >= 'A' && c <= 'Z') {
            return 27 + c - 'A';
        }

        throw new IllegalArgumentException("Unexpected value: " + c);
    }

    private PriorityUtils() {
    }

}
