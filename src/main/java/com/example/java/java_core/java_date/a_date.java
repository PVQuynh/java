package com.example.java.java_core.java_date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class a_date {
    public static void main(String[] args) throws ParseException {
        /**
         * use
         */
        // TimeUnit
        long seconds = 120; // 120 giây
        long minutes = TimeUnit.SECONDS.toMinutes(seconds);
        System.out.println(seconds + " giây tương đương với " + minutes + " phút.");
        // Timestamp: 2024-11-26 17:50:25.054
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Timestamp: " + timestamp);
        // SimpleDateFormat and Date: Tue Nov 26 17:45:23 ICT 2024
        SimpleDateFormat formatter1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date date = new Date();
        String formattedDate = formatter1.format(date);
        System.out.println("Formatted Date: " + formattedDate);
        // ZoneId: Asia/Ho_Chi_Minh
        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        System.out.println("Múi giờ: " + zoneId);
        // LocalDate:  2024-11-26
        LocalDate localDate = LocalDate.of(2024, 11, 26);
        LocalDate today = LocalDate.now();
        System.out.println("LocalDate: " + localDate);
        System.out.println("Today: " + today.getDayOfWeek());
        // LocalTime: 17:52:42.417488843
        LocalTime localTime = LocalTime.parse("17:52:42.417488843");
        LocalTime todayLocalTime = LocalTime.of(17, 52, 42);
        System.out.println("LocalTime: " + localTime);
        System.out.println("Today LocalTime: " + todayLocalTime);
        // LocalDateTime: 2024-11-26T17:45:23.480934277
        LocalDateTime localDateTime = LocalDateTime.parse("2024-11-26T17:45:23.480934277");
        System.out.println("LocalDateTime: " + localDateTime);
        // ZonedDateTime: 2024-11-26T17:48:04.765723854+07:00[Asia/Ho_Chi_Minh]
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2024-11-26T17:48:04.765723854+07:00[Asia/Ho_Chi_Minh]");
        System.out.println("ZonedDateTime: " + zonedDateTime);
        // DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);
        System.out.println("Formatted LocalDateTime: " + formattedDateTime);

        /**
         * zone
         */
        // Thời gian ở múi giờ Ho Chi Minh
        ZonedDateTime hcmTime = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        System.out.println("Thời gian hiện tại ở Ho Chi Minh: " + hcmTime);
        // Chuyển đổi sang múi giờ New York
        ZonedDateTime nyTime = hcmTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("Thời gian tương ứng ở New York: " + nyTime);
    }
}
