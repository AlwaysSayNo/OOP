package org.nazar.grynko.task;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class MyLock {

    private final int border;
    private int lockHoldCount;
    private final List<Thread> currentThreads;

    public MyLock(int border) {
        this.border = border;
        this.lockHoldCount = 0;
        this.currentThreads = new ArrayList<>();
    }

    @SneakyThrows
    public synchronized void lock() {
        Thread thread = Thread.currentThread();

        while(!thread.isInterrupted()) {
            if (currentThreads.contains(thread)) {
                throw new IllegalStateException();
            } else if (lockHoldCount < border) {
                lockHoldCount++;
                currentThreads.add(thread);
                break;
            } else {
                wait();
            }
        }
    }

    public synchronized void unlock() {
        Thread thread = Thread.currentThread();

        if (!currentThreads.contains(thread))
            throw new IllegalStateException();

        lockHoldCount--;
        currentThreads.remove(thread);

        notify();
    }
}