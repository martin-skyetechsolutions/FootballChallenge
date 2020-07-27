package com.skyetechsolutions.footballteams;

import java.util.Random;

public class RandomString {
    private static Random random = new Random();
    private static int leftLimit = 97; // letter 'a'
    private static int rightLimit = 122; // letter 'z'

    public static String generate(int length){
        String result = random.ints(leftLimit, rightLimit + 1)
            .limit(length)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        System.out.println("result: " + result);
        return result;
    }
}
