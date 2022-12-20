package ru.skel2007.adventofcode2022.day09;

import java.util.HashMap;
import java.util.TreeSet;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RopeTest {

    @ParameterizedTest
    @CsvSource({
            "2,13",
            "10,1",
    })
    void rope(int size, int expected) {
        var rope = new Rope(size);

        var input = """
                R 4
                U 4
                L 3
                D 1
                R 4
                D 1
                L 5
                R 2
                """.split("\n");

        for (var line : input) {
            System.out.println(line);
            System.out.println("----------");

            var move = Move.parse(line);
            for (int i = 0; i < move.count(); i++) {
                rope.move(move.direction());
                print(rope);
                System.out.println("----------");
            }
            System.out.println("==========");
        }

        assertEquals(expected, rope.getVisitedByTailCount());
    }

    static void print(Rope rope) {
        var xs = new TreeSet<Integer>();
        var ys = new TreeSet<Integer>();
        var positions = new HashMap<Position, Character>();

        var i = 0;
        var knot = rope.head;

        while (knot != null) {
            xs.add(knot.position.x());
            ys.add(knot.position.y());

            positions.putIfAbsent(knot.position, (char) ('0' + i));

            knot = knot.next.orElse(null);
            i++;
        }

        positions.putIfAbsent(Position.START, 'S');

        for (var position : rope.visitedByTail) {
            xs.add(position.x());
            ys.add(position.y());
            positions.putIfAbsent(position, '#');
        }

        for (int y = ys.last(); y >= ys.first(); y--) {
            for (int x = xs.first(); x <= xs.last(); x++) {
                var position = new Position(x, y);
                var c = positions.getOrDefault(position, '.');

                System.out.print(c);
            }

            System.out.println();
        }
    }

}
