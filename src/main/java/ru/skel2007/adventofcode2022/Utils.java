package ru.skel2007.adventofcode2022;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public final class Utils {

    public static List<String> readInput(Class<?> clazz) {
        try {
            var resource = Objects.requireNonNull(clazz.getResource("input.txt"));
            return Files.readAllLines(Paths.get(resource.toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Utils() {
    }

}
