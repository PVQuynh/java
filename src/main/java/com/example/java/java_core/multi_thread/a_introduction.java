package com.example.java.java_core.multi_thread;

import lombok.Getter;

// extend threads
// implements runnable
@Getter
public class a_introduction implements Runnable {
    private Thread t;
    private String threadName;

    a_introduction(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // Let the thread sleep for a while.
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        System.out.println("Main thread running... ");

        a_introduction R1 = new a_introduction("Thread-1-HR-Database");
        R1.start();
        R1.getT().join(1);

        a_introduction R2 = new a_introduction("Thread-2-Send-Email");
        R2.start();

        System.out.println("==&gt; Main thread stopped!!! ");
    }
}
