package com.example.java.java_core.java_date;

import java.sql.Timestamp;
import java.time.*;
import java.util.Date;

public class a_date {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);

        ZoneId defaultZoneId = ZoneId.systemDefault();
        System.out.println("Default Zone ID: " + defaultZoneId);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Current Timestamp: " + timestamp);

        LocalDate localDate = LocalDate.now();
        System.out.println("Current Date: " + localDate);

        LocalTime localTime = LocalTime.now();
        System.out.println("Current Time: " + localTime);
    }
}
