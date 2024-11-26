package com.example.java.java_library.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class Person {
    private String name;
    private String location;

    public Person(String name, String location) {
        super();
        this.name = name;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", location=" + location + "]";
    }

}

public class a_gson {

    public static void main(String[] args) {
        /**
         * Single Object
         */
//        Person person = new Person("GP Coder", "Viet Nam");
//        Gson gson = new Gson();
//        // Serialization
//        String json = gson.toJson(person);
//        System.out.println(json); // {"name":"GP Coder","location":"Viet Nam"}
//
//        // Deserialization
//        Person person2 = gson.fromJson(json, Person.class);
//        System.out.println(person2);


        /**
         * List Object
         */
        // Tạo danh sách người
        List<Person> people = new ArrayList<>();
        people.add(new Person("GP Coder", "Viet Nam"));
        people.add(new Person("Alice", "USA"));
        people.add(new Person("Bob", "UK"));

        Gson gson = new Gson();

        // Serialization
        String json = gson.toJson(people);
        System.out.println("Serialized JSON: " + json);
        // Output: Serialized JSON: [{"name":"GP Coder","location":"Viet Nam"},{"name":"Alice","location":"USA"},{"name":"Bob","location":"UK"}]

        // Deserialization
        Type personListType = new TypeToken<List<Person>>(){}.getType();
        List<Person> personList = gson.fromJson(json, personListType);
        System.out.println("Deserialized List: " + personList);
    }
}