package ru.skel2007.adventofcode2022.day07;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import one.util.streamex.EntryStream;

public record Node(Optional<Node> parent, String name, Map<String, Node> children, long size) {

    public static Node root() {
        return new Node(Optional.empty(), "/", new HashMap<>(), 0L);
    }

    public void addFile(String name, long size) {
        var file = new Node(Optional.of(this), name, Map.of(), size);
        children.put(name, file);
    }

    public void addDir(String name) {
        var dir = new Node(Optional.of(this), name, new HashMap<>(), 0L);
        children.put(name, dir);
    }

    public boolean isFile() {
        return children.isEmpty();
    }

    public long calculateSize() {
        return size + EntryStream.of(children).values().mapToLong(Node::calculateSize).sum();
    }

    public void dfs(Consumer<Node> consumer) {
        var stack = new ArrayDeque<Node>();
        stack.push(this);

        while (!stack.isEmpty()) {
            var currentNode = stack.pop();
            consumer.accept(currentNode);

            currentNode.children.values().forEach(stack::push);
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", children=" + children +
                ", size=" + size +
                '}';
    }

}
