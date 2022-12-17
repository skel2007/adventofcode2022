package ru.skel2007.adventofcode2022.day05;

import java.util.regex.Pattern;

record Action(
        int amount,
        String from,
        String to
) {

    private static final Pattern PATTERN = Pattern.compile("^move (\\d+) from (.+) to (.+)$");

    static Action valueOf(String line) {
        var matcher = PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unexpected action line: " + line);
        }

        var amount = Integer.parseInt(matcher.group(1));
        var from = matcher.group(2);
        var to = matcher.group(3);

        return new Action(amount, from, to);
    }

}
