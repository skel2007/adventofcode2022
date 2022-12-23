package ru.skel2007.adventofcode2022.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;

record RouteMap(
        Map<Hill.Point, Node> nodes,
        Map<Hill.Point, Integer> minRouteLengthMap,
        Node start,
        Node end
) {

    static RouteMap parse(List<String> input) {
        var hills = Hill.parse(input);
        var nodes = StreamEx.of(hills)
                .mapToEntry(Hill::point, Node::new)
                .toMap();

        for (var node : nodes.values()) {
            node.fillNext(nodes);
        }

        var start = StreamEx.of(nodes.values())
                .filter(Node::start)
                .findFirst()
                .orElseThrow();

        var end = StreamEx.of(nodes.values())
                .filter(Node::end)
                .findFirst()
                .orElseThrow();

        var minRouteLengthMap = new HashMap<Hill.Point, Integer>();
        fillMinRouteLength(end, 0, minRouteLengthMap);

        return new RouteMap(nodes, minRouteLengthMap, start, end);
    }

    int minRouteLengthFromStart() {
        return minRouteLengthMap.get(start.point());
    }

    int minRouteLengthFromAny() {
        return EntryStream.of(nodes)
                .values()
                .filterBy(Node::height, 0)
                .map(Node::point)
                .map(minRouteLengthMap::get)
                .nonNull()
                .mapToInt(Integer::intValue)
                .min()
                .orElseThrow();
    }

    private static void fillMinRouteLength(Node node, int minRouteLength, Map<Hill.Point, Integer> minRouteLengthMap) {
        var currentMinRouteLength = minRouteLengthMap.getOrDefault(node.point(), Integer.MAX_VALUE);
        if (minRouteLength >= currentMinRouteLength) {
            return;
        }

        minRouteLengthMap.put(node.point(), minRouteLength);

        for (var previous : node.previous) {
            fillMinRouteLength(previous, minRouteLength + 1, minRouteLengthMap);
        }
    }


    record Node(
            Hill hill,
            List<Node> previous,
            List<Node> next
    ) {

        Node(Hill hill) {
            this(hill, new ArrayList<>(), new ArrayList<>());
        }

        Hill.Point point() {
            return hill.point();
        }

        int height() {
            return hill.height();
        }

        boolean start() {
            return hill.start();
        }

        boolean end() {
            return hill.end();
        }

        void fillNext(Map<Hill.Point, Node> nodes) {
            StreamEx.of(hill.point().possibleNeighbors())
                    .map(nodes::get)
                    .nonNull()
                    .filter(neighbor -> neighbor.hill.height() >= hill.height() - 1)
                    .forEach(previous::add);

            StreamEx.of(hill.point().possibleNeighbors())
                    .map(nodes::get)
                    .nonNull()
                    .filter(neighbor -> neighbor.hill.height() <= hill.height() + 1)
                    .forEach(next::add);
        }

        @Override
        public String toString() {
            return "Node{" +
                   "hill=" + hill +
                   '}';
        }

    }

}
