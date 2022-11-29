package org.nazar.grynko;

import lombok.SneakyThrows;

public class Recorder {

    private final Thread runner;
    private final int trackTime;

    public Recorder(ThreadGroup group, int trackTime) {
        this.runner = track(group);
        this.trackTime = trackTime;
    }

    @SneakyThrows
    private void printGroup(ThreadGroup group) {
        int activeCount = group.activeCount();

        while (activeCount > 0) {
            String threadsToString = getString(group);

            System.out.println( group.getName() + " (alive " + activeCount + "): [" +  threadsToString + "]");
            Thread.sleep(this.trackTime);

            activeCount = group.activeCount();
        }
    }

    private String getString(ThreadGroup group) {
        int activeCount = group.activeCount();

        Thread[] activeThreads = new Thread[activeCount];
        group.enumerate(activeThreads);

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < activeCount; i++) {
            Thread thread = activeThreads[i];
            String name = thread.getName();

            str.append(name);
            if(i != activeCount - 1) str.append(", ");
        }

        return str.toString();
    }

    public Thread track(ThreadGroup group) {
        Runnable runnable = () -> printGroup(group);

        Thread runner = new Thread(runnable);
        runner.start();

        return runner;
    }

    @SneakyThrows
    public void join() {
        runner.join();
    }

}
