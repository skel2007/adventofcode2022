package ru.skel2007.adventofcode2022.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;

record RouteMap(
        Map<Hill.Point, Node> nodes,
        Map<Hill.Point, Integer> minRouteLengthMap
) {

    static RouteMap parse(List<String> input) {
        var hills = Hill.parse(input);
        var nodes = StreamEx.of(hills)
                .mapToEntry(Hill::point, Node::new)
                .toMap();

        for (var node : nodes.values()) {
            node.fillNext(nodes);
        }

        var minRouteLengthMap = fillMinRouteLengthMap(nodes);

        return new RouteMap(nodes, minRouteLengthMap);
    }

    int minRouteLengthFromStart() {
        return EntryStream.of(nodes)
                .values()
                .filter(Node::start)
                .map(Node::point)
                .map(minRouteLengthMap::get)
                .nonNull()
                .mapToInt(Integer::intValue)
                .findFirst()
                .orElseThrow();
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

    private static Map<Hill.Point, Integer> fillMinRouteLengthMap(Map<Hill.Point, Node> nodes) {
        var minRouteLengthMap = new HashMap<Hill.Point, Integer>();
        var end = StreamEx.of(nodes.values())
                .filter(Node::end)
                .findFirst()
                .orElseThrow();

        fillMinRouteLengthMap(end, 0, minRouteLengthMap);

        return minRouteLengthMap;
    }

    private static void fillMinRouteLengthMap(Node node, int minRouteLength, Map<Hill.Point, Integer> minRouteLengthMap) {
        var currentMinRouteLength = minRouteLengthMap.getOrDefault(node.point(), Integer.MAX_VALUE);
        if (minRouteLength >= currentMinRouteLength) {
            return;
        }

        minRouteLengthMap.put(node.point(), minRouteLength);

        for (var previous : node.previous) {
            fillMinRouteLengthMap(previous, minRouteLength + 1, minRouteLengthMap);
        }
    }


    record Node(
            Hill hill,
            List<Node> previous
    ) {

        Node(Hill hill) {
            this(hill, new ArrayList<>());
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
        }

        @Override
        public String toString() {
            return "Node{" +
                   "hill=" + hill +
                   '}';
        }

    }

}
