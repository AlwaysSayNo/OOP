package org.nazar.grynko;

public class Main {

    public static void main(String[] args) {
        ThreadGroup group1 = new ThreadGroup("group-1");
        ThreadGroup group2 = new ThreadGroup("group-2");

        new Thread(group1, new MyRunnable(1000)).start();
        new Thread(group1, new MyRunnable(2000)).start();
        new Thread(group2, new MyRunnable(2000)).start();
        new Thread(group1, new MyRunnable(4000)).start();
        new Thread(group2, new MyRunnable(5000)).start();

        Recorder recorder1 = new Recorder(group1, 1000);
        recorder1.track(group1);

        Recorder recorder2 = new Recorder(group1, 1500);
        recorder2.track(group2);

        recorder1.join();
        recorder2.join();
    }

}
