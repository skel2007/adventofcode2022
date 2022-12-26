package ru.skel2007.adventofcode2022.day15;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import one.util.streamex.StreamEx;

record Range(int start, int end) {

    int length() {
        return end - start + 1;
    }

    static StreamEx<Range> merge(StreamEx<Range> ranges) {
        return ranges
                .sorted(Comparator.comparingInt(Range::start).thenComparingInt(Range::end))
                .foldLeft(new RangesMerger(), RangesMerger::add)
                .finish();
    }

    private static class RangesMerger {

        private final List<Range> processed = new ArrayList<>();
        private Range current;

        RangesMerger add(Range next) {
            if (current == null) {
                current = next;
            } else if (current.end + 1 < next.start) {
                processed.add(current);
                current = next;
            } else if (current.end < next.end) {
                current = new Range(current.start, next.end);
            }

            return this;
        }

        private StreamEx<Range> finish() {
            return StreamEx.of(processed).append(current);
        }

    }


}
