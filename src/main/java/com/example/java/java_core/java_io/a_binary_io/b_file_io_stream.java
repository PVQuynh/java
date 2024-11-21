package com.example.java.java_core.java_io.a_binary_io;

import java.io.*;

public class b_file_io_stream {
    public static void main(String[] args) throws IOException {

//        // Tạo một đối tượng InputStream: Luồng đọc một file.
//        InputStream is = new FileInputStream("data/input.txt");
//
//        byte[] bytes = new byte[1];
//        int i = -1;
//
//        // Đọc lần lượt các byte (8bit) trong luồng và lưu vào biến i
//        // Khi đọc ra giá trị -1 nghĩa là kết thúc luồng.
//        while ((i = is.read(bytes)) != -1) {
//            String s = new String(bytes, 0, i);
//            System.out.println(s);
//        }
//        is.close();


        // Tạo một luồng ký tự đầu ra với mục đích ghi thông tin vào file
        OutputStream os = new FileOutputStream("data/output.txt");

        // Tạo một mảng byte ,ta sẽ ghi các byte này vào file nói trên .
        byte[] by = new byte[] { 'g', 'p', 'c', 'o', 'd', 'e', 'r', '.', 'c', 'o', 'm' };
        // Ghi lần lượt các ký tự vào luồng
        for (int i = 0; i < by.length; i++) {
            byte b = by[i];
            // Ghi ký tự vào luồng
            os.write(b);
        }
        // Đóng luồng đầu ra lại việc ghi xuống file hoàn tất.
        os.close();
    }
}
