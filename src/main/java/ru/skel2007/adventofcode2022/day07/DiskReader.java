package ru.skel2007.adventofcode2022.day07;

import java.util.List;

final class DiskReader {

    static Node readTree(List<String> input) {
        var root = Node.root();
        var current = root;

        for (var line : input) {
            if (line.startsWith("$")) {
                var command = line.split(" ");
                switch (command[1]) {
                    case "cd" -> {
                        var nextDir = command[2];
                        current = switch (nextDir) {
                            case "/" -> root;
                            case ".." -> current.parent().orElseThrow();
                            default -> current.children().get(nextDir);
                        };
                    }
                    case "ls" -> {
                    }
                    default -> throw new IllegalArgumentException("Unknown command: " + line);
                }
            } else {
                var output = line.split(" ");
                var name = output[1];

                if (output[0].equals("dir")) {
                    current.addDir(name);
                } else {
                    current.addFile(name, Long.parseLong(output[0]));
                }
            }
        }

        return root;
    }

    private DiskReader() {
    }

}
