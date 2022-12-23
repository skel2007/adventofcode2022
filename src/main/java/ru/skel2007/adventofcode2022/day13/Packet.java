package ru.skel2007.adventofcode2022.day13;

import java.util.ArrayList;
import java.util.List;

import one.util.streamex.StreamEx;
import ru.skel2007.adventofcode2022.Pair;

record Packet(PacketItem.ItemList items) implements Comparable<Packet> {

    @Override
    public int compareTo(Packet that) {
        return this.items.compareTo(that.items);
    }

    static Packet parse(String line) {
        return new Packet((PacketItem.ItemList) PacketItem.parse(line));
    }

    static List<Packet> parse(List<String> input) {
        return StreamEx.of(input)
                .remove(String::isBlank)
                .map(Packet::parse)
                .sorted()
                .toList();
    }

    static List<Pair<Packet, Packet>> parsePairs(List<String> input) {
        var result = new ArrayList<Pair<Packet, Packet>>();

        for (int i = 0; i < input.size(); i += 3) {
            var packet1 = Packet.parse(input.get(i));
            var packet2 = Packet.parse(input.get(i + 1));

            result.add(new Pair<>(packet1, packet2));
        }

        return result;
    }

}
