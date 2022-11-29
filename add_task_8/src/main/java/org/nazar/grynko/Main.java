package org.nazar.grynko;

import org.nazar.grynko.task.MyLock;
import org.nazar.grynko.task.MyRunnable;

public class Main {

    private static final int BORDER = 2;
    private static final int THREAD_NUMBER = 5;

    public static void main(String[] args) {
        MyLock customLock = new MyLock(BORDER);

        for(int i = 0; i < THREAD_NUMBER; ++i) {
            String name = "Thread-" + i;
            MyRunnable runnable = new MyRunnable(customLock, name);

            new Thread(runnable).start();
        }
    }

}
