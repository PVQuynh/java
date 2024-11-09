package com.example.java.java_core.multi_thread;
// chờ đợi các thread khác hoàn thành mới được chạy
// có thể dùng join: nhưng join là chờ các thread được join hoàn thành thì thread cha mới được chạy
// ngược lại với deamon
public class g_Join_CountDownLatch {
    public static void main(String[] args) {

        // making two threads for 3 services
        Thread service1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Started service 1");
            }
        });
        Thread service2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Started service 2");
            }
        });
        Thread service3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Started service 3");
            }
        });

        service1.start();
        service2.start();
        service3.start();

        // using thread.join() to make sure that the execution of main thread only
        // finishes ones 3 services have executed
        try {
            System.out.println("Waiting for 3 services have started ... ");
            service1.join();
            service2.join();
            service3.join();
            System.out.println("Starting main Thread ... ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done!!!");
    }
}
