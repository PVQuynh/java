package com.example.java.java_core.java_io.b_character_io;

import java.io.*;

public class c_binary_character {
    public static void main(String[] args) throws IOException {
//        // Tạo một OutputStream (luồng đầu ra) để ghi dữ liệu vào file.
//        OutputStream out = new FileOutputStream("data/test.txt");
//
//        // Tạo một Character Stream (luồng ghi ký tự) với mã hóa (encoding) là UTF-8.
//        Writer writer = new OutputStreamWriter(out, "UTF-8");
//
//        String s = "Lập trình Java";
//        writer.write(s);
//        writer.close();

        // Tạo một binary Stream (luồng nhị phân), để đọc file.
        InputStream in = new FileInputStream("data/test.txt");

        // Tạo một Character stream (luồng ký tự) với mã hóa (encoding) là UTF-8.
        Reader reader = new InputStreamReader(in, "UTF-8");

        int i = 0;
        // Đọc lần lượt từng ký tự.
        while ((i = reader.read()) != -1) {
            // Ép kiểu (cast) thành một ký tự và in ra màn hình.
            System.out.println((char) i + " " + i);
        }
        reader.close();
    }
}
