package com.example.java.java_core.java_io.d_csv.csv_opencsv;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.opencsv.CSVReader;

public class OpenCsvReaderExample {
    public static void main(String[] args) throws IOException {

        String csvFile = "data/data.csv";
        try (Reader reader = new FileReader(csvFile);
             CSVReader csvReader = new CSVReader(reader);) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                System.out.println("Country [id= " + line[0]
                        + ", code= " + line[1]
                        + " , name=" + line[2] + "]");
            }
        }
    }
}