package org.nazar.grynko;

import lombok.SneakyThrows;

public class MyRunnable implements Runnable{

    private final int time;

    MyRunnable(int time) {
        this.time = time;
    }

    @SneakyThrows
    public void run() {
        Thread.sleep(time);
    }

}
