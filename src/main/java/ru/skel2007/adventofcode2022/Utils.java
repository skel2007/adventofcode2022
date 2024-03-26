package ru.skel2007.adventofcode2022;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public final class Utils {

    public static List<String> readInput(Class<?> clazz) {
        return readFile(clazz, "input.txt");
    }

    public static List<String> readFile(Class<?> clazz, String filename) {
        try {
            var resource = Objects.requireNonNull(clazz.getResource(filename));
            return Files.readAllLines(Paths.get(resource.toURI()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private Utils() {
    }

}
