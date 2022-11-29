package org.nazar.grynko;

import lombok.SneakyThrows;
import org.nazar.grynko.task.MyThreadPool;

import java.util.Random;

public class Main {

    private static final int THREAD_NUMBER = 10;
    private static final int BARRIER_NUMBER = 5;


    @SneakyThrows
    public static void main(String[] args) {
        MyThreadPool threadPool = new MyThreadPool(BARRIER_NUMBER);

        for (int i = 0; i < THREAD_NUMBER; i++) {
            int finalI = i;
            Runnable runnable = () -> {
                String name = "Thread-" + finalI;

                System.out.println(name + " is working");

                randSleep(900);
            };

            threadPool.execute(runnable);
        }
    }


    @SneakyThrows
    public static void randSleep(int border){
        Random random = new Random();
        int time = random.nextInt(border) + 500;

        Thread.sleep(time);
    }

}
