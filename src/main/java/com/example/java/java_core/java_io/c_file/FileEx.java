package com.example.java.java_core.java_io.c_file;

import java.io.File;
import java.util.Date;


public class FileEx {
    public static void main(String[] args) {
        //1 Đường dẫn (Path) là gì?
        //2 Đường dẫn tương đối và tuyệt đối
        //3 Giới thiệu lớp Java.io.File
        File file = new File("data/test.txt");
        System.out.println("Path exists : " + file.exists());
        if (file.exists()) {
            System.out.println("isDirectory : " + file.isDirectory());
            System.out.println("isHidden : " + file.isHidden());
            System.out.println("Simple Name: " + file.getName());
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Length : " + file.length() + " (bytes)");
            long lastMofifyInMillis = file.lastModified(); // milliseconds
            Date lastModifyDate = new Date(lastMofifyInMillis);
            System.out.println("Last modify date: " + lastModifyDate);

            //4 Lấy đường dẫn đến thư mục đang làm việc (Project Path)
            File file4 = new File("");
            String currentDirectory = file4.getAbsolutePath();
            System.out.println("Current working directory : " + currentDirectory);
            //5 Tạo thư mục

            //6 Bộ lọc File (File Filter)

            //7 Đổi tên thư mục và tập tin

            //8 Xóa thư mục và tập tin

            //9 Sao chép tập tin và thư mục
        }
    }
}
