package org.nazar.grynko.task;

import lombok.SneakyThrows;

public class MyPhaser {

    private int currentPhase;
    private int parties;
    private int arrived;
    private int unArrived;
    private boolean isTerminated;

    public MyPhaser(int parties) {
        this.currentPhase = 0;
        this.arrived = 0;
        this.parties = parties;
        this.unArrived = parties;
        this.isTerminated = false;
    }


    private synchronized boolean CheckTaskFinished() {
        if(unArrived == 0) {
            arrived = 0;
            unArrived = parties;
            currentPhase++;
            System.out.println("Tasks finished. Phase: " + currentPhase);

            notifyAll();
            return true;
        }
        return false;
    }

    @SneakyThrows
    public synchronized void arriveAndAwaitAdvance() {
        if(isTerminated) return;

        arrived++;
        unArrived--;

        while(!CheckTaskFinished()) {
            this.wait();
        }
    }

    public synchronized void arriveAndDeregister() {
        if(isTerminated) return;
        parties--;
        unArrived--;
        CheckTaskFinished();
        if(parties == 0) {
            isTerminated = true;
        }
    }

    public boolean isTerminated() {
        return isTerminated;
    }
    public synchronized void register() {
        parties++;
    }

    public int getParties() {
        return parties;
    }

    public int getArrived() {
        return arrived;
    }

    public int getUnArrived() {
        return unArrived;
    }

    public int getCurrentPhase() {
        return currentPhase;
    }
    public synchronized void arrive(){
        arrived++;
        unArrived--;
        CheckTaskFinished();
    }
}