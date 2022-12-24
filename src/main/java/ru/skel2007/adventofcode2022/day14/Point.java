package ru.skel2007.adventofcode2022.day14;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import one.util.streamex.StreamEx;

record Point(int x, int y) {

    static Stream<Point> parseTrace(String s) {
        var points = Arrays.stream(s.split(" -> "))
                .map(Point::parse)
                .toArray(Point[]::new);

        return trace(points);
    }

    private static Point parse(String s) {
        var point = s.split(",");

        return new Point(
                Integer.parseInt(point[0]),
                Integer.parseInt(point[1])
        );
    }

    private static Stream<Point> trace(Point[] points) {
        if (points.length == 1) {
            return Stream.of(points[0]);
        }

        var result = StreamEx.<Point>empty();
        for (int i = 0; i < points.length - 1; i++) {
            var point1 = points[i];
            var point2 = points[i + 1];

            result = result.append(point1.path(point2));
        }

        return result;
    }

    private Stream<Point> path(Point that) {
        if (this.x == that.x) {
            var minY = Math.min(this.y, that.y);
            var maxY = Math.max(this.y, that.y);

            return IntStream.rangeClosed(minY, maxY).mapToObj(y -> new Point(this.x, y));
        }

        if (this.y == that.y) {
            var minX = Math.min(this.x, that.x);
            var maxX = Math.max(this.x, that.x);

            return IntStream.rangeClosed(minX, maxX).mapToObj(x -> new Point(x, this.y));
        }

        throw new IllegalArgumentException("Path from %s to %s can't exist".formatted(this, that));
    }

}
