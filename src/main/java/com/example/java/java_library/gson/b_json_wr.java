package com.example.java.java_library.gson;

import com.google.gson.stream.JsonReader;

import java.io.*;

// xử lý luồng Gson rất nhanh nhưng rất khó code vì bạn cần phải xử lý từng chi tiết của dữ liệu JSON đang xử lý
public class b_json_wr {
    // write
//    public static void main(String args[]) throws IOException {
//        OutputStream out = new FileOutputStream("data/result.json");
//        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
//
//        writer.beginObject(); // {
//        writer.name("name").value("GP Coder"); // "name" : "gpcoder"
//        writer.name("website").value("https://gpcoder.com"); // "website" : "https://gpcoder.com"
//        writer.name("year").value(2017); // "year" : 2017
//
//        writer.name("posts"); // "colors" :
//        writer.beginArray(); // [
//        writer.value("Java Core"); // "Java Core"
//        writer.value("Design Pattern"); // "Design Pattern"
//        writer.value("Spring"); // "Spring"
//        writer.endArray(); // ]
//
//        writer.endObject(); // }
//        writer.close();
//
//        System.out.println("Done!");
//    }

    public static void main(String[] args) throws IOException {
        InputStream out = new FileInputStream("data/result.json");
        JsonReader reader = new JsonReader(new InputStreamReader(out, "UTF-8"));
        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals("name")) {
                System.out.println(reader.nextString());
            } else if (name.equals("website")) {
                System.out.println(reader.nextString());
            } else if (name.equals("year")) {
                System.out.println(reader.nextInt());
            } else if (name.equals("posts")) {

                // it's an array.
                reader.beginArray();
                while (reader.hasNext()) {
                    System.out.println(reader.nextString());
                }
                reader.endArray();
            } else {// unexpected value, skip it or generate error
                reader.skipValue();
            }
        }

        reader.endObject();
        reader.close();
    }
}
