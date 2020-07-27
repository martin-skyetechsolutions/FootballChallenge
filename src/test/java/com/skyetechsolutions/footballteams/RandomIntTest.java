package com.skyetechsolutions.footballteams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomIntTest {

    @Test
    void singleRandom(){
        int actual = RandomInt.generate();
        System.out.println("singleRandom: " + actual);
        assertTrue(1 < actual);
        assertTrue(RandomInt.range * RandomInt.multiplier > actual);
    }
}