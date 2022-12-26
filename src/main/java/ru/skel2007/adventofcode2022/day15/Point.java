package ru.skel2007.adventofcode2022.day15;

record Point(int x, int y) {

    int distanceTo(Point that) {
        return Math.abs(this.x - that.x) + Math.abs(this.y - that.y);
    }

}
