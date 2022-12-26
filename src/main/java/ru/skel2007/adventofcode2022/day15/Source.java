package ru.skel2007.adventofcode2022.day15;

import java.util.regex.Pattern;

record Source(
        Point point,
        Point closestBeacon,
        int distance
) {

    Source(Point point, Point closestBeacon) {
        this(point, closestBeacon, point.distanceTo(closestBeacon));
    }

    private static final Pattern LINE_PATTERN = Pattern.compile("Sensor at x=(.*), y=(.*): closest beacon is at x=(.*), y=(.*)");

    static Source parse(String line) {
        var matcher = LINE_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unexpected input line: " + line);
        }

        var source = new Point(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2))
        );
        var closestBeacon = new Point(
                Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(4))
        );

        return new Source(source, closestBeacon);
    }

}
