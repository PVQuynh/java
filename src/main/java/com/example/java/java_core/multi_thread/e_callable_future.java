package com.example.java.java_core.multi_thread;
// Tương tự như các thread thông thường, nhưng có thể lấy được kết quả trả về là đối tượng Future<T>
// Đặc biệt khi get thì thread chính sẽ bị block

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CallableWorker implements Callable<Integer> {

    private int num;
    private Random ran;

    public CallableWorker(int num) {
        this.num = num;
        this.ran = new Random();
    }

    public Integer call() throws Exception {
        int result = num * num;
        System.out.println("Complete " + num);
        Thread.sleep(1000);
        return result;
    }
}

public class e_callable_future {
    public static void main(String[] args) {
        // create a list to hold the Future object associated with Callable
        List<Future<Integer>> list = new ArrayList<>();

        // Get ExecutorService from Executors utility class, thread pool size is 5
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Callable<Integer> callable;
        Future<Integer> future;
        for (int i = 1; i <= 10; i++) {
            callable = new CallableWorker(i);

            // submit Callable tasks to be executed by thread pool
            future = executor.submit(callable);

            // add Future to the list, we can get return value using Future
            list.add(future);
        }

        // shut down the executor service now
        executor.shutdown();

//        int sum = 0;
//        for (Future<Integer> f : list) {
//            try {
//                sum += f.get();
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }

        System.out.println("Finished all threads: ");
//        System.out.println("Sum all = " + sum);
    }
}