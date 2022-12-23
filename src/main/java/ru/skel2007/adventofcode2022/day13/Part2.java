package ru.skel2007.adventofcode2022.day13;

import java.util.List;

import ru.skel2007.adventofcode2022.Utils;

class Part2 {

    public static void main(String[] args) {
        var input = Utils.readInput(Part2.class);
        var packets = Packet.parse(input);

        var packet1 = new Packet(new PacketItem.ItemList(List.of(
                new PacketItem.ItemList(List.of(
                        new PacketItem.Value(2)
                ))
        )));
        var packet2 = new Packet(new PacketItem.ItemList(List.of(
                new PacketItem.ItemList(List.of(
                        new PacketItem.Value(6)
                ))
        )));

        var result = 1;
        for (int i = 1; i <= packets.size(); i++) {
            var currentPacket = packets.get(i - 1);
            if (i == 1 && packet1.compareTo(currentPacket) < 0) {
                continue;
            } else if (i == packets.size()) {
                if (currentPacket.compareTo(packet2) < 0) {
                    result *= (i + 2);
                }

                continue;
            }

            var nextPacket = packets.get(i);
            if (currentPacket.compareTo(packet1) < 0 && packet1.compareTo(nextPacket) < 0) {
                result *= (i + 1);
            } else if (currentPacket.compareTo(packet2) < 0 && packet2.compareTo(nextPacket) < 0) {
                result *= (i + 2);
            }
        }

        System.out.println(result);
    }

}
