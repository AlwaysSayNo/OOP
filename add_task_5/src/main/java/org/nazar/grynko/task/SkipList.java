package org.nazar.grynko.task;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class SkipList {

    private final Node header;
    private final AtomicInteger currentLevels;
    private final int maxLevel;
    private final AtomicInteger size;
    private final Random levelRandom;

    public SkipList(int maxLevel) {
        this.currentLevels = new AtomicInteger(0);
        this.size = new AtomicInteger(0);
        this.maxLevel = maxLevel;
        header = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE, 0, maxLevel);
        this.levelRandom = new Random(0);
        for (int i = 0; i < maxLevel; i += 1) {
            header.setNextNode(i, header);
        }
    }

    public boolean add(Integer value) { return insert(value); }

    private boolean insert(int value) {
        Node[] predecessors = new Node[maxLevel];
        Node[] successors = new Node[maxLevel];

        while (true) {
            int foundNodeLevel = find(value, predecessors, successors);

            if (foundNodeLevel != -1) {
                Node foundNode = successors[foundNodeLevel];
                if (!foundNode.isMarkedForRemoval()) {
                    while (!foundNode.isFullyLinked());
                    return false;
                }
            }

            int levels = currentLevels.get();
            int newLevel = chooseRandomLevel();

            if (newLevel > levels) {
                newLevel = currentLevels.incrementAndGet();
            }

            int highestLockedLevel = -1;

            try {
                boolean valid = true;
                Node predecessor;
                Node successor;
                Node previousPredecessor = null;

                for (int level = 0; (valid && (level <= newLevel)); level += 1) {
                    predecessor = predecessors[level];
                    successor = successors[level];

                    if (predecessor != previousPredecessor) {
                        predecessor.getLock().lock();
                        highestLockedLevel = level;
                        previousPredecessor = predecessor;
                    }

                    valid = !predecessor.isMarkedForRemoval()
                            && !successor.isMarkedForRemoval()
                            && predecessor.getNextNode(level) == successor;
                }

                if (!valid) { continue; }

                Node newNode = new Node(value, value, newLevel, maxLevel);

                for (int level = 0; level <= newLevel; level += 1) {
                    newNode.setNextNode(level, successors[level]);
                    predecessors[level].setNextNode(level, newNode);
                }

                newNode.setFullyLinked(true);
                size.incrementAndGet();

                return true;
            }
            finally {
                for (int level = 0; level <= highestLockedLevel; level += 1) {
                    if (predecessors[level].getLock().isHeldByCurrentThread()) {
                        predecessors[level].getLock().unlock();
                    }
                }
            }
        }
    }

    public void remove(Integer value) {
        if (value == null) { return; }

        Node[] predecessors = new Node[maxLevel];
        Node[] successors = new Node[maxLevel];
        Node nodeToRemove = null;
        boolean inProcessOfRemoving = false;
        int highestLevelFound = -1;

        while (true) {
            int foundNodeLevel = find(value, predecessors, successors);

            if (inProcessOfRemoving || (foundNodeLevel != -1 && canDelete(successors[foundNodeLevel], foundNodeLevel)))
            {

                if (!inProcessOfRemoving) {
                    nodeToRemove = successors[foundNodeLevel];
                    highestLevelFound = nodeToRemove.getLevel();
                    nodeToRemove.getLock().lock();

                    if (nodeToRemove.isMarkedForRemoval()) {
                        nodeToRemove.getLock().unlock();
                        return;
                    }
                    nodeToRemove.setMarkedForRemoval(true);
                    inProcessOfRemoving = true;
                }

                int highestLockedLevel = -1;

                try {
                    boolean valid = true;
                    Node predecessor;
                    Node successor;
                    Node previousPredecessor = null;

                    for (int level = 0; (valid && (level <= highestLevelFound)); level += 1) {
                        predecessor = predecessors[level];
                        successor = successors[level];

                        if (predecessor != previousPredecessor) {
                            predecessor.getLock().lock();
                            highestLockedLevel = level;
                            previousPredecessor = predecessor;
                        }

                        valid = !predecessor.isMarkedForRemoval()
                                && predecessor.getNextNode(level) == successor;
                    }

                    if (!valid) {
                        continue;
                    }

                    for (int level = highestLevelFound; level >= 0; level -= 1) {
                        predecessors[level].setNextNode(level, nodeToRemove.getNextNode(level));
                    }

                    nodeToRemove.getLock().unlock();
                    size.decrementAndGet();

                    return;
                }
                finally {
                    for (int level = 0; level <= highestLockedLevel; level += 1) {
                        if (predecessors[level].getLock().isHeldByCurrentThread()) {
                            predecessors[level].getLock().unlock();
                        }
                    }
                }
            }
            else {
                return;
            }
        }
    }

    public boolean canDelete(Node node, int highestLevelFound) {
        return !node.isMarkedForRemoval() && node.isFullyLinked() && node.getLevel() == highestLevelFound;
    }

    public int find(Integer value, Node[] predecessors, Node[] successors) {

        int highestLevel = -1;
        int searchKey = value;
        Node predecessor = this.header;
        Node current;

        for (int level = maxLevel - 1; level >= 0; level -= 1) {
            current = predecessor.getNextNode(level);

            while (current.getKey() < searchKey) {
                predecessor = current;
                current = predecessor.getNextNode(level);
            }

            if (highestLevel == -1 && current.getKey() == searchKey) {
                highestLevel = level;
            }

            predecessors[level] = predecessor;
            successors[level] = current;
        }

        return highestLevel;
    }

    public boolean contains(Integer value) {
        Node[] predecessors = new Node[maxLevel];
        Node[] successors = new Node[maxLevel];

        int foundNodeLevel = find(value, predecessors, successors);

        return foundNodeLevel != -1
                && successors[foundNodeLevel].isFullyLinked()
                && !successors[foundNodeLevel].isMarkedForRemoval();
    }

    private int chooseRandomLevel() {

        int level = 1 + Integer.numberOfTrailingZeros(levelRandom.nextInt());
        if (level > maxLevel)
            level = maxLevel;
        return level;
    }

    public int size() { return size.get(); }

}
