package com.keksec.data_structures.custom_hash_map;

public class Example {

    public static void main(String[] args) {
        MyHashMap<String, String> myMap = new MyHashMap<>();
        String key = "Kek";
        String value = "Lol";
        myMap.put(key, value);
        System.out.println(myMap.get(key));
        System.out.println(myMap.remove(key));
        System.out.println(myMap.get(key));
    }
}
