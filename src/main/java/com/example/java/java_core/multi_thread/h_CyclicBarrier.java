package com.example.java.java_core.multi_thread;
// các thread chờ nhau ở 1 điểm chung nào đó

import java.util.Date;
import java.util.concurrent.CyclicBarrier;

class ServiceOne implements Runnable {

    private final CyclicBarrier cyclicBarrier;

    public ServiceOne(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("Starting service One...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("Service One has finished its work... waiting for others...");
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            System.out.println("Service one interrupted!");
            e.printStackTrace();
        }
        System.out.println("The wait is over, lets complete Service One!");

    }

}

class ServiceTwo implements Runnable {

    private final CyclicBarrier cyclicBarrier;

    public ServiceTwo(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("Starting service Two....");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("Service Two has finished its work.. waiting for others...");
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            System.out.println("Service two interrupted!");
            e.printStackTrace();
        }
        System.out.println("The wait is over, lets complete Service two!");

    }

}

class ServiceThree implements Runnable {

    private final CyclicBarrier cyclicBarrier;

    public ServiceThree(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("Starting service Three....");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("Service Three has finished its work.. waiting for others...");
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            System.out.println("Service Three interrupted!");
            e.printStackTrace();
        }
        System.out.println("The wait is over, lets complete Service Three!");
    }
}

public class h_CyclicBarrier {
    public static void main(String[] args) {

        // 4 threads are part of the barrier, ServiceOne, ServiceTwo, ServiceThree and
        // this main thread calling them.
        final CyclicBarrier barrier = new CyclicBarrier(4);

        Thread service1 = new Thread(new ServiceOne(barrier));
        Thread service2 = new Thread(new ServiceTwo(barrier));
        Thread service3 = new Thread(new ServiceThree(barrier));

        System.out.println("Starting both the services at" + new Date());

        service1.start();
        service2.start();
        service3.start();

        try {
            barrier.await();
        } catch (Exception e) {
            System.out.println("Main Thread interrupted!");
            e.printStackTrace();
        }
        System.out.println("Ending both the services at" + new Date());
    }
}
