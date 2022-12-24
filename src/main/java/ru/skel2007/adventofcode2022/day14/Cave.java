package ru.skel2007.adventofcode2022.day14;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import one.util.streamex.StreamEx;

record Cave(
        Type[][] cave,
        Point sandSource
) {

    private static final Point SAND_SOURCE_POINT = new Point(500, 0);

    static Cave parse(List<String> input, boolean withFloor) {
        var rocks = parseRocks(input, withFloor);

        var cave = new Type[rocks.maxX - rocks.minX + 1][rocks.maxY + 1];
        for (var rock : rocks.rocks) {
            cave[rock.x() - rocks.minX][rock.y()] = Type.ROCK;
        }

        var sandSource = new Point(SAND_SOURCE_POINT.x() - rocks.minX(), SAND_SOURCE_POINT.y());
        cave[sandSource.x()][sandSource.y()] = Type.SAND_SOURCE;

        return new Cave(cave, sandSource);
    }

    private static Rocks parseRocks(List<String> input, boolean withFloor) {
        var rocks = StreamEx.of(input).flatMap(Point::parseTrace).toSet();

        var minX = StreamEx.of(rocks).mapToInt(Point::x).min().orElseThrow();
        var maxX = StreamEx.of(rocks).mapToInt(Point::x).max().orElseThrow();
        var maxY = StreamEx.of(rocks).mapToInt(Point::y).max().orElseThrow();

        if (!withFloor) {
            return new Rocks(rocks, minX, maxX, maxY);
        }

        maxY += 2;

        minX = Math.min(minX, SAND_SOURCE_POINT.x() - maxY);
        maxX = Math.max(minX, SAND_SOURCE_POINT.x() + maxY);

        for (int x = minX; x <= maxX; x++) {
            rocks.add(new Point(x, maxY));
        }

        return new Rocks(rocks, minX, maxX, maxY);
    }

    int produceSandUnits() {
        for (int i = 0; ; i++) {
            if (!produceSandUnit()) {
                return i;
            }
        }
    }

    private boolean produceSandUnit() {
        if (cave[sandSource.x()][sandSource.y()] != Type.SAND_SOURCE) {
            return false;
        }

        var currentX = sandSource.x();
        var currentY = sandSource.y();

        while (true) {
            var nextX = currentX;
            var nextY = currentY + 1;

            if (nextY == cave[nextX].length) {
                return false;
            }

            if (cave[nextX][nextY] != null) {
                nextX = currentX - 1;
                if (nextX < 0) {
                    return false;
                }

                if (cave[nextX][nextY] != null) {
                    nextX = currentX + 1;
                    if (nextX == cave.length) {
                        return false;
                    }

                    if (cave[nextX][nextY] != null) {
                        cave[currentX][currentY] = Type.SAND;
                        return true;
                    }
                }
            }

            currentX = nextX;
            currentY = nextY;
        }
    }

    void print() {
        for (int y = 0; y < cave[0].length; y++) {
            int sandUnits = 0;
            for (var column : cave) {
                var type = Objects.requireNonNullElse(column[y], Type.AIR);
                if (type == Type.SAND) {
                    sandUnits++;
                }

                System.out.print(type.c());
            }
            System.out.printf(" (%d)%n", sandUnits);
        }

        System.out.println();
    }


    private enum Type {
        AIR('.'),
        ROCK('#'),
        SAND('o'),
        SAND_SOURCE('+');

        private final char c;

        Type(char c) {
            this.c = c;
        }

        char c() {
            return c;
        }
    }

    private record Rocks(
            Set<Point> rocks,
            int minX,
            int maxX,
            int maxY
    ) {

    }

}
