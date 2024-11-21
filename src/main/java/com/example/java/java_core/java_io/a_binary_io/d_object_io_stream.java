package com.example.java.java_core.java_io.a_binary_io;

import java.io.*;

class Student implements Serializable {

    private static final long serialVersionUID = -266706354210367639L;

    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + "]";
    }
}

public class d_object_io_stream {
    public static void main(String[] args) throws IOException {
//        ObjectOutputStream oos = null;
//        try {
//            oos = new ObjectOutputStream(new FileOutputStream("data/student.txt"));
//            Student student = new Student(1, "gpcoder.com", 28);
//            oos.writeObject(student);
//            oos.flush();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            oos.close();
//        }
//        System.out.println("success...");


        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("data/student.txt"));
            Student student = (Student) ois.readObject();
            System.out.println(student);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            ois.close();
        }
    }
}
