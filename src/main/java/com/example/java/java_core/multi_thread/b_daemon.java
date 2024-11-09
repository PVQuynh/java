package com.example.java.java_core.multi_thread;
// daemon kết thúc theo thread thông thường

public class b_daemon implements Runnable {
    @Override
    public void run() {
        while (true) {
            processSomething();
        }
    }

    private void processSomething() {
        try {
            System.out.println("Processing working thread");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread dt = new Thread(new b_daemon(), "My Non-Daemon Thread");
        dt.setDaemon(true);
        dt.start();

        // continue program
        Thread.sleep(3000);
        System.out.println(">><< Finishing main program");
    }
}
