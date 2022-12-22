package ru.skel2007.adventofcode2022.day11;

record ThrowDecision(
        long divider,
        String ifTrueId,
        String ifFalseId
) {

    String apply(long worryLevel) {
        return worryLevel % divider == 0 ? ifTrueId : ifFalseId;
    }

}
