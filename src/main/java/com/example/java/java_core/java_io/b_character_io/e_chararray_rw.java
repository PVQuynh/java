package com.example.java.java_core.java_io.b_character_io;

import java.io.CharArrayWriter;
import java.io.FileWriter;
import java.io.IOException;

public class e_chararray_rw {
    public static void main(String[] args) throws IOException {
//        char[] ary = { 'g', 'p', 'c', 'o', 'd', 'e', 'r', '.', 'c', 'o', 'm' };
//        CharArrayReader reader = new CharArrayReader(ary);
//        int k = 0;
//        // Read until the end of a file
//        while ((k = reader.read()) != -1) {
//            char ch = (char) k;
//            System.out.print(ch + " : ");
//            System.out.println(k);
//        }


        CharArrayWriter out = new CharArrayWriter();
        out.write("gpcoder.com");

        FileWriter f1 = new FileWriter("data/f1.txt");
        out.writeTo(f1);
        f1.close();

        System.out.println("Success...");
    }
}
