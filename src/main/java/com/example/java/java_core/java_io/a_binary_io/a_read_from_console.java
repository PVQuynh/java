package com.example.java.java_core.java_io.a_binary_io;

import java.io.IOException;
import java.io.InputStream;

public class a_read_from_console {
    public static void main(String[] args) throws IOException {
//        InputStream is = System.in;
//        while (true) {
//            System.out.print("Nhập 1 ký tự: ");
//            int ch = is.read();
//            if (ch == 'q') {
//                System.out.println("Finished!");
//                break;
//            }
//            is.skip(2); // Loại bỏ 2 ký tự \r và \n
//            System.out.println("Ký tự nhận được: " + (char) ch);
//        }

        while (true) {
            System.out.print("Nhập chuỗi ký tự: ");
            byte[] bytes = new byte[100]; // Tạo vùng đệm để nhập chuỗi
            int length = System.in.read(bytes);
            String str = new String(bytes, 0, length - 2);
            if (str.equalsIgnoreCase("EXIT")) {
                System.out.println("Finished!");
                break;
            }
            System.out.println("Chuỗi nhận được: " + str);
        }
    }
}
