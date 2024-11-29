package com.example.java.java_library.cache.cache_guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
public class cache_guava {
    // Giả lập database
    final static Map<Integer, String> keyValueMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        // Khởi tạo dữ liệu mẫu
        for (int i = 0; i < 10000; i++) {
            keyValueMap.put(i, "value " + i);
        }

        // Tạo LoadingCache với cấu hình
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.SECONDS) // Hết hạn sau 10 giây không truy cập
                .maximumSize(10) // Giới hạn số lượng mục tối đa là 10
                .refreshAfterWrite(2, TimeUnit.SECONDS) // Làm mới sau 2 giây
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) {
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
                    String value = cache.get(keyFinding); // Lấy giá trị từ cache

                    System.out.println("Giá trị lấy từ cache: " + value);
                } catch (NumberFormatException e) {
                    log.error("Đầu vào không hợp lệ: {}", input);
                }

                System.out.println("==============================");
                cache.asMap().forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
            } while (true);
        } catch (Exception e) {
            log.error("Đã xảy ra lỗi: {}", e.getMessage());
        }
    }

    private static String getValue(Integer key) {
        return keyValueMap.get(key);
    }
}