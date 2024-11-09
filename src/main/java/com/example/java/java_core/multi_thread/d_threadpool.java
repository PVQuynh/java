package com.example.java.java_core.multi_thread;
// quản lý các thread: queue -> thread

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class d_threadpool implements Runnable {
    private String task;

    public d_threadpool(String s) {
        this.task = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Starting. Task = " + task);
        processCommand();
        System.out.println(Thread.currentThread().getName() + " Finished.");
    }

    private void processCommand() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 10; i++) {
            Runnable worker = new d_threadpool("" + i);
            executor.execute(worker);
        }

        executor.shutdown();

        System.out.println("Finished all threads");
    }
}
