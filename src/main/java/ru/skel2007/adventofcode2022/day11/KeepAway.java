package ru.skel2007.adventofcode2022.day11;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.LongUnaryOperator;

import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;

class KeepAway {

    private final LongUnaryOperator worryLevelOperator;

    private final Map<String, Monkey> monkeys;
    private final Map<String, AtomicInteger> inspectedItemsCounts;

    KeepAway(LongUnaryOperator worryLevelOperator, List<Monkey> monkeys) {
        this.worryLevelOperator = worryLevelOperator;
        this.monkeys = StreamEx.of(monkeys).mapToEntry(Monkey::id, Function.identity()).toCustomMap(LinkedHashMap::new);
        this.inspectedItemsCounts = StreamEx.of(monkeys).mapToEntry(Monkey::id, it -> new AtomicInteger()).toCustomMap(TreeMap::new);
    }

    void round() {
        for (var monkey : monkeys.values()) {
            for (var item : monkey.items()) {
                inspectedItemsCounts.get(monkey.id()).incrementAndGet();
                var worryLevel = worryLevelOperator.applyAsLong(monkey.operation().apply(item));

                var nextId = monkey.decision().apply(worryLevel);
                monkeys.get(nextId).items().add(worryLevel);
            }

            monkey.items().clear();
        }
    }

    long calculateMonkeyBusiness() {
        return EntryStream.of(inspectedItemsCounts)
                .values()
                .mapToLong(AtomicInteger::longValue)
                .reverseSorted()
                .limit(2)
                .reduce(Math::multiplyExact)
                .orElseThrow();
    }

    String printInspectedItemsCounts() {
        var sb = new StringBuilder();
        for (var entry : inspectedItemsCounts.entrySet()) {
            sb.append("Monkey %s inspected items %d times.%n".formatted(entry.getKey(), entry.getValue().intValue()));
        }

        return sb.toString();
    }

    int inspectedItemsCounts(String id) {
        var result = inspectedItemsCounts.get(id);
        return result != null ? result.intValue() : 0;
    }

}
