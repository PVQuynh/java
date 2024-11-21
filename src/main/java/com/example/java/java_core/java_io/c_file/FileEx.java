package com.example.java.java_core.java_io.c_file;

import java.io.File;

public class FileEx {
    public static void main(String[] args) {
        // Làm việc với file

        File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.println(root.getAbsolutePath());
        }
    }
}
