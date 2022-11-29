package org.nazar.grynko.task;

import lombok.SneakyThrows;

public class MyCyclicBarrier {
    private int waitingParties;
    private final int parties;

    public MyCyclicBarrier(int parties) {
        this.parties = parties;
        this.waitingParties = 0;
    }

    @SneakyThrows
    public synchronized void await() {
        ++this.waitingParties;
        System.out.println(Thread.currentThread().getName() + " is " + this.waitingParties + " in queue from " + this.parties);
        if (this.waitingParties == this.parties) {
            System.out.println("GO. Started " + Thread.currentThread().getName());
            this.waitingParties = 0;
            this.notifyAll();
        } else {
            this.wait();
        }
    }
}