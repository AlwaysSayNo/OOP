package org.nazar.grynko.task;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyRunnable implements Runnable {

    private final Queue<Runnable> workQueue;
    private final AtomicBoolean isRunning;

    public MyRunnable(Queue<Runnable> workQueue, AtomicBoolean isRunning) {
        this.workQueue = workQueue;
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        while (isRunning.get()) {
            Runnable nextTask = workQueue.poll();
            if (nextTask != null) {
                nextTask.run();
            }
        }
    }

}
