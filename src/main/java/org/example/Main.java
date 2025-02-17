package org.example;

import org.example.collection.task1.Filter;
import org.example.collection.task1.Utils;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] intArray = {0, 1, 2, 3, 4};
        Filter intFilter = o -> (Integer) o + 1;

        String[] strArray = {"0", "1", "2", "3", "4"};
        Filter strFilter = o -> o + " applied";
        Object[] newIntArray = Utils.filter(intArray, intFilter);
        Object[] newStrArray = Utils.filter(strArray, strFilter);

        System.out.println(Arrays.toString(intArray));
        System.out.println(Arrays.toString(newIntArray));

        System.out.println(Arrays.toString(strArray));
        System.out.println(Arrays.toString(newStrArray));
    }
}
