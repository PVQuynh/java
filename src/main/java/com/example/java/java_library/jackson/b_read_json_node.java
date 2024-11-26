package com.example.java.java_library.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;



public class b_read_json_node {
    public static void main(String[] args) {
        // Chuỗi JSON phức tạp
        String jsonString = "{ \"className\": \"Java Programming\", " +
                "\"teacher\": { \"name\": \"Mr. Smith\", \"experience\": 10 }, " +
                "\"students\": [ " +
                "{ \"id\": 1, \"name\": \"Alice\", \"age\": 20, \"grades\": { \"math\": 85, \"java\": 90 } }, " +
                "{ \"id\": 2, \"name\": \"Bob\", \"age\": 22, \"grades\": { \"math\": 78, \"java\": 88 } } " +
                "] }";

        // Tạo ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Chuyển đổi chuỗi JSON thành JsonNode
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // Truy cập tên lớp
            String className = jsonNode.get("className").asText();
            System.out.println("Class Name: " + className);

            // Truy cập thông tin giáo viên
            JsonNode teacherNode = jsonNode.get("teacher");
            String teacherName = teacherNode.get("name").asText();
            int teacherExperience = teacherNode.get("experience").asInt();
            System.out.println("Teacher: " + teacherName + ", Experience: " + teacherExperience + " years");

            // Truy cập danh sách học sinh
            JsonNode studentsNode = jsonNode.get("students");
            System.out.println("Students:");

            for (JsonNode studentNode : studentsNode) {
                int id = studentNode.get("id").asInt();
                String name = studentNode.get("name").asText();
                int age = studentNode.get("age").asInt();

                // Truy cập điểm
                JsonNode gradesNode = studentNode.get("grades");
                int mathGrade = gradesNode.get("math").asInt();
                int javaGrade = gradesNode.get("java").asInt();

                // In thông tin học sinh
                System.out.printf("ID: %d, Name: %s, Age: %d, Math Grade: %d, Java Grade: %d%n",
                        id, name, age, mathGrade, javaGrade);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}