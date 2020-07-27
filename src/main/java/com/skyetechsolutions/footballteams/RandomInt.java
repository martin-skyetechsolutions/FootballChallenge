package com.skyetechsolutions.footballteams;

import java.util.Random;

public class RandomInt {
    private static Random random = new Random();
    public static int range = 100;
    public static int multiplier = 1000;

    public static Integer generate(){
        return random.nextInt(range)*multiplier;
    }
}
