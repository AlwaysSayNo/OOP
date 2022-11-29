package org.nazar.grynko.task;

public class MyRunnable_ implements Runnable {

    private final MyCyclicBarrier myCyclicBarrier;
    private final String name;

    public MyRunnable_(MyCyclicBarrier myCyclicBarrier, String name) {
        this.myCyclicBarrier = myCyclicBarrier;
        this.name = name;
    }

    public void run() {
        this.myCyclicBarrier.await();
        System.out.println(this.name + " has released barrier");
    }
}
