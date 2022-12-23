package ru.skel2007.adventofcode2022.day13;

import java.util.ArrayList;
import java.util.List;

sealed interface PacketItem extends Comparable<PacketItem> {

    ItemList toList();

    @Override
    default int compareTo(PacketItem that) {
        return compare(this, that);
    }

    static int compare(PacketItem item1, PacketItem item2) {
        if (item1 instanceof Value value1 && item2 instanceof Value value2) {
            return Value.compare(value1, value2);
        }

        return ItemList.compare(item1.toList(), item2.toList());
    }

    static PacketItem parse(String s) {
        if (s.startsWith("[") && s.endsWith("]")) {
            return ItemList.parse(s);
        }

        return Value.parse(s);
    }


    record Value(int v) implements PacketItem {

        @Override
        public ItemList toList() {
            return new ItemList(List.of(this));
        }

        static int compare(Value v1, Value v2) {
            return Integer.compare(v1.v, v2.v);
        }

        private static Value parse(String s) {
            var v = Integer.parseInt(s);
            return new Value(v);
        }

    }

    record ItemList(List<PacketItem> items) implements PacketItem {

        @Override
        public ItemList toList() {
            return this;
        }

        int size() {
            return items.size();
        }

        PacketItem item(int i) {
            return items.get(i);
        }

        static int compare(ItemList list1, ItemList list2) {
            for (int i = 0; i < list1.size(); i++) {
                if (list2.size() < i + 1) {
                    return 1;
                }

                var item1 = list1.item(i);
                var item2 = list2.item(i);

                var result = PacketItem.compare(item1, item2);
                if (result != 0) {
                    return result;
                }
            }

            if (list2.size() == list1.size()) {
                return 0;
            }

            return -1;
        }

        private static ItemList parse(String s) {
            s = s.substring(1, s.length() - 1);

            var items = new ArrayList<PacketItem>();
            for (int startIndex = 0; startIndex < s.length(); ) {
                var endIndex = findItemEndIndex(s, startIndex);
                items.add(PacketItem.parse(s.substring(startIndex, endIndex)));

                startIndex = endIndex + 1;
            }

            return new ItemList(items);
        }

        private static int findItemEndIndex(String s, int startIndex) {
            var c = s.charAt(startIndex);
            if (c != '[') {
                var endIndex = s.indexOf(',', startIndex);
                if (endIndex == -1) {
                    endIndex = s.length();
                }

                return endIndex;
            }

            var brackets = 1;
            for (int i = startIndex + 1; i < s.length(); i++) {
                c = s.charAt(i);
                if (c == ']') {
                    if (--brackets == 0) {
                        return i + 1;
                    }
                } else if (c == '[') {
                    brackets++;
                }
            }

            throw new IllegalArgumentException("Unexpected item: " + s);
        }

    }

}
