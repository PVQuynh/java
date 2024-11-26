package com.example.java.java_library.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Student {
    private String name;
    private int age;
    private List<Subject> subjects;

    public Student() {
        super();
    }

    public Student(String name, int age, List<Subject> subjects) {
        super();
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", subjects=" + subjects + "]";
    }
}

class Subject {
    public Subject() {
        super();
    }

    private String name;
    private float point;

    public Subject(String name, float point) {
        super();
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Subject [name=" + name + ", point=" + point + "]";
    }

}

public class a_object_json {
    // Object -> Json
//    public static void main(String[] args) throws Exception {
//        Subject math = new Subject("Math", 10.0f);
//        Subject physical = new Subject("Physical", 8.5f);
//        List<Subject> subjects = new ArrayList<>();
//        subjects.add(math);
//        subjects.add(physical);
//
//        Student student = new Student("GP Coder", 28, subjects);
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        // Convert object to JSON string and save into a file directly
//        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data/result.json"), student); // Plain JSON
//
//        // Convert object to JSON string
//        String jsonInString = mapper.writeValueAsString(student);
//        System.out.println("JSON: " + jsonInString);
//
//        // Convert object to JSON string and pretty print
//        jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
//        System.out.println("JSON pretty print: " + jsonInString);
//    }

    // Json -> Object
//    public static void main(String[] args) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//
//        // Convert JSON string from file to Object
//        Student student = mapper.readValue(new File("data/result.json"), Student.class);
//        System.out.println(student);
//
//        // Convert JSON string to Object
//        String jsonInString = "{\"name\":\"GP Coder\",\"age\":28,\"subjects\":[{\"name\":\"Math\",\"point\":10.0},{\"name\":\"Physical\",\"point\":8.0}]}";
//        student = mapper.readValue(jsonInString, Student.class);
//        System.out.println(student);
//
//        // Pretty print
//        jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
//        System.out.println(jsonInString);
//    }

    // Jsons -> Collection
    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        Student student1 = getStudent(1);
        Student student2 = getStudent(2);

        Student[] students = { student1, student2 };

        // Convert array object to JSON string
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(students);
        System.out.println("JSON: " + jsonInString);

        // Convert a JSON Array format to a Java List object
        List<Student> list = mapper.readValue(jsonInString, new TypeReference<List<Student>>(){});
        for (Student student : list) {
            System.out.println(student);
        }
    }

    private static Student getStudent(int id) {
        Subject math = new Subject("Math", 10.0f);
        Subject physical = new Subject("Physical", 8.5f);
        List<Subject> subjects = new ArrayList<>();
        subjects.add(math);
        subjects.add(physical);

        return new Student("GP Coder " + id, 28, subjects);
    }
}
