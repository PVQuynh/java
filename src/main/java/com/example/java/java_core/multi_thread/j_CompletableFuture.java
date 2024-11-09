package com.example.java.java_core.multi_thread;

import java.util.concurrent.*;

public class j_CompletableFuture {

    public static void main(String[] args) {
        // Sử dụng Future
        System.out.println("Using Future:");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            Thread.sleep(4000); // Giả lập tác vụ tốn thời gian
            return 42;
        });

        // Chờ và lấy kết quả (block)
        try {
            System.out.println("Waiting for the result...");
            Integer result = future.get(); // Chương trình sẽ block cho đến khi có kết quả
            System.out.println("Result from Future: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        // Sử dụng CompletableFuture
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("\nUsing CompletableFuture:");
                Thread.sleep(3000); // Giả lập tác vụ tốn thời gian
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 42;
        });

        // Không bị block, tiếp tục thực hiện các tác vụ khác
        CompletableFuture<Void> completableFuture1 = completableFuture.thenAccept(result -> {
            System.out.println("Result from CompletableFuture: " + result);
        });

        // Chờ cho CompletableFuture hoàn thành (không block chính)
        try {
            System.out.println("Doing something else while waiting...");
            Thread.sleep(1000); // Giả lập công việc khác
            System.out.println("Still working...");
            // Đợi cho CompletableFuture hoàn thành
            completableFuture1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("All tasks completed.");

    }
}