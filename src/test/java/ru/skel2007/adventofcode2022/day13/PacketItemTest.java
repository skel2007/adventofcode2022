package ru.skel2007.adventofcode2022.day13;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PacketItemTest {

    @Test
    void parse() {
        assertEquals(new PacketItem.Value(10), PacketItem.parse("10"));

        assertEquals(new PacketItem.ItemList(List.of(
                new PacketItem.Value(1),
                new PacketItem.ItemList(List.of(
                        new PacketItem.Value(2),
                        new PacketItem.Value(3),
                        new PacketItem.ItemList(List.of(
                                new PacketItem.Value(4)
                        ))
                )),
                new PacketItem.ItemList(List.of())
        )), PacketItem.parse("[1,[2,3,[4]],[]]"));

        assertEquals(new PacketItem.ItemList(List.of(
                new PacketItem.ItemList(List.of(
                        new PacketItem.ItemList(List.of())
                ))
        )), PacketItem.parse("[[[]]]"));
    }

}
