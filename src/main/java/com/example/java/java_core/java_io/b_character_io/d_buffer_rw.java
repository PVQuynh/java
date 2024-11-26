package com.example.java.java_core.java_io.b_character_io;

import java.io.*;

//  đọc dữ liệu theo dòng (line by line), dữ liệu lớn hiệu suất nhanh
public class d_buffer_rw {
    public static void main(String[] args) throws IOException {
//        FileWriter writer = new FileWriter("data/test.txt");
//        BufferedWriter buffer = new BufferedWriter(writer);
//        buffer.write("gpcoder.com");
//        buffer.close();
//        System.out.println("Success...");

        FileReader fr = new FileReader("data/test.txt");
        BufferedReader br = new BufferedReader(fr);

        int i;
        while ((i = br.read()) != -1) {
            System.out.print((char) i);
        }
        br.close();
        fr.close();
    }
}
