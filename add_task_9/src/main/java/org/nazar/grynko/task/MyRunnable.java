package org.nazar.grynko.task;

public class MyRunnable implements Runnable {

    private final int number;
    private final MyPhaser phaser;

    public MyRunnable(int number, MyPhaser phaser) {
        this.number = number;
        this.phaser = phaser;
    }

    @Override
    public void run() {

        try {
            int count = number;
            for (int i = 1; i < 100; i++) {
                count++;
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " Value: " + count);
                if (i * number > 20) {
                    System.out.println(i);
                    phaser.arriveAndDeregister();
                    break;
                } else {

                    phaser.arriveAndAwaitAdvance();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
