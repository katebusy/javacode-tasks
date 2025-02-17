package org.example;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] strArray = {"one", "two", "three", "three", "two", "three"};
        Integer[] intArray = {1, 3, 2, 3, 2, 3};

        Map<String, Long> strMap = ArrayConverter.arrayToMap(strArray);
        Map<Integer, Long> intMap = ArrayConverter.arrayToMap(intArray);

        strMap.entrySet().forEach(System.out::println);
        intMap.entrySet().forEach(System.out::println);
    }
}
