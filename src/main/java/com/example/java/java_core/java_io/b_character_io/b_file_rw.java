package com.example.java.java_core.java_io.b_character_io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class b_file_rw {
    public static void main(String[] args) throws IOException {
//        try {
//            FileWriter fw = new FileWriter("data/test.txt");
//            fw.write("gpcoder.com");
//            fw.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        System.out.println("Success...");

        FileReader fr = new FileReader("data/test.txt");
        int i;
        while ((i = fr.read()) != -1) {
            System.out.print((char) i);
        }
        fr.close();
    }
}
