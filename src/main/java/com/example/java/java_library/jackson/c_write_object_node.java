package com.example.java.java_library.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class c_write_object_node {
    public static void main(String[] args) {
        // Tạo ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Tạo ObjectNode cho lớp học
        ObjectNode classNode = objectMapper.createObjectNode();
        classNode.put("className", "Java Programming");

        // Tạo ObjectNode cho giáo viên
        ObjectNode teacherNode = objectMapper.createObjectNode();
        teacherNode.put("name", "Mr. Smith");
        teacherNode.put("experience", 10);
        classNode.set("teacher", teacherNode);

        // Tạo ArrayNode cho danh sách học sinh
        ArrayNode studentsNode = objectMapper.createArrayNode();

        // Tạo ObjectNode cho học sinh Alice
        ObjectNode aliceNode = objectMapper.createObjectNode();
        aliceNode.put("id", 1);
        aliceNode.put("name", "Alice");
        aliceNode.put("age", 20);

        // Tạo ObjectNode cho điểm của Alice
        ObjectNode aliceGrades = objectMapper.createObjectNode();
        aliceGrades.put("math", 85);
        aliceGrades.put("java", 90);
        aliceNode.set("grades", aliceGrades);

        // Thêm Alice vào danh sách học sinh
        studentsNode.add(aliceNode);

        // Tạo ObjectNode cho học sinh Bob
        ObjectNode bobNode = objectMapper.createObjectNode();
        bobNode.put("id", 2);
        bobNode.put("name", "Bob");
        bobNode.put("age", 22);

        // Tạo ObjectNode cho điểm của Bob
        ObjectNode bobGrades = objectMapper.createObjectNode();
        bobGrades.put("math", 78);
        bobGrades.put("java", 88);
        bobNode.set("grades", bobGrades);

        // Thêm Bob vào danh sách học sinh
        studentsNode.add(bobNode);

        // Thêm danh sách học sinh vào lớp học
        classNode.set("students", studentsNode);

        // In ra JSON đã tạo
        String jsonString = classNode.toString();
        System.out.println("Generated JSON: " + jsonString);
    }
}