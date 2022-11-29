package org.nazar.grynko.task;

import lombok.SneakyThrows;

import java.util.Random;

public class MyRunnable implements Runnable {

    private final MyLock customLock;
    private final String name;

    public MyRunnable(MyLock customLock, String name) {
        this.customLock = customLock;
        this.name = name;
    }

    public void run() {
        while (true){
            customLock.lock();
            System.out.println(name + " has taken lock");
            randSleep(1000);
            System.out.println(name + " has released lock");
            customLock.unlock();
            randSleep(500);
        }
    }

    @SneakyThrows
    public static void randSleep(int border){
        Random random = new Random();
        int time = random.nextInt(border) + 500;

        Thread.sleep(time);
    }
}
