package com.example.java.java_core.java_io.a_binary_io;

import java.io.*;

public class c_bytearray_io_stream {
    public static void main(String[] args) throws IOException {
        // input
        byte[] buf = new byte[] { 'g', 'p', 'c', 'o', 'd', 'e', 'r', '.', 'c', 'o', 'm' };
        // Create the new byte array input stream
        InputStream byt = new ByteArrayInputStream(buf);
        int k = 0;
        while ((k = byt.read()) != -1) {
            // Conversion of a byte into character
            char ch = (char) k;
            System.out.println("ASCII value of Character is:" + k + " - Special character is: " + ch);
        }
        // output
        OutputStream fout1 = new FileOutputStream("data/f1.txt");
        FileOutputStream fout2 = new FileOutputStream("data/f2.txt");

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        bout.write(65);
        bout.writeTo(fout1);
        bout.writeTo(fout2);

        bout.flush();
        bout.close();// has no effect
        System.out.println("Success...");

    }
}
