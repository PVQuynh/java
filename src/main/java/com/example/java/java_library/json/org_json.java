package com.example.java.java_library.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class org_json {
    public static void main(String[] args) {
        // Tạo mảng JSON
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("Hanoi");
        jsonArray.put("Ho Chi Minh");
        jsonArray.put("Da Nang");

        // Tạo đối tượng JSON chứa mảng
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cities", jsonArray);

        // In ra đối tượng JSON
        System.out.println("Đối tượng JSON với mảng: " + jsonObject.toString());

        // Truy xuất mảng và in từng thành phần
        JSONArray cities = jsonObject.getJSONArray("cities");
        for (int i = 0; i < cities.length(); i++) {
            System.out.println("Thành phố: " + cities.getString(i));
        }
    }
}
