package org.nazar.grynko.task;

import lombok.Setter;
import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public class Node {

    private int key;
    private int value;
    private int level;
    private Node[] next;

    private ReentrantLock lock;

    private boolean fullyLinked;
    private boolean markedForRemoval;

    public Node(int key, int value, int level, int maxLevel) {
        this.key = key;
        this.value = value;
        this.level = level;
        this.next = new Node[maxLevel];
        this.fullyLinked = true;
        this.markedForRemoval = false;
        this.lock = new ReentrantLock();
    }

    public void setNextNode(int i, Node node) {
        next[i] = node;
    }

    public Node getNextNode(int i) {
        return next[i];
    }

    public String toString() {
        return Integer.toString(value);
    }

}
