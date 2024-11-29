package com.example.java.java_library.cache.cache_caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class cache_caffeine {
    // Gỉa lập database
    final static Map<Integer, String> keyValueMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        // Khởi tạo dữ liệu mẫu
        for (int i = 0; i < 10000; i++) {
            keyValueMap.put(i, "value " + i);
        }

        // Tạo cache với cấu hình
        final Cache<Integer, String> cache = Caffeine.newBuilder()
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .maximumSize(10)
                .refreshAfterWrite(2, TimeUnit.SECONDS)
                .executor(Executors.newSingleThreadExecutor())
                .build(new CacheLoader<Integer, String>() {

                    @Override
                    public @Nullable String load(@NonNull Integer key) throws Exception {
                        // Lấy giá trị từ giả lập database
                        String value = keyValueMap.get(key);
                        if (value == null) {
                            log.error("Key {} not found", key);
                        }
                        return value;
                    }
                });

        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            do {
                System.out.print("Nhập key để lấy value (hoặc 'q' để thoát): ");
                input = scanner.nextLine();

                if ("q".equals(input)) break;

                try {
                    int keyFinding = Integer.parseInt(input);
                    String value = cache.get(keyFinding, ignored -> {
                        String foundValue = keyValueMap.get(keyFinding);
                        if (foundValue == null) {
                            log.error("Key {} not found", keyFinding);
                        }
                        return foundValue;
                    });

                    System.out.println("Giá trị lấy từ cache: "+ value);
                } catch (NumberFormatException e) {
                    log.error("Đầu vào không hợp lệ: {}", input);
                }

                log.info("==============================");
                cache.asMap().forEach((key, value) -> log.info("Key: {}, Value: {}", key, value));
            } while (true);
        } catch (Exception e) {
            log.error("Đã xảy ra lỗi: {}", e.getMessage());
        }
    }
}