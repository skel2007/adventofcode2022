package ru.skel2007.adventofcode2022.day13;

import one.util.streamex.EntryStream;
import ru.skel2007.adventofcode2022.Utils;

class Part1 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part1.class);
        var packetPairs = Packet.parsePairs(input);

        var result = EntryStream.of(packetPairs)
                .mapValues(pair -> pair.first().compareTo(pair.second()) < 0)
                .nonNullValues()
                .filterValues(it -> it)
                .keys()
                .mapToInt(i -> i + 1)
                .sum();

        System.out.println(result);
    }

}
