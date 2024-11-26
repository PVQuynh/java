package com.example.java.java_library.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

class Student {

    @SerializedName("id")
    private int studentId;

    @SerializedName("name")
    private String studentName;

    @SerializedName("clazz")
    private String clazzId;

    @SerializedName("age")
    private transient int age;

    public Student() {
        super();
    }

    public Student(int studentId, String studentName, String clazzId, int age) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.clazzId = clazzId;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", clazzId='" + clazzId + '\'' +
                ", age=" + age +
                '}';
    }
}

public class c_gson_annotations {
    public static void main(String[] args) {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();

        final Student student = new Student(1, "GP Coder", "Java Dev", 23);
        final String json = gson.toJson(student);
        System.out.println("Json: " + json);

        final Student student2 = gson.fromJson(json, Student.class);
        System.out.println("Java Object: " + student2);
    }

}


