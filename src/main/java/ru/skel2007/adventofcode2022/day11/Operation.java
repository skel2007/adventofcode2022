package ru.skel2007.adventofcode2022.day11;

record Operation(
        Operand operand1,
        Operand operand2,
        Type type
) {

    long apply(long old) {
        var v1 = operand1.value(old);
        var v2 = operand2.value(old);

        return type.apply(v1, v2);
    }

    static Operation parse(String s) {
        var operation = s.split(" ");
        return new Operation(
                Operand.parse(operation[0]),
                Operand.parse(operation[2]),
                Type.parse(operation[1])
        );
    }


    sealed interface Operand {

        long value(long old);

        static Operand parse(String s) {
            if (s.equals("old")) {
                return new Old();
            }

            return new Value(Integer.parseInt(s));
        }

        record Old() implements Operand {

            @Override
            public long value(long old) {
                return old;
            }

        }

        record Value(long value) implements Operand {

            @Override
            public long value(long old) {
                return value;
            }

        }

    }


    sealed interface Type {

        long apply(long v1, long v2);

        static Type parse(String s) {
            return switch (s) {
                case "+" -> new Plus();
                case "*" -> new Multiply();
                default -> throw new IllegalArgumentException("Unexpected operation: " + s);
            };
        }

        record Plus() implements Type {

            @Override
            public long apply(long v1, long v2) {
                return v1 + v2;
            }

        }

        record Multiply() implements Type {

            @Override
            public long apply(long v1, long v2) {
                return v1 * v2;
            }

        }

    }


}
