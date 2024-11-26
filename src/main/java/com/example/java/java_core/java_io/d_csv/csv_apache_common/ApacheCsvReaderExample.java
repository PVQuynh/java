package com.example.java.java_core.java_io.d_csv.csv_apache_common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ApacheCsvReaderExample {

    public static void main(String[] args) throws IOException {
        String csvFile = "data/data.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("id", "code", "name").withIgnoreHeaderCase().withTrim());) {

            for (CSVRecord csvRecord : csvParser.getRecords()) {
                System.out.println("Country [id= " + csvRecord.get(0)
                        + ", code= " + csvRecord.get(1)
                        + " , name=" + csvRecord.get(2) + "]");
            }
        }
    }
}