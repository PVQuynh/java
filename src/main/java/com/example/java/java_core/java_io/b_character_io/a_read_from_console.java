package com.example.java.java_core.java_io.b_character_io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class a_read_from_console {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Enter your name: ");
            String name = br.readLine();
            if (name.equalsIgnoreCase("Exit")) {
                System.out.println("Finished!");
                break;
            }
            System.out.println("Hello " + name);
        }
    }
}
