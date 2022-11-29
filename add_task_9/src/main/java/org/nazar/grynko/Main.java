package org.nazar.grynko;

import org.nazar.grynko.task.MyRunnable;
import org.nazar.grynko.task.MyPhaser;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final int BORDER = 2;
    private static final int THREAD_NUMBER = 5;

    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser(5);

        List<Runnable> runnables = Arrays.asList(
            new MyRunnable(5, phaser),
            new MyRunnable(7, phaser),
            new MyRunnable(3, phaser),
            new MyRunnable(2, phaser),
            new MyRunnable(9, phaser)
        );

        for(int i = 0; i < THREAD_NUMBER; ++i) {
            String name = "Thread-" + i;
            new Thread(runnables.get(i), name).start();
        }
    }

}
