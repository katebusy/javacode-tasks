package org.example.collection.task1;

import java.util.Arrays;

public class Utils {

    public static Object[] filter(Object[] array, Filter filter) {
        return Arrays.stream(array)
                .map(filter::apply)
                .toArray();
    }
}
