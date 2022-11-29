package org.nazar.grynko.task;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int THREAD_NUMBER = 5;
    private static final int BARRIER_NUMBER = 5;

    public static void main(String[] args) {
            MyCyclicBarrier barrier = new MyCyclicBarrier(5);
            List<Runnable> runnables = getRunnables(5, barrier);
            Iterator var3 = runnables.iterator();

            while(var3.hasNext()) {
                Runnable runnable = (Runnable)var3.next();
                randSleep(800);
                (new Thread(runnable)).start();
            }
    }

    @SneakyThrows
    private static List<Runnable> getRunnables(int number, MyCyclicBarrier barrier) {
        List<Runnable> runnables = new ArrayList();

        for(int i = 0; i < number; ++i) {
            String name = "MyThread-" + i;
            runnables.add(new MyRunnable_(barrier, name));
        }

        return runnables;
    }

    @SneakyThrows
    public static void randSleep(int border) {
        Random random = new Random();
        int time = random.nextInt(border) + 500;
        Thread.sleep((long)time);
    }
}
