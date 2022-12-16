package ru.skel2007.adventofcode2022.day04;

record Ranges(int ll, int lr, int rl, int rr) {

    static Ranges valueOf(String line) {
        var ranges = line.split(",");

        var leftRange = ranges[0].split("-");
        var rightRange = ranges[1].split("-");

        var ll = Integer.parseInt(leftRange[0]);
        var lr = Integer.parseInt(leftRange[1]);
        var rl = Integer.parseInt(rightRange[0]);
        var rr = Integer.parseInt(rightRange[1]);

        return new Ranges(ll, lr, rl, rr);
    }

}
