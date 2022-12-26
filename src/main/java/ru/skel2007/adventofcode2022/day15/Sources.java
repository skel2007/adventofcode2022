package ru.skel2007.adventofcode2022.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import one.util.streamex.IntStreamEx;
import one.util.streamex.StreamEx;

record Sources(List<Source> sources) {

    static Sources parse(List<String> input) {
        var sources = StreamEx.of(input).map(Source::parse).toList();
        return new Sources(sources);
    }

    int countNotBeaconPoints(int y) {
        var beacons = StreamEx.of(sources).map(Source::closestBeacon).filterBy(Point::y, y).toSet();
        return findNotBeaconRanges(y).mapToInt(Range::length).sum() - beacons.size();
    }

    Point findDistressBeacon(int max) {
        return IntStreamEx.rangeClosed(0, max)
                .flatMapToObj(y -> {
                    var result = new ArrayList<Integer>();
                    var current = new AtomicInteger();

                    findNotBeaconRanges(y).forEach(range -> {
                        IntStreamEx.range(current.get(), range.start()).boxed().forEach(result::add);
                        current.set(range.end() + 1);
                    });

                    IntStreamEx.rangeClosed(current.get(), max).boxed().forEach(result::add);

                    var beaconXs = StreamEx.of(sources).map(Source::closestBeacon).filterBy(Point::y, y).map(Point::x).toSet();
                    return StreamEx.of(result)
                            .remove(x -> x > max)
                            .remove(beaconXs::contains)
                            .map(x -> new Point(x, y));
                })
                .findFirst()
                .orElseThrow();
    }

    private StreamEx<Range> findNotBeaconRanges(int y) {
        var ranges = StreamEx.of(sources)
                .map(source -> {
                    var rest = source.distance() - Math.abs(source.point().y() - y);
                    if (rest < 0) {
                        return null;
                    }

                    var minX = source.point().x() - rest;
                    var maxX = source.point().x() + rest;

                    return new Range(minX, maxX);
                })
                .nonNull();

        return Range.merge(ranges);
    }

}
