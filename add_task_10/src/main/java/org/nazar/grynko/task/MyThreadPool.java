package org.nazar.grynko.task;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyThreadPool implements Executor {

    private final Queue<Runnable> workQueue;
    private final AtomicBoolean isRunning;

    public MyThreadPool(int number) {
        this.workQueue = new ConcurrentLinkedQueue<>();
        this.isRunning = new AtomicBoolean(true);

        initialize(number);
    }

    private void initialize(int number) {
        for (int i = 0; i < number; i++) {
            MyRunnable runnable = new MyRunnable(workQueue, isRunning);
            new Thread(runnable).start();
        }
    }


    @Override
    public void execute(Runnable command) {
        if (isRunning.get()) workQueue.offer(command);
    }

    public void shutdown() {
        isRunning.set(false);
    }
}